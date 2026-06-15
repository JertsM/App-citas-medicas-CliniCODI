package com.clinicodi.CliniCodi.controller;


import com.clinicodi.CliniCodi.dto.CrearCitaRequest;
import com.clinicodi.CliniCodi.entidades.Cita;
import com.clinicodi.CliniCodi.repository.UserRepository;
import com.clinicodi.CliniCodi.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody CrearCitaRequest request){
        try {
            Cita cita = citaService.crearCita(request);
            return ResponseEntity.ok().body(cita);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
