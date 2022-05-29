package com.ibm.academia.apirest.Ruleta.services;

import com.ibm.academia.apirest.Ruleta.models.entities.Apostador;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;

import java.util.List;

public interface ApostadorDAO extends PersonaDAO {

    //public Persona asociarApostadorApuesta(Persona apostador, List<Apuesta> apuestas);
    public List <Persona> buscarApostadoresPorRuletaId(Integer ruletaId);
}

