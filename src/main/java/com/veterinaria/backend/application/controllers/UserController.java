package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreateUserReq;
import com.veterinaria.backend.application.dto.request.LoginReq;
import com.veterinaria.backend.application.dto.response.LoginResponse;
import com.veterinaria.backend.domain.User;
import com.veterinaria.backend.services.AuthService;
import com.veterinaria.backend.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final AuthService authService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> registrar(@Valid @RequestBody CreateUserReq req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setRol(req.getRol());

        userService.register(user);

        return ResponseHandler.success("Usuario registrado correctamente.");
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginReq loginReq) {
        String token = authService.login(loginReq);
        LoginResponse response = new LoginResponse(token);
        return ResponseHandler.success(response, "Inicio de sesión exitoso.");
    }
}
