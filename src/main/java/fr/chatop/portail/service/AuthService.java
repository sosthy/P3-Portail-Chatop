package fr.chatop.portail.service;

import fr.chatop.portail.dto.TokenResponseDTO;
import fr.chatop.portail.dto.UserLoginDTO;
import fr.chatop.portail.dto.UserRegisterDTO;
import fr.chatop.portail.dto.UserResponseDTO;


public interface AuthService {
    TokenResponseDTO authenticateUser(UserLoginDTO userLoginDTO);
    TokenResponseDTO registerUser(UserRegisterDTO userRegisterDTO);
    UserResponseDTO whoIam();
}
