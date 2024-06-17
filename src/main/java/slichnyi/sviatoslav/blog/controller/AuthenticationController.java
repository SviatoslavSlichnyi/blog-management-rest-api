package slichnyi.sviatoslav.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import slichnyi.sviatoslav.blog.dto.LoginResponse;
import slichnyi.sviatoslav.blog.dto.LoginUserDTO;
import slichnyi.sviatoslav.blog.dto.RegisterUserDTO;
import slichnyi.sviatoslav.blog.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        authenticationService.signup(registerUserDTO);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        return authenticationService.authenticate(loginUserDTO);
    }
}
