package com.ibm.academia.apirest.Ruleta.services;

import com.ibm.academia.apirest.Ruleta.models.entities.Apostador;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.repositories.ApostadorRepository;
import com.ibm.academia.apirest.Ruleta.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("ApostadorDAOImpl")
public class ApostadorDAOImpl extends PersonaDAOImpl implements ApostadorDAO {

    @Autowired
    public ApostadorDAOImpl(@Qualifier("repositorioApostadores") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarApostadoresPorRuletaId(Integer ruletaId) {
        return ((ApostadorRepository)repository).buscarApostadorporRuletaId(ruletaId);
    }


}
