package com.ibm.academia.apirest.Ruleta.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.apirest.Ruleta.enums.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apuestas", schema = "apuesta")
public class Apuesta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    Color color;
    @Min(value = 0, message = "Solo puedes apostar del 0 al 36")
    @Max(value = 36, message = "Solo puedes apostar del 0 al 36")
    @Column(name = "numero")
    Integer numero;

    @Min(value = 1, message = "Puedes apostar desde 1 dolar hasta 10000 dolares")
    @Max(value = 10000, message = "Puedes apostar desde 1 dolar hasta 10000 dolares\"")
    @Column(name = "cantidad_apostar")
    private Double cantidadApostar;

    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "apostador_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "apuestas"})
    private Apostador apostador;
}
