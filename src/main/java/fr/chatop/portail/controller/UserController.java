package fr.chatop.portail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatop.portail.api.UserApi;
import fr.chatop.portail.dto.UserResponseDTO;

@RestController
@RequestMapping("api/v1")
public class UserController implements UserApi {

    @Override
    public ResponseEntity<UserResponseDTO> getUserById(Long userId) {
        // TODO Auto-generated method stub
        return UserApi.super.getUserById(userId);
    }
    
}
