package fr.chatop.portail.exception;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(Long rentalId) {
        super(String.format("Rental %d not found", rentalId));
    }
}
