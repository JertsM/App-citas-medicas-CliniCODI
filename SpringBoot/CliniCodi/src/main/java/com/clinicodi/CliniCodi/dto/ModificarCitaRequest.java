package com.clinicodi.CliniCodi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ModificarCitaRequest {

    private LocalDate fecha;
    private LocalTime hora;
}
