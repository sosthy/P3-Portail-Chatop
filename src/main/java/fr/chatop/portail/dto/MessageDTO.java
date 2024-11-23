package fr.chatop.portail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageDTO {

    private Long id;
    private RentalDTO rental;
    private AppUserDTO owner;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
