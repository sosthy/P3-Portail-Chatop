package fr.chatop.portail.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.chatop.portail.dto.AppUserDTO;


public interface UserService extends UserDetailsService {
    AppUserDTO getUserById(Long id);
}
