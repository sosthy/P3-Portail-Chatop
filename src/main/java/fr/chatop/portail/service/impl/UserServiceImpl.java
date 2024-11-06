package fr.chatop.portail.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.chatop.portail.repository.UserRepository;
import fr.chatop.portail.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var optionalUser = utilisateurRepository.findByUsername(username);

        if (optionalUser.isEmpty()) 
            throw new UsernameNotFoundException(String.format("User '%s' not found !", username));

        return optionalUser.get();    
    }
}
