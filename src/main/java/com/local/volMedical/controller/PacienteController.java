package com.local.volMedical.controller;

import com.local.volMedical.paciente.DatosPaciente;
import com.local.volMedical.paciente.Paciente;
import com.local.volMedical.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Transactional  //cuando el metodo realice cambios en la base de datos
    @PostMapping
    public void registrar(@RequestBody @Valid DatosPaciente datos) {

        var paciente = new Paciente(datos);
        repository.save(paciente);
    }
}
