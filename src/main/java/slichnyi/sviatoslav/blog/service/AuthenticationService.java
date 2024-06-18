package slichnyi.sviatoslav.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slichnyi.sviatoslav.blog.dto.LoginResponse;
import slichnyi.sviatoslav.blog.dto.LoginUserDTO;
import slichnyi.sviatoslav.blog.dto.RegisterUserDTO;
import slichnyi.sviatoslav.blog.entity.ApplicationUser;
import slichnyi.sviatoslav.blog.entity.Role;
import slichnyi.sviatoslav.blog.repository.ApplicationUserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    private final ApplicationUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signup(RegisterUserDTO registerUserDTO) {
        ApplicationUser user = ApplicationUser.builder()
                .username(registerUserDTO.getUsername())
                .email(registerUserDTO.getEmail())
                .password(passwordEncoder.encode(registerUserDTO.getPassword()))
                .bio(registerUserDTO.getBio())
                .role(Role.builder().code("READER").build())
                .build();

        userRepository.save(user);
    }

    public LoginResponse authenticate(LoginUserDTO loginUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getEmail(),
                        loginUserDTO.getPassword()
                )
        );

        ApplicationUser authenticatedUser = userRepository.findByEmail(loginUserDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }
}
