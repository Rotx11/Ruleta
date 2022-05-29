package com.ibm.academia.apirest.Ruleta.controllers;

import com.ibm.academia.apirest.Ruleta.exceptions.BadRequestException;
import com.ibm.academia.apirest.Ruleta.exceptions.NotFoundException;
import com.ibm.academia.apirest.Ruleta.mapper.RuletaCreacionMapper;
import com.ibm.academia.apirest.Ruleta.mapper.RuletaMapper;
import com.ibm.academia.apirest.Ruleta.models.dto.RuletaCreacionDTO;
import com.ibm.academia.apirest.Ruleta.models.dto.RuletaDTO;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;
import com.ibm.academia.apirest.Ruleta.services.PersonaDAO;
import com.ibm.academia.apirest.Ruleta.services.RuletaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruleta")
public class RuletaController
{
    @Autowired
    private RuletaDAO ruletaDao;
    @Autowired
    @Qualifier("ApostadorDAOImpl")
    private PersonaDAO apostadorDao;

    /**
     * Endpoint que crea una ruleta
     * @return ruletaCreada
     * @Autor: XRMG
     */

    @PostMapping("/creada")
    public ResponseEntity<?> crearRuleta()
    {
        Ruleta ruletaCreada= ruletaDao.crearRuleta();
        return new ResponseEntity<Ruleta>(ruletaCreada, HttpStatus.CREATED);
    }
    /**
     * Endpoint que dado un Id pone el estadoAbierto de la Ruleta en true
     * @param ruletaId
     * @return ruletaAperturada
     * @Autor: XRMG
     */
    @PutMapping ("/apertura/ruleta/{ruletaId}")
    public ResponseEntity <?> AperturaRuleta(@PathVariable(value = "ruletaId")  Integer ruletaId)
    {
        Ruleta ruletaAperturada=  ruletaDao.AperturarRuleta(ruletaId);
        return new ResponseEntity<Ruleta>(ruletaAperturada,HttpStatus.OK);
    }
    /**
     * Endpoint que asocia una ruleta con un apostador
     * @param ruletaId
     * @param apostadorId
     * @return ruletaAsociada
     * @Autor: XRMG
     */
    @PutMapping("apostador/{apostadorId}/ruleta/{ruletaId}")
    public ResponseEntity<?> AsociarRuletaApostador(@PathVariable Integer apostadorId, @PathVariable Integer ruletaId )
    {
        Ruleta ruletaAsociada= ruletaDao.asociarRuletaApostador(apostadorId,ruletaId);
        return new ResponseEntity<Ruleta>(ruletaAsociada, HttpStatus.OK);
    }
    /**
     * Endpoint en donde se juega la ruleta, asgnandosele un numeroGanador y colorGanador y a su vez cerrando las apuestas
     * de esa ruleta
     * @return ruletaJugada con los resultads de las apuestas
     * @Autor: XRMG
     */

    @PutMapping("/jugar/{ruletaId}")
    public ResponseEntity<?> jugarRuleta(@PathVariable Integer ruletaId)
{
    Ruleta ruletaJugada = ruletaDao.jugarRuleta(ruletaId);
    return new ResponseEntity<Ruleta>(ruletaJugada, HttpStatus.OK );
}

    /**
     * Endpoint que dado un id de Ruleta cierra las apuestas de la ruleta
     * @param ruletaId
     * @return ruletaCerrada
     * @Autor: XRMG
     */

    @PutMapping("/cierre/{ruletaId}")
    public ResponseEntity<?> cierreApuestas(@PathVariable Integer ruletaId)
    {
        Ruleta ruletaCerrada = ruletaDao.cerrarApuestas(ruletaId);
        return new ResponseEntity<Ruleta>(ruletaCerrada, HttpStatus.OK );
    }

    /**
     * Endpoint que regresa una lista con las ruletas creadas en donde se muestra los estados de estas
     * @return lista de RuletaDTO
     * @Autor: XRMG
     */
    @GetMapping("/ruletas/estados/dto")
    public ResponseEntity<?> ObtenerRuletasEstadosDTO()
    {
        List<RuletaDTO> listaRuletas = ruletaDao.listaRuletasEstados();
        return new ResponseEntity<List<RuletaDTO>>(listaRuletas, HttpStatus.OK);
    }

}
