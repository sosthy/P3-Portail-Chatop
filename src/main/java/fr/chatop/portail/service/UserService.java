package fr.chatop.portail.service;

import fr.chatop.portail.dto.AppUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    AppUserDTO getUserById(Long id);
}
