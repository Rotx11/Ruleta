package com.ibm.academia.apirest.Ruleta.services;

import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaDAOImpl  extends GenericoDAOImpl <Persona, PersonaRepository>  implements PersonaDAO
{


    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }
}
