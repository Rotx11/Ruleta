package com.ibm.academia.apirest.Ruleta.repositories;

import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.criteria.CriteriaBuilder;
@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona, Integer>
{
}
