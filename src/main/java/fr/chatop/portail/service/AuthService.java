package fr.chatop.portail.service;

import fr.chatop.portail.dto.AppUserDTO;
import fr.chatop.portail.dto.TokenResponseDTO;
import fr.chatop.portail.dto.UserLoginDTO;
import fr.chatop.portail.dto.UserRegisterDTO;


public interface AuthService {
    TokenResponseDTO authenticateUser(UserLoginDTO userLoginDTO);
    TokenResponseDTO registerUser(UserRegisterDTO userRegisterDTO);
    AppUserDTO whoIam();
}
