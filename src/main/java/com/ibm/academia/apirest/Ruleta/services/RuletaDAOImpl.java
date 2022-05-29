package com.ibm.academia.apirest.Ruleta.services;

import com.ibm.academia.apirest.Ruleta.enums.Color;
import com.ibm.academia.apirest.Ruleta.exceptions.BadRequestException;
import com.ibm.academia.apirest.Ruleta.exceptions.NotFoundException;
import com.ibm.academia.apirest.Ruleta.mapper.RuletaMapper;
import com.ibm.academia.apirest.Ruleta.models.dto.RuletaDTO;
import com.ibm.academia.apirest.Ruleta.models.entities.Apostador;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;
import com.ibm.academia.apirest.Ruleta.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class RuletaDAOImpl extends GenericoDAOImpl<Ruleta,RuletaRepository> implements RuletaDAO {
    public RuletaDAOImpl(RuletaRepository repository) {
        super(repository);
    }

    @Autowired
    @Qualifier("ApostadorDAOImpl")
    private PersonaDAO apostadorDao;
    @Autowired
    private ApuestaDAO apuestaDao;

    @Override
    public Ruleta crearRuleta() {
        Ruleta ruletaCreada=new Ruleta();
        ruletaCreada.setEstadoAbierta(false);
        this.guardar(ruletaCreada);
        return ruletaCreada;
    }

    @Override
    @Transactional
    public Ruleta cerrarApuestas(Integer ruletaId) {
        Ruleta ruletaCerrada=null;
        Ruleta ruleta = this.buscarPorId(ruletaId)
                .orElseThrow(()-> new NotFoundException(String.format("La ruleta con ID: %d no existe", ruletaId)));
        ruleta.setEstadoAbierta(Boolean.FALSE);
        ruletaCerrada = repository.save(ruleta);
        return ruletaCerrada;
    }

    @Override
    @Transactional
    public Ruleta AperturarRuleta(Integer ruletaId) {
         Ruleta ruleta = this.buscarPorId(ruletaId)
                .orElseThrow(()-> new NotFoundException(String.format("La ruleta con ID: %d no existe", ruletaId)));
        Ruleta ruletaAbierta= null;
        ruleta.setEstadoAbierta(Boolean.TRUE);
        ruletaAbierta= this.guardar(ruleta);
        return ruletaAbierta;
    }

    @Override
    @Transactional
    public Ruleta asociarRuletaApostador(Integer apostadorId, Integer ruletaId) {
        Persona apostador = apostadorDao.buscarPorId(apostadorId)
                .orElseThrow(()-> new NotFoundException(String.format("El apostador con ID: %d no existe", apostadorId)));
        Ruleta ruleta = this.buscarPorId(ruletaId)
                .orElseThrow(()-> new NotFoundException(String.format("La ruleta con ID: %d no existe", ruletaId)));
       ruleta.getApostadores().add((Apostador) apostador);
        return repository.save(ruleta);
    }

    @Override
    public Ruleta jugarRuleta(Integer ruletaId) {
        Integer numeroMinimo=0;
        Integer numeroMaximo=36;
        Random r= new Random();
        Ruleta ruleta= this.buscarPorId(ruletaId)
                .orElseThrow(()-> new NotFoundException(String.format("La ruleta con ID: %d no existe", ruletaId)));
        if (ruleta.getEstadoAbierta()!= true)
            new BadRequestException(String.format("La ruleta con ID: %d  no esta abierta", ruletaId));
        Integer numeroGanador= r.nextInt((numeroMaximo + 1) - numeroMinimo) + numeroMinimo;
        Color colorGanador = asignarColor(numeroGanador);
        ruleta.setNumero(numeroGanador);
        ruleta.setColor(colorGanador);
        asignarGanador(ruletaId, numeroGanador);
        repository.save(ruleta);
        this.cerrarApuestas(ruletaId);
        return ruleta;
    }

    @Override
    public Color asignarColor(Integer numeroGanador) {
        return switch(numeroGanador){
            case 0 -> Color.VERDE;
            case 1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36 -> Color.ROJO;
            case 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35-> Color.NEGRO;
            default -> null;
        };

    }

    @Override
    public void asignarGanador(Integer ruletaId, Integer numeroGanador) {
        List<Persona> apostadoresPorRuleta = ((ApostadorDAO)apostadorDao).buscarApostadoresPorRuletaId(ruletaId);
        List<Apuesta> apuestasDeApostador= new ArrayList<>();

        for(Persona apostador:apostadoresPorRuleta)
        {
            for (Apuesta apuesta:apuestasDeApostador= apuestaDao.encontrarApuestasApostadorId(apostador.getId()))
            {
                if (((Apostador)apostador).getGanador() != true)
                {
                    if ((apuesta.getNumero() != null) && (apuesta.getNumero() == numeroGanador)) {
                        ((Apostador) apostador).setGanador(true);
                        ((Apostador) apostador).setCantidadGanada(apuesta.getCantidadApostar());
                    }
                    if ((apuesta.getColor() != null) && (apuesta.getColor() == asignarColor(numeroGanador)) && (((Apostador)apostador).getGanador()!= true) ) {
                        ((Apostador) apostador).setGanador(true);
                        ((Apostador) apostador).setCantidadGanada(apuesta.getCantidadApostar());
                    }
                    apostadorDao.guardar((Apostador)apostador);
                }
            }


        }





    }

    @Override
    public List<RuletaDTO>  listaRuletasEstados() {
        List<Ruleta> ruletas = (List<Ruleta>)this.buscarTodos();
        if(ruletas.isEmpty())
            throw new BadRequestException("No existen ruletas");
        List<RuletaDTO> listaRuletas = ruletas
                .stream()
                .map(RuletaMapper::mapRuleta)
                .collect(Collectors.toList());
        return listaRuletas;
    }


}
