package fr.chatop.portail.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.chatop.portail.dto.TokenResponseDTO;
import fr.chatop.portail.dto.UserLoginDTO;
import fr.chatop.portail.dto.UserRegisterDTO;
import fr.chatop.portail.dto.UserResponseDTO;
import fr.chatop.portail.entity.AppUser;
import fr.chatop.portail.mapper.UserMapper;
import fr.chatop.portail.entity.AppRole;
import fr.chatop.portail.repository.RoleRepository;
import fr.chatop.portail.repository.UserRepository;
import fr.chatop.portail.service.AuthService;
import fr.chatop.portail.utils.JwtUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponseDTO authenticateUser(UserLoginDTO userLoginDTO) {

        final Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userLoginDTO.getLogin(), userLoginDTO.getPassword());
		final Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        
        final String jwt = jwtUtils.generateJwtToken(authenticationResponse);
        final TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setMessage(jwt);

        return tokenResponseDTO;
    }
    
    @Override
    public TokenResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {

        final String passwordEncrypted = passwordEncoder.encode(userRegisterDTO.getPassword());

        final AppRole userRole = roleRepository.findByAuthority("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new AppRole("ROLE_USER")));

        final AppUser user = new AppUser(userRegisterDTO.getEmail(), passwordEncrypted, Collections.singleton(userRole));
        userRepository.save(user);

        return authenticateUser(new UserLoginDTO().login(userRegisterDTO.getEmail()).password(userRegisterDTO.getPassword()));
    }

    @Override
    public UserResponseDTO whoIam() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final Optional<AppUser> appUser = userRepository.findByUsername(userDetails.getUsername());
        return UserMapper.INSTANCE.toDTO(appUser.get());
    }
    
}
