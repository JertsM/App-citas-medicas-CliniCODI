package com.clinicodi.CliniCodi.dto;

public class PerfilResponse {

    private String nombreUsuario;
    private String email;

    public PerfilResponse(String nombreUsuario, String email) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getEmail() {
        return email;
    }
}
