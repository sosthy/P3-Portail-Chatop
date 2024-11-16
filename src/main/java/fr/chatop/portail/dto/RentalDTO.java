package fr.chatop.portail.dto;

import java.util.Collections;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RentalDTO {
    
    private Long id;
    private String name;
    private List<MultipartFile> picturesFiles = Collections.emptyList();
    private List<String> picturesNames = Collections.emptyList();
    private String description;
    private Long ownerId;
    private Double surface;
    private Double price;
    private String createdAt;
    private String updatedAt;

}
