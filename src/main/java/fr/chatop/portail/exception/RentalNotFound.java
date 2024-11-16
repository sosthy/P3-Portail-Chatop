package fr.chatop.portail.exception;

public class RentalNotFound extends RuntimeException {
    public RentalNotFound(Long rentalId) {
        super(String.format("Rental %d not found", rentalId));
    }
}
