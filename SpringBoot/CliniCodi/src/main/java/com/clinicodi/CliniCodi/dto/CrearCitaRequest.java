package com.clinicodi.CliniCodi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CrearCitaRequest {
    private LocalDate fecha;
    private LocalTime hora;
    private Long idUsuario;


}
