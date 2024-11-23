package fr.chatop.portail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RentalDTO {
    
    private Long id;
    private String name;
    private List<MultipartFile> picturesFiles = new ArrayList<>();
    private List<String> picturesNames = new ArrayList<>();
    private String description;
    private Long ownerId;
    private Double surface;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
