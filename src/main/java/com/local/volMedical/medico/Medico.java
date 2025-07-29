package com.local.volMedical.medico;

import com.local.volMedical.direccion.Direccion;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion; //clase Direccion


    public Medico(DatosRegistroMedico datos) {
        this.id =null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono= datos.telefono();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarInformaciones(@Valid DatosActualizacionMedico datos) {

        if(datos.nombre() != null) {
            this.nombre = datos.nombre();
        }

        if(datos.telefono() != null) {
            this.telefono = datos.telefono();
        }

        if(datos.direccion() != null) {
            this.direccion.actualizarDatos(datos.direccion());
        }

    }

    public void eliminar() {
        this.activo = false;
    }
}
