package com.ibm.academia.apirest.Ruleta.repositories;

import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;
import com.ibm.academia.apirest.Ruleta.services.GenericoDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta, Integer> {


}
