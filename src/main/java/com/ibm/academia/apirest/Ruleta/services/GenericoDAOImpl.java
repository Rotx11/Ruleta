package com.ibm.academia.apirest.Ruleta.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenericoDAOImpl <E, R extends CrudRepository<E, Integer>> implements GenericoDAO <E>
{

    protected  final R repository;

    public GenericoDAOImpl(R repository)
    {
        this.repository= repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public E guardar(E entidad) {
        return repository.save(entidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> validaciones(BindingResult result) {
        Map<String, Object> validaciones = new HashMap<String, Object>();

            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);

    }
}
