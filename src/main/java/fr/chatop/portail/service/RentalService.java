package fr.chatop.portail.service;

import fr.chatop.portail.dto.RentalDTO;

import java.util.List;

public interface RentalService {
    List<RentalDTO> getAllRentals();
    RentalDTO getRentalById(Long rentalId);
    RentalDTO createRental(RentalDTO rentalDTO);
    RentalDTO updateRental(RentalDTO rentalDTO);
}