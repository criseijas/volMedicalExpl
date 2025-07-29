package com.local.volMedical.medico;

public record DatosListaMedico(
        Long id, //agregamos el id para cuando tengamos que hacer la actualización y eliminación
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad
) {
    public DatosListaMedico(Medico medico) {
        // "Usá el constructor principal que ya viene con el record
        // y pasale estos 4 datos que saqué del objeto Medico."
        this(   medico.getId(), //agregamos el id para cuando tengamos que hacer la actualización y eliminación
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getTelefono(),
                medico.getEspecialidad());
    }
}
