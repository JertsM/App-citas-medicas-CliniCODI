package com.clinicodi.CliniCodi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String nombreUsuario;
    private String email;
    private String password;

}
