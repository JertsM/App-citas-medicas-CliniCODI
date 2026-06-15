package com.clinicodi.CliniCodi.service;

import com.clinicodi.CliniCodi.dto.CrearCitaRequest;
import com.clinicodi.CliniCodi.entidades.Cita;
import com.clinicodi.CliniCodi.entidades.EstadoCita;
import com.clinicodi.CliniCodi.entidades.Usuario;
import com.clinicodi.CliniCodi.repository.CitaRepository;
import com.clinicodi.CliniCodi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UserRepository usuarioRepository;

    public Cita crearCita(CrearCitaRequest request) {
        boolean ocupada = citaRepository.existsByFecha_Hora_Estado(
                request.getFecha(),
                request.getHora(),
                EstadoCita.ACTIVA
        );

        if (ocupada) {
            throw new RuntimeException("La hora seleccionada no está disponible");
        }

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Optional<Cita> citaActiva = citaRepository.findByUsuario_Id_Estado(request.getIdUsuario(), EstadoCita.ACTIVA);

        if (citaActiva.isPresent()) {
            throw new RuntimeException("El usuario ya tiene una cita activa");
        }

        Cita cita = new Cita();
        cita.setFecha(request.getFecha());
        cita.setHora(request.getHora());
        cita.setEstado(EstadoCita.ACTIVA);
        cita.setUsuario(usuario);

        return citaRepository.save(cita);

    }

}
