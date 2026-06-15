package com.clinicodi.CliniCodi.controller;

import com.clinicodi.CliniCodi.AuthService;
import com.clinicodi.CliniCodi.dto.LoginRequest;
import com.clinicodi.CliniCodi.dto.RegisterRequest;
import com.clinicodi.CliniCodi.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(
            @RequestBody RegisterRequest request) {
        System.out.println("ENTRANDO EN REGISTRO");
        authService.registrar(request);

        return ResponseEntity.ok(Map.of(
                "message", "Usuario registrado"
        ).toString());
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest request) {
        Usuario usuario = authService.login(request);
        return ResponseEntity.ok(usuario);
    }
}