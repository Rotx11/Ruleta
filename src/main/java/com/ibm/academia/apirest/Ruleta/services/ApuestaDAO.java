package com.ibm.academia.apirest.Ruleta.services;

import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ApuestaDAO extends GenericoDAO<Apuesta>
{
    public Apuesta asociarApostadorApuesta(Persona apostador, Apuesta apuestas);
    public List <Apuesta> encontrarApuestasApostadorId(Integer apostadorId);
    public Apuesta crearApuesta(Apuesta apuestaCreada);
}
