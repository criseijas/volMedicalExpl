package com.local.volMedical.paciente;

import com.local.volMedical.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosPaciente(
                            @NotBlank String nombre,
                            @NotBlank String email,
                            @NotBlank String documento_identidad,
                            @NotBlank String telefono,
                            @NotNull @Valid DatosDireccion direccion) {
}
