package fr.chatop.portail.controller;

import fr.chatop.portail.api.RentalApi;
import fr.chatop.portail.dto.AllRentalResponseDTO;
import fr.chatop.portail.dto.MessageResponseDTO;
import fr.chatop.portail.dto.RentalDTO;
import fr.chatop.portail.dto.RentalResponseDTO;
import fr.chatop.portail.mapper.RentalMapper;
import fr.chatop.portail.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class RentalController implements RentalApi {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    @Override
    public ResponseEntity<AllRentalResponseDTO> getAllRentals() {
        
        final List<RentalDTO> rentalDTOs = rentalService.getAllRentals();
        final AllRentalResponseDTO response = new AllRentalResponseDTO();
        response.setRentals(rentalMapper.toResponseDTO(rentalDTOs));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RentalResponseDTO> getRentalById(Long rentalId) {
        
        final RentalDTO rentalDTO = rentalService.getRentalById(rentalId);
        return ResponseEntity.ok(rentalMapper.toResponseDTO(rentalDTO));
    }

    @Override
    public ResponseEntity<MessageResponseDTO> createRental(@Valid String name, @Valid Double surface,
            @Valid Double price, MultipartFile picture, @Valid String description) {

        final var rentalDTO = new RentalDTO();
        rentalDTO.setName(name);
        rentalDTO.setSurface(surface);
        rentalDTO.setPrice(price);
        rentalDTO.setPictureFile(picture);
        rentalDTO.setDescription(description);

        rentalService.createRental(rentalDTO);
        
        final var messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessage("Rental created !");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponseDTO);
    }

    @Override
    public ResponseEntity<MessageResponseDTO> updateRentalById(Long rentalId, @Valid String name, @Valid Double surface,
            @Valid Double price, MultipartFile picture, @Valid String description) {
        
        final var rentalDTO = new RentalDTO();
        rentalDTO.setId(rentalId);
        rentalDTO.setName(name);
        rentalDTO.setSurface(surface);
        rentalDTO.setPrice(price);
        rentalDTO.setPictureFile(picture);
        rentalDTO.setDescription(description);

        rentalService.updateRental(rentalDTO);
        
        final var messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessage("Rental updated !");
        return ResponseEntity.ok(messageResponseDTO);
    }
    
}
