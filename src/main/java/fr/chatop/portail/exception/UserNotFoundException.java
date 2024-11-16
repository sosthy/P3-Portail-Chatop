package fr.chatop.portail.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super(String.format("User %d not found", userId));
    }
}
