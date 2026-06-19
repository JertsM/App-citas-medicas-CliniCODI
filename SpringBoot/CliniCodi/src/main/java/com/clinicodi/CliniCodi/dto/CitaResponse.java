package com.clinicodi.CliniCodi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaResponse {
    private Integer idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    public CitaResponse(Integer idCita, LocalDate fecha, LocalTime hora, String estado) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }
}
