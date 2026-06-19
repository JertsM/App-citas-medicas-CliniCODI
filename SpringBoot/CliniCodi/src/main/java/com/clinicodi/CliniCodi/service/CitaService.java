package com.clinicodi.CliniCodi.service;

import com.clinicodi.CliniCodi.dto.CitaRequest;
import com.clinicodi.CliniCodi.dto.CitaResponse;
import com.clinicodi.CliniCodi.entidades.Cita;
import com.clinicodi.CliniCodi.entidades.EstadoCita;
import com.clinicodi.CliniCodi.entidades.Usuario;
import com.clinicodi.CliniCodi.repository.CitaRepository;
import com.clinicodi.CliniCodi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
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

    public List<CitaResponse> obtenerCitasUsuario(String email) {

        List<Cita> citas = citaRepository.findAllByUsuario_EmailAndEstado(
                email,
                EstadoCita.PENDIENTE
        );

        System.out.println("Citas encontradas: " + citas.size());

        return citas.stream()
                .map(cita -> new CitaResponse(
                        cita.getIdCita(),
                        cita.getFecha(),
                        cita.getHora(),
                        cita.getEstado().name()
                ))
                .toList();
    }

    public void cancelarCita(Integer idCita, String email) {

        Cita cita = citaRepository
                .findByIdCitaAndUsuario_Email(idCita, email)
                .orElseThrow(() ->
                        new RuntimeException("Cita no encontrada"));

        cita.setEstado(EstadoCita.CANCELADA);

        citaRepository.save(cita);
    }

    public void modificarCita(Integer idCita, CitaRequest request) {

        Cita cita = citaRepository
                .findByIdCitaAndUsuario_Email(
                        idCita,
                        request.getEmailUsuario()
                )
                .orElseThrow(() ->
                        new RuntimeException("Cita no encontrada"));

        DayOfWeek diaSemana = request.getFecha().getDayOfWeek();

        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            throw new RuntimeException("No se pueden pedir citas los fines de semana");
        }

        Optional<Cita> citaExistente = citaRepository
                .findByFechaAndHoraAndEstado(
                        request.getFecha(),
                        request.getHora(),
                        EstadoCita.PENDIENTE
                );

        if (citaExistente.isPresent()
                && !citaExistente.get().getIdCita().equals(idCita)) {
            throw new RuntimeException("Ya existe una cita para esa fecha y hora");
        }

        cita.setFecha(request.getFecha());
        cita.setHora(request.getHora());

        citaRepository.save(cita);
    }

}
