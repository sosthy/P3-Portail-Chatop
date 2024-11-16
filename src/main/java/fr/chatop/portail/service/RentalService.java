package fr.chatop.portail.service;

import java.util.List;

import fr.chatop.portail.dto.RentalDTO;

public interface RentalService {
    List<RentalDTO> getAllRentals();
    RentalDTO getRentalById(Long rentalId);
    RentalDTO createRental(RentalDTO rentalDTO);
    RentalDTO updateRental(RentalDTO rentalDTO);
}