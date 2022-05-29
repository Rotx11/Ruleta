package com.ibm.academia.apirest.Ruleta.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.apirest.Ruleta.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.ibm.academia.apirest.Ruleta.models.entities.Apostador;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "ruletas", schema = "apuesta")
public class Ruleta implements Serializable
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
    @NotNull(message = "No púede ser nulo")
    @Column(name = "estado_abierta")
    Boolean estadoAbierta = false;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ruleta_apostador", schema = "apuesta",
            joinColumns = @JoinColumn(name = "ruleta_id"),
            inverseJoinColumns = @JoinColumn(name = "apostador_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "ruletas"})
    private List<Apostador> apostadores=new ArrayList<>();



    /*@PrePersist
    private void antesPersistir()
    {
        this.estadoAbierta=Boolean.FALSE;
    }*/

}
