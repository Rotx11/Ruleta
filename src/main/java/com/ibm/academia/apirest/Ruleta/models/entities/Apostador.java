package com.ibm.academia.apirest.Ruleta.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;
import com.ibm.academia.apirest.Ruleta.models.entities.Ruleta;


@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Setter
@Table(name = "apostadores", schema = "apuesta")
@PrimaryKeyJoinColumn(name = "persona_id")


public class Apostador extends Persona
{

    @Column(name = "ganador")
    private Boolean ganador= false;
    private Double cantidadGanada=0.0;

    @ManyToMany(mappedBy = "apostadores", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "apostadores"})
    private List<Ruleta> ruletas;

    @OneToMany(mappedBy = "apostador", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","apostador"})
    private List<Apuesta> apuestas;
}
