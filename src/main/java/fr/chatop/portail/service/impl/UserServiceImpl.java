package fr.chatop.portail.service.impl;

import fr.chatop.portail.dto.AppUserDTO;
import fr.chatop.portail.entity.AppUser;
import fr.chatop.portail.exception.UserNotFoundException;
import fr.chatop.portail.mapper.UserMapper;
import fr.chatop.portail.repository.UserRepository;
import fr.chatop.portail.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final var optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException(String.format("User '%s' not found !", email));

        return optionalUser.get();    
    }

    @Override
    public AppUserDTO getUserById(Long id) {

        AppUser optionalUser = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
        
        return userMapper.toDTO(optionalUser);
    }
}
