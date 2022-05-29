package com.ibm.academia.apirest.Ruleta.mapper;

import com.ibm.academia.apirest.Ruleta.models.dto.RuletaCreacionDTO;
import com.ibm.academia.apirest.Ruleta.models.dto.RuletaDTO;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;

public class RuletaCreacionMapper
{
    public static Ruleta mapRuletaCreacion(RuletaCreacionDTO ruletaCreacionDTO)
    {
        Ruleta ruleta= new Ruleta();
        ruleta.setId(ruletaCreacionDTO.getId());
        ruleta.setEstadoAbierta(ruletaCreacionDTO.getEstadoAbierta());
        return ruleta;
    }
}
