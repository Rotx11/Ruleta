package com.ibm.academia.apirest.Ruleta.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface GenericoDAO<E>
{
    public Optional<E> buscarPorId (Integer id);
    public E guardar(E entidad);

    //public ResponseEntity<?> guardar(E entidad, BindingResult result);

    public Iterable <E> buscarTodos();
    public void eliminarPorId (Integer id);
    public ResponseEntity<?> validaciones(BindingResult result);
}
