package fr.chatop.portail.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.chatop.portail.api.RentalApi;
import fr.chatop.portail.dto.MessageResponseDTO;
import fr.chatop.portail.dto.RentalResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class RentalController implements RentalApi {


    @Override
    public ResponseEntity<List<RentalResponseDTO>> getAllRentals() {
        // TODO Auto-generated method stub
        return RentalApi.super.getAllRentals();
    }

    @Override
    public ResponseEntity<RentalResponseDTO> getRentalById(Long rentalId) {
        // TODO Auto-generated method stub
        return RentalApi.super.getRentalById(rentalId);
    }

    @Override
    public ResponseEntity<MessageResponseDTO> createRental(@Valid Long id, @Valid String name, @Valid Integer surface,
            @Valid Integer price, List<MultipartFile> picture, @Valid String description, @Valid Integer ownerId,
            @Valid String createdAt, @Valid String updatedAt) {
        // TODO Auto-generated method stub
        return RentalApi.super.createRental(id, name, surface, price, picture, description, ownerId, createdAt, updatedAt);
    }

    @Override
    public ResponseEntity<MessageResponseDTO> updateRentalById(Long rentalId, @Valid Long id, @Valid String name,
            @Valid Integer surface, @Valid Integer price, List<MultipartFile> picture, @Valid String description,
            @Valid Integer ownerId, @Valid String createdAt, @Valid String updatedAt) {
        // TODO Auto-generated method stub
        return RentalApi.super.updateRentalById(rentalId, id, name, surface, price, picture, description, ownerId, createdAt,
                updatedAt);
    }
    
}
