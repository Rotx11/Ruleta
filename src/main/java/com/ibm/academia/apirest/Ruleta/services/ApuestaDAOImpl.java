package com.ibm.academia.apirest.Ruleta.services;

import com.ibm.academia.apirest.Ruleta.models.entities.Apostador;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;
import com.ibm.academia.apirest.Ruleta.repositories.ApuestaRepository;
import com.ibm.academia.apirest.Ruleta.repositories.PersonaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApuestaDAOImpl extends GenericoDAOImpl <Apuesta, ApuestaRepository>  implements ApuestaDAO {
    public ApuestaDAOImpl(ApuestaRepository repository) {
        super(repository);
    }

    @Override
    public Apuesta asociarApostadorApuesta(Persona apostador, Apuesta apuestas)
    {
      apuestas.setApostador((Apostador) apostador);
      return repository.save(apuestas);
    }

    @Override
    public List<Apuesta> encontrarApuestasApostadorId(Integer apostadorId) {
        return repository.buscarApuestasPorApostadorId(apostadorId);
    }

    @Override
    public Apuesta crearApuesta(Apuesta apuestaCreada) {

        Apuesta apuestaGuardado= this.guardar(apuestaCreada);
        return apuestaGuardado;
    }
}
