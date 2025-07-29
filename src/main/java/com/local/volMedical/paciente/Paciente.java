package com.local.volMedical.paciente;

import com.local.volMedical.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String documento_identidad;
    private String telefono;
    @Embedded
    private Direccion direccion;

    public Paciente(DatosPaciente datos) {

        this.id = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documento_identidad = datos.documento_identidad();
        this.telefono = datos.telefono();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarInformaciones(DatosActualizacionPaciente datos) {

        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }

        if (datos.telefono() != null) {
            this.telefono = datos.telefono();
        }

        if (datos.documento_identidad() != null) {
            this.documento_identidad = datos.documento_identidad();
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
