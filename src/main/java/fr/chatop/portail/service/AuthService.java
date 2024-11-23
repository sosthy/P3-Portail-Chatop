package fr.chatop.portail.service;

import fr.chatop.portail.dto.AppUserDTO;
import fr.chatop.portail.dto.UserLoginDTO;
import fr.chatop.portail.dto.UserRegisterDTO;


public interface AuthService {
    String authenticateUser(UserLoginDTO userLoginDTO);
    String registerUser(UserRegisterDTO userRegisterDTO);
    AppUserDTO whoIam();
}
