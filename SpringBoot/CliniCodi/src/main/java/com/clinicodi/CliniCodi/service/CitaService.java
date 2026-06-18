package com.clinicodi.CliniCodi.service;

import com.clinicodi.CliniCodi.dto.CitaRequest;
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

        public void crearCita(CitaRequest request) {

            Usuario usuario = usuarioRepository
                    .findByEmail(request.getEmailUsuario())
                    .orElseThrow(() ->
                            new RuntimeException("Usuario no encontrado"));

            boolean citaOcupada = citaRepository.existsByFechaAndHoraAndEstado(
                    request.getFecha(),
                    request.getHora(),
                    EstadoCita.PENDIENTE
            );

            if (citaOcupada) {
                throw new RuntimeException("Ya existe una cita para esa fecha y hora");
            }

            Optional<Cita> citaUsuario = citaRepository.findByUsuario_EmailAndEstado(
                    request.getEmailUsuario(),
                    EstadoCita.PENDIENTE
            );

            if (citaUsuario.isPresent()) {
                throw new RuntimeException("Ya tienes una cita pendiente");
            }

            Cita cita = new Cita();

            cita.setUsuario(usuario);
            cita.setFecha(request.getFecha());
            cita.setHora(request.getHora());
            cita.setEstado(EstadoCita.PENDIENTE);

            citaRepository.save(cita);
        }

}
