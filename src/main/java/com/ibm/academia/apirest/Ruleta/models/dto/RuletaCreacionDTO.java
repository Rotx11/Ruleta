package com.ibm.academia.apirest.Ruleta.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.academia.apirest.Ruleta.enums.Color;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
public class RuletaCreacionDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    Color color;
    @Column(name = "numero")
    @JsonIgnore
    Integer numero;
    @Column(name = "estado_abierta")
    @NotNull
    Boolean estadoAbierta;
    @Column(name = "valor_apertura")
    @JsonIgnore
    Double valorApertura;


}
