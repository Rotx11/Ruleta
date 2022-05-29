package com.ibm.academia.apirest.Ruleta.repositories;

import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApuestaRepository extends CrudRepository<Apuesta, Integer> {

    @Query("select a from Apuesta a join fetch a.apostador p where p.id = ?1")
    List<Apuesta> buscarApuestasPorApostadorId(Integer apostadorId);
}
