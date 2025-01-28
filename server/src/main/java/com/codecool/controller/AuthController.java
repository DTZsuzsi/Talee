package com.codecool.controller;

import com.codecool.DTO.auth.AuthResponseDTO;
import com.codecool.DTO.auth.CredentialsDTO;
import com.codecool.DTO.auth.RegistrationDTO;
import com.codecool.model.users.Role;
import com.codecool.model.users.UserEntity;
import com.codecool.repository.RoleRepository;
import com.codecool.repository.UserRepository;
import com.codecool.security.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTUtils jwtUtils;

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  public AuthController(AuthenticationManager authenticationManager,
                        UserRepository userRepository,
                        RoleRepository roleRepository,
                        PasswordEncoder passwordEncoder, JWTUtils jwtUtils) {

    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping("/register")
  public ResponseEntity<RegistrationDTO> register(@RequestBody CredentialsDTO credentials) {
    logger.info(String.valueOf(credentials));
    if (userRepository.existsByUsername(credentials.username())) {
      return new ResponseEntity<>(new RegistrationDTO("Username is taken!"), HttpStatus.BAD_REQUEST);
    }

    UserEntity user = new UserEntity();
    user.setUsername(credentials.username());
    user.setPassword(passwordEncoder.encode(credentials.password()));
    Role role = roleRepository.findByName("ROLE_USER").get();
    user.setRoles(Set.of(role));
    userRepository.save(user);

    return new ResponseEntity<>(new RegistrationDTO("Registration was successful"), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody CredentialsDTO credentials) {
    Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.username(),
                            credentials.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtUtils.generateJwtToken(authentication);

    User userDetails = (User) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

    return new ResponseEntity<>(new AuthResponseDTO(token, userDetails.getUsername(), roles, "User login successfully"), HttpStatus.OK);
  }
}
