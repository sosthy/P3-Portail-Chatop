package fr.chatop.portail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.chatop.portail.api.AuthApi;
import fr.chatop.portail.dto.AppUserDTO;
import fr.chatop.portail.dto.TokenResponseDTO;
import fr.chatop.portail.dto.UserDTO;
import fr.chatop.portail.dto.UserLoginDTO;
import fr.chatop.portail.dto.UserRegisterDTO;
import fr.chatop.portail.dto.UserResponseDTO;
import fr.chatop.portail.mapper.UserMapper;
import fr.chatop.portail.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<TokenResponseDTO> authenticateUser(@Valid UserLoginDTO userLoginDTO) {
        TokenResponseDTO tokenResponseDTO = authService.authenticateUser(userLoginDTO);
        return ResponseEntity.ok(tokenResponseDTO);
    }

    @Override
    public ResponseEntity<TokenResponseDTO> registerUser(@Valid UserRegisterDTO userRegisterDTO) {
        final TokenResponseDTO registeredUser = authService.registerUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @Override
    public ResponseEntity<UserResponseDTO> whoIam() {
        final AppUserDTO appUserDTO = authService.whoIam();
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(appUserDTO));
    }

}
