package com.local.volMedical.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //con: extends JpaRepository<Medico, Long> tenemos un set de metodos automaticos que
    //incluyen la parte de un CRUD

    Page<Medico> findAllByActivoTrue(Pageable paginacion);



}
