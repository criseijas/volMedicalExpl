package com.local.volMedical.controller;


import com.local.volMedical.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional //cuando el metodo realice cambios en la base de datos
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos) {

        var medico = new Medico(datos); // transformamos el DTO en entidad
        repository.save(medico);
    }

    //1-El @RestController con @PostMapping indica que
    // este método va a responder a peticiones POST en la ruta /medicos

    //2-El @RequestBody DatosRegistroMedico datos le dice a Spring:
    //“Tomá el cuerpo del JSON enviado en la request,
    // convertílo en una instancia de DatosRegistroMedico”

    //3-Spring usa Jackson (que está incluido por defecto con Spring Boot)
    // para hacer esta conversión. Esto se llama deserialización JSON → objeto Java.

    //En resumen:
    //Lo que hace que funcione es:
    //@RequestBody: le dice a Spring que lea el body como JSON.
    //Jackson: convierte el JSON en un objeto Java.
    //record DatosRegistroMedico: Jackson mapea los datos JSON a los parámetros del constructor del record por nombre.

    /*@GetMapping
    //Querés devolver una lista de médicos,
    // pero solo mostrando los campos: nombre, email, documento, especialidad (no toda la entidad).
    public List<DatosListaMedico> listar() {

        return repository.findAll()
                .stream()
                .map(DatosListaMedico::new) //idem: .map(medico -> new DatosListaMedico(medico))
                .toList();           //el findAll del repository nos
                                     // devuelve una lista de medicos no el DTO nuevo.
                                     //usamos el stream y el map: para cada item llamamos al constructor.
                                     //// 🔁 Se convierte la entidad Medico al DTO
    }*/

    //Paginación y ordenación
    @GetMapping
    public Page<DatosListaMedico> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        //return repository.findAll(paginacion).map(DatosListaMedico::new); sin modificar x el delete
        return repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
    }

    /*Colocaremos esta paginación dentro del método findAll.
      Si hacemos un control P en findAll, uno de los tipos de parámetros que podemos enviarle
      es pageable. Entonces, le enviaremos nuestra paginación.
      Sin embargo, con esta paginación, el método findAll no nos devuelve una lista.
      Antes, sin paginación, nos devolvía una lista. Con la paginación,
      que es una sobrecarga del método, nos devolverá un Page.
      Un Page, además de contener la lista de médicos, también incluye los datos de paginación.
      Ahora enfrentamos un problema con el método toList. Tendremos que eliminarlo,
      ya que convertía todos los cambios que hemos estado haciendo a lista.
      Además, el Page que nos trae findAll no es compatible con stream.
      Por lo tanto, eliminaremos eso también.
      Sin embargo, el Page sí comprende el método map, por lo que podemos dejarlo así.
      De esta forma, estamos devolviendo un Page.*/

    @Transactional
    @PutMapping
    /*es similar a el post pero no podemos utilizar el DTO DatosRegistroMedico porque
      en ese DTO todos los datos son obligatorios y no coincide con nuestras reglas de negocio para
      el PUT donde indica que solo podemos modificar nombre, teléfono y dirección.
      No podemos modificar correo electrónico, documento ni especialidad. Entonces tendremos que
      crear un nuevo DTO para la actualización*/

    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {

        Medico medico = repository.getReferenceById(datos.id());//obtenemos el medico por el id que fue pasado por el frontend.

        medico.actualizarInformaciones(datos);//creamos el metodo para realizar la actualización

    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        //repository.deleteById(id);//esto elimina completamento el medico
                                  // con ese id de la base de datos. Es una eliminación fisica.

        Medico medico = repository.getReferenceById(id);

        medico.eliminar();//luego de esto hay que hacer unos cambios en listar
                          // xq sino va a seguir apareciendo el medico inactivo.
    }

}



