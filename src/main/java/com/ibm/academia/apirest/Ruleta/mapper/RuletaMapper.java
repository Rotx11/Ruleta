package com.ibm.academia.apirest.Ruleta.mapper;

import com.ibm.academia.apirest.Ruleta.models.dto.RuletaDTO;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;

public class RuletaMapper
{
    public static RuletaDTO mapRuleta(Ruleta ruleta)
    {
        RuletaDTO ruletaDTO= new RuletaDTO();
        ruletaDTO.setId(ruleta.getId());
        ruletaDTO.setEstadoAbierta(ruleta.getEstadoAbierta());
        return ruletaDTO;

    }
}
