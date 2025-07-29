package com.local.volMedical.paciente;

public record DatosListaPacientes(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento_identidad
) {
    public DatosListaPacientes(Paciente paciente) {
        this(   paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento_identidad());
    }
}
