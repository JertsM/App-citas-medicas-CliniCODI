package com.clinicodi.CliniCodi.controller;


import com.clinicodi.CliniCodi.dto.PerfilResponse;
import com.clinicodi.CliniCodi.entidades.Usuario;
import com.clinicodi.CliniCodi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/perfil")
    private ResponseEntity<PerfilResponse> obtenerPerfil(
            @RequestParam String email
    ) {
        Usuario usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        PerfilResponse perfil = new PerfilResponse(
                usuario.getNombreUsuario(),
                usuario.getEmail()
        );

        return ResponseEntity.ok(perfil);
    }
}
