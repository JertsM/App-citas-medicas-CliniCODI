package com.clinicodi.CliniCodi.repository;

import com.clinicodi.CliniCodi.entidades.Cita;
import com.clinicodi.CliniCodi.entidades.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    boolean existsByFecha_Hora_Estado(LocalDate fecha, LocalTime hora, EstadoCita estado);

    Optional<Cita> findByUsuario_Id_Estado(Long idUsuario, EstadoCita estado);

    List<Cita> findByUsuario_Id(Long idUsuario);
}
