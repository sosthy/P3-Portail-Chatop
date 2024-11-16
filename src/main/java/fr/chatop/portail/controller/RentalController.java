package fr.chatop.portail.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.chatop.portail.api.RentalApi;
import fr.chatop.portail.dto.MessageResponseDTO;
import fr.chatop.portail.dto.RentalDTO;
import fr.chatop.portail.dto.RentalResponseDTO;
import fr.chatop.portail.mapper.RentalMapper;
import fr.chatop.portail.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class RentalController implements RentalApi {

    private final RentalService rentalService;

    @Override
    public ResponseEntity<List<RentalResponseDTO>> getAllRentals() {
        
        final List<RentalDTO> rentalDTOs = rentalService.getAllRentals();
        return ResponseEntity.ok(RentalMapper.INSTANCE.toResponseDTO(rentalDTOs));
    }

    @Override
    public ResponseEntity<RentalResponseDTO> getRentalById(Long rentalId) {
        
        final RentalDTO rentalDTO = rentalService.getRentalById(rentalId);
        return ResponseEntity.ok(RentalMapper.INSTANCE.toResponseDTO(rentalDTO));
    }

    @Override
    public ResponseEntity<MessageResponseDTO> createRental(@Valid String name, @Valid Double surface,
            @Valid Double price, List<MultipartFile> picture, @Valid String description) {

        final var rentalDTO = new RentalDTO();
        rentalDTO.setName(name);
        rentalDTO.setSurface(surface);
        rentalDTO.setPrice(price);
        rentalDTO.setPicturesFiles(picture);
        rentalDTO.setDescription(description);

        rentalService.createRental(rentalDTO);
        
        final var messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessage("Rental created !");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponseDTO);
    }

    @Override
    public ResponseEntity<MessageResponseDTO> updateRentalById(Long rentalId, @Valid String name, @Valid Double surface,
            @Valid Double price, List<MultipartFile> picture, @Valid String description) {
        
        final var rentalDTO = new RentalDTO();
        rentalDTO.setId(rentalId);
        rentalDTO.setName(name);
        rentalDTO.setSurface(surface);
        rentalDTO.setPrice(price);
        rentalDTO.setPicturesFiles(picture);
        rentalDTO.setDescription(description);

        rentalService.updateRental(rentalDTO);
        
        final var messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessage("Rental updated !");
        return ResponseEntity.ok(messageResponseDTO);
    }
    
}
