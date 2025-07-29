package com.local.volMedical.paciente;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(
        @NotNull Long id,
        String nombre,
        String telefono,
        String documento_identidad
) {
}
