package fr.chatop.portail.service.impl;

import fr.chatop.portail.dto.AppUserDTO;
import fr.chatop.portail.dto.UserLoginDTO;
import fr.chatop.portail.dto.UserRegisterDTO;
import fr.chatop.portail.entity.AppRole;
import fr.chatop.portail.entity.AppUser;
import fr.chatop.portail.mapper.UserMapper;
import fr.chatop.portail.repository.RoleRepository;
import fr.chatop.portail.repository.UserRepository;
import fr.chatop.portail.service.AuthService;
import fr.chatop.portail.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticateUser(UserLoginDTO userLoginDTO) {

        final Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        final Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        return jwtUtils.generateJwtToken(authenticationResponse);
    }
    
    @Override
    public String registerUser(UserRegisterDTO userRegisterDTO) {

        final String passwordEncrypted = passwordEncoder.encode(userRegisterDTO.getPassword());

        final AppRole userRole = roleRepository.findByAuthority("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new AppRole("ROLE_USER")));

        final AppUser user = new AppUser(userRegisterDTO.getEmail(), passwordEncrypted, Collections.singleton(userRole));
        user.setName(userRegisterDTO.getName());
        userRepository.save(user);

        return authenticateUser(new UserLoginDTO().email(userRegisterDTO.getEmail()).password(userRegisterDTO.getPassword()));
    }

    @Override
    public AppUserDTO whoIam() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final Optional<AppUser> appUser = userRepository.findByEmail(userDetails.getUsername());
        return userMapper.toDTO(appUser.get());
    }
    
}
