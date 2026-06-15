package com.clinicodi.CliniCodi;

import com.clinicodi.CliniCodi.dto.LoginRequest;
import com.clinicodi.CliniCodi.dto.RegisterRequest;
import com.clinicodi.CliniCodi.entidades.Usuario;
import com.clinicodi.CliniCodi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    public UserRepository userRepository;
    Usuario usuario = new Usuario();


    public void registrar(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }

        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setEmail(request.getEmail());

        usuario.setPassword(request.getPassword());
        userRepository.save(usuario);
    }

    public Usuario login(LoginRequest request) {

        Usuario usuario = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "El usuario no existe"
                        )
                );

        if (usuario.getPassword() == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "El usuario no tiene contraseña guardada"
            );
        }
        if (!request.getPassword().equals(usuario.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        return usuario;
    }
}
