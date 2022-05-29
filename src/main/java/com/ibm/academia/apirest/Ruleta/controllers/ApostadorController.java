package com.ibm.academia.apirest.Ruleta.controllers;

import com.ibm.academia.apirest.Ruleta.exceptions.NotFoundException;
import com.ibm.academia.apirest.Ruleta.models.entities.Apostador;
import com.ibm.academia.apirest.Ruleta.models.entities.Apuesta;
import com.ibm.academia.apirest.Ruleta.models.entities.Persona;
import com.ibm.academia.apirest.Ruleta.services.ApostadorDAO;
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
@RequestMapping("/apostador")
public class ApostadorController
{
    @Autowired
    @Qualifier("ApostadorDAOImpl")
    private PersonaDAO apostadorDao;

    @Autowired
    private ApuestaDAO apuestaDao;

    /**
     * @Endpoint en donde se crea un apostador
     * @param apostador
     * @param result
     * @return apostadorGuardado
     * @Autor: XRMG
     */
    @PostMapping
    public ResponseEntity<?> crearApostador(@Valid @RequestBody Persona apostador, BindingResult result)
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
        Persona apostadorGuardado= apostadorDao.guardar(apostador);
        return new ResponseEntity<Persona>(apostadorGuardado, HttpStatus.CREATED);
    }

}
