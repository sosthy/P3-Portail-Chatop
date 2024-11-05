package fr.chatop.portail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.chatop.portail.api.AuthApi;
import fr.chatop.portail.dto.TokenResponseDTO;
import fr.chatop.portail.dto.UserLoginDTO;
import fr.chatop.portail.dto.UserRegisterDTO;
import fr.chatop.portail.dto.UserResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class AuthController implements AuthApi {

    @Override
    public ResponseEntity<TokenResponseDTO> authenticateUser(@Valid UserLoginDTO userLoginDTO) {
        // TODO Auto-generated method stub
        return AuthApi.super.authenticateUser(userLoginDTO);
    }

    @Override
    public ResponseEntity<TokenResponseDTO> registerUser(@Valid UserRegisterDTO userRegisterDTO) {
        // TODO Auto-generated method stub
        return AuthApi.super.registerUser(userRegisterDTO);
    }

    @Override
    public ResponseEntity<UserResponseDTO> whoIam() {
        // TODO Auto-generated method stub
        return AuthApi.super.whoIam();
    }

}
