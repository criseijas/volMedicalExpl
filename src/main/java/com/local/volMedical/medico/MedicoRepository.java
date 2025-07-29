package com.local.volMedical.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    //con: extends JpaRepository<Medico, Long> tenemos un set de metodos automaticos que
    //incluyen la parte de un CRUD

}
