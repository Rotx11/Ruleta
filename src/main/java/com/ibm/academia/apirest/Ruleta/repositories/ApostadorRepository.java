package com.ibm.academia.apirest.Ruleta.repositories;

import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioApostadores")
public interface ApostadorRepository extends PersonaRepository {

    @Query("select a from Apostador a join fetch a.ruletas r where r.id= ?1")
    List<Persona> buscarApostadorporRuletaId(Integer ruletaId);
}



