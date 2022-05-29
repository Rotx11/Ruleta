package com.ibm.academia.apirest.Ruleta.services;

import com.ibm.academia.apirest.Ruleta.enums.Color;
import com.ibm.academia.apirest.Ruleta.models.dto.RuletaDTO;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RuletaDAO extends GenericoDAO<Ruleta>
{
    public Ruleta crearRuleta();
    public Ruleta cerrarApuestas(Integer ruletaId);
    public Ruleta  AperturarRuleta(Integer ruletaId);
    public Ruleta asociarRuletaApostador(Integer apostadorId, Integer ruletaId);
    public Ruleta jugarRuleta(Integer ruletaId);
    public Color asignarColor(Integer numeroGanador);
    public void asignarGanador(Integer ruletaId, Integer numeroGanador);
    public List<RuletaDTO> listaRuletasEstados();

}
