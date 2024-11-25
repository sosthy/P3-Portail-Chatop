package fr.chatop.portail.service.impl;

import fr.chatop.portail.exception.StorageException;
import fr.chatop.portail.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    public FileSystemStorageService(@Value("${chatop.app.storage.location}") String rootLocation) throws IOException {
        this.rootLocation = Paths.get(rootLocation);

        if (Files.notExists(this.rootLocation, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectory(this.rootLocation);
        }
    }

    @Override
    public void store(MultipartFile file) {

        try 
        {
            if (file.isEmpty()) 
                throw new StorageException("Failed to store empty file.");

            final String filename = Objects.requireNonNull(file.getOriginalFilename());
            final Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) 
                throw new StorageException("Cannot store file outside current directory.");

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                // 3. Attendre la disponibilité du fichier
                waitForFileAvailability(destinationFile, 5, 200); // 5 tentatives, 200ms entre chaque tentative
            }
        } 
        catch (IOException e) 
        {
            throw new StorageException("Failed to store file");
        }
    }

    private void waitForFileAvailability(Path file, int retries, int delayMs) throws IOException {

        while (retries > 0)
        {
            if (Files.exists(file) && Files.isReadable(file)) {
                return; // Le fichier est disponible
            }

            try
            {
                Thread.sleep(delayMs); // Attendre avant la prochaine tentative
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt(); // Réinterrompre le thread
                throw new IOException("Attente interrompue pour le fichier : " + file, e);
            }

            retries--;
        }
        throw new IOException("Le fichier n'est pas disponible après plusieurs tentatives : " + file);
    }
    
}
