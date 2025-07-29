package com.local.volMedical.medico;

public record DatosListaMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public DatosListaMedico(Medico medico) {
        // "Usá el constructor principal que ya viene con el record
        // y pasale estos 4 datos que saqué del objeto Medico."
        this(medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}
