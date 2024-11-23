package fr.chatop.portail.controller;

import fr.chatop.portail.api.UserApi;
import fr.chatop.portail.dto.UserResponseDTO;
import fr.chatop.portail.mapper.UserMapper;
import fr.chatop.portail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponseDTO> getUserById(Long userId) {
        final var userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userMapper.toDTO(userDTO));
    }
    
}
