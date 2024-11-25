package fr.chatop.portail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RentalDTO {
    
    private Long id;
    private String name;
    private MultipartFile pictureFile;
    private String pictureName;
    private String description;
    private Long ownerId;
    private Double surface;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
