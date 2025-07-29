package com.local.volMedical.medico;

import com.local.volMedical.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;


public record DatosActualizacionMedico(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion //usamos el DTO porque tiene algunas validaciones y nos sirve
                                 //si tuvieramos alguna regla de negocio en la direccion tendriamos
                                 //que crear un nuevo DTO

) {
}
