package com.clinicodi.CliniCodi.controller;


import com.clinicodi.CliniCodi.dto.CitaRequest;
import com.clinicodi.CliniCodi.dto.CitaResponse;
import com.clinicodi.CliniCodi.dto.MensajeResponse;
import com.clinicodi.CliniCodi.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCita(
            @RequestBody CitaRequest request
    ) {
        citaService.crearCita(request);
        return ResponseEntity.ok("Cita creada correctamente");
    }

    @GetMapping("/mis-citas")
    public ResponseEntity<List<CitaResponse>> listarCitas(
            @RequestParam String email
    ) {
        System.out.println("Email recibido: " + email);
        return ResponseEntity.ok(
                citaService.obtenerCitasUsuario(email)
        );
    }

    @PutMapping("/cancelar/{idCita}")
    public ResponseEntity<String> cancelarCita(
            @PathVariable Integer idCita,
            @RequestParam String email
    ) {
        citaService.cancelarCita(idCita, email);

        return ResponseEntity.ok("Cita cancelada correctamente");
    }

    @PutMapping("/modificar/{idCita}")
    public ResponseEntity<MensajeResponse> modificarCita(
            @PathVariable Integer idCita,
            @RequestBody CitaRequest request
    ) {
        citaService.modificarCita(idCita, request);

        return ResponseEntity.ok(
                new MensajeResponse("Cita modificada correctamente")
        );
    }

}
