package com.clinicodi.CliniCodi.dto;

public class LoginResponse {
    private Long id;
    private String email;
    private String nombreUsuario;

    public LoginResponse(Long id, String email, String nombreUsuario) {
        this.id = id;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
