package com.ibm.academia.apirest.Ruleta.controllers;

import com.ibm.academia.apirest.Ruleta.exceptions.NotFoundException;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.services.ApuestaDAO;
import com.ibm.academia.apirest.Ruleta.services.PersonaDAO;
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
@RequestMapping("/apuesta")
public class ApuestaController
{
    @Autowired
    private ApuestaDAO apuestaDao;
    @Autowired
    @Qualifier("ApostadorDAOImpl")
    private PersonaDAO apostadorDao;

    /**
     * @Endpoint en donde se crea una apuesta
     * @param apuesta
     * @param result
     * @return apuestaGuardada
     * @Autor: XRMG
     */
    @PostMapping
    public ResponseEntity<?> crearApuesta(@Valid @RequestBody Apuesta apuesta, BindingResult result)
    {
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }
        Apuesta apuestaGuardado= apuestaDao.guardar(apuesta);
        return new ResponseEntity<Apuesta>(apuestaGuardado, HttpStatus.CREATED);
    }

    /**
     * @Endpoint que asocia una apuesta a un apostador
     * @param apostadorId
     * @param apuestaId
     * @return apuestaAsociada
     * @Autor: XRMG
     */

    @PutMapping("/apostador/{apostadorId}/apuesta/{apuestaId}")
    public ResponseEntity<?> AsociarApuestaApostador(@PathVariable Integer apostadorId, @PathVariable Integer apuestaId)
    {
        Optional<Persona> oApostador = apostadorDao.buscarPorId(apostadorId);
        if (!oApostador.isPresent())
            throw new NotFoundException(String.format("El apostador con ID: %d no existe", apostadorId));
        Optional<Apuesta> oApuesta = apuestaDao.buscarPorId(apuestaId);
        if (!oApuesta.isPresent())
            throw new NotFoundException(String.format("La apuesta con ID: %d no existe", apuestaId));
        Apuesta apuestaAsociada= apuestaDao.asociarApostadorApuesta(oApostador.get(),oApuesta.get());
        return new ResponseEntity<Apuesta>(apuestaAsociada, HttpStatus.CREATED);
    }

}
