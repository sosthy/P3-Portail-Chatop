package fr.chatop.portail.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppUserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String createdAt;
    private String updatedAt;

}