package fr.chatop.portail.controller;

import fr.chatop.portail.api.AuthApi;
import fr.chatop.portail.dto.*;
import fr.chatop.portail.mapper.UserMapper;
import fr.chatop.portail.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<TokenResponseDTO> authenticateUser(@Valid UserLoginDTO userLoginDTO) {
        final String token = authService.authenticateUser(userLoginDTO);
        final TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setToken(token);
        return ResponseEntity.ok(tokenResponseDTO);
    }

    @Override
    public ResponseEntity<TokenResponseDTO> registerUser(@Valid UserRegisterDTO userRegisterDTO) {
        final String token = authService.registerUser(userRegisterDTO);
        final TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponseDTO);
    }

    @Override
    public ResponseEntity<UserResponseDTO> whoIam() {
        final AppUserDTO appUserDTO = authService.whoIam();
        return ResponseEntity.ok(userMapper.toDTO(appUserDTO));
    }

}
