package com.projeto.ecommerce.controllers;

import com.projeto.ecommerce.DTOs.LoginRequest;
import com.projeto.ecommerce.entities.UserEntity;
import com.projeto.ecommerce.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // Busca o usuário pelo email
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Valida a senha (ideal seria usar BCrypt)
        if (!user.getPassword().equals(request.password())) {
            throw new RuntimeException("Senha inválida");
        }

        // Se chegou até aqui, login foi bem-sucedido
        return "Login realizado com sucesso para o usuário: " + user.getName();
    }
}