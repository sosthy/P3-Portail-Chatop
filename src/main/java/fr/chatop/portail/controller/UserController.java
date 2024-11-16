package fr.chatop.portail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatop.portail.api.UserApi;
import fr.chatop.portail.dto.UserResponseDTO;
import fr.chatop.portail.mapper.UserMapper;
import fr.chatop.portail.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseDTO> getUserById(Long userId) {
        final var userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userDTO));
    }
    
}
