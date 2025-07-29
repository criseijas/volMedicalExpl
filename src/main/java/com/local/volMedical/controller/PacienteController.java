package com.local.volMedical.controller;

import com.local.volMedical.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping
    public Page<DatosListaPacientes> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion)
                .map(paciente -> new DatosListaPacientes(paciente));
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {

        Paciente paciente = repository.getReferenceById(datos.id());

        paciente.actualizarInformaciones(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {

        Paciente paciente = repository.getReferenceById(id);

        paciente.eliminar();

    }
}
