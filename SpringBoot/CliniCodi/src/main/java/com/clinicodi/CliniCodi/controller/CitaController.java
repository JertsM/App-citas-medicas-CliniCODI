package com.clinicodi.CliniCodi.controller;


import com.clinicodi.CliniCodi.dto.CitaRequest;
import com.clinicodi.CliniCodi.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
