package com.clinicodi.CliniCodi.repository;

import com.clinicodi.CliniCodi.entidades.Cita;
import com.clinicodi.CliniCodi.entidades.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    boolean existsByFechaAndHoraAndEstado(LocalDate fecha, LocalTime hora, EstadoCita estado);

    Optional<Cita> findByUsuario_IdAndEstado(Long idUsuario, EstadoCita estado);

    Optional<Cita> findByUsuario_EmailAndEstado(String email, EstadoCita estado);

    List<Cita> findByUsuario_Id(Long idUsuario);

    Optional<Cita> findByIdCitaAndUsuario_Email(
            Integer idCita,
            String email
    );

    List<Cita> findAllByUsuario_EmailAndEstado(
            String email,
            EstadoCita estado
    );

    Optional<Cita> findByFechaAndHoraAndEstado(
            LocalDate fecha,
            LocalTime hora,
            EstadoCita estado
    );

}
