package com.ibm.academia.apirest.Ruleta.models.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "personas", schema = "apuesta")
@JsonTypeInfo(
        use= JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({

        @JsonSubTypes.Type(value = Apostador.class, name = "apostador")
} )
public class Persona implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Ingresa un nombre, este campo no puede ser nulo")
    @NotEmpty(message = "Ingresa un nombre, este campo no puede estar vacio")
    @NotBlank(message = "Ingresa un nombre, los espacios no cuentan")
    @Size(min =3 ,max =60, message = "El nombre debe tener entre 3 y 60 caracteres")
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;
    @NotNull(message = "Ingresa un apellido, este campo no puede ser nulo")
    @NotEmpty(message = "Ingresa un apellido, este campo no debe estar vacio")
    @NotBlank(message = "Ingresa un nombre, no cuentan los espacios")
    @Size(min =3 ,max =60, message = "El apellido debe tener entre 3 y 60 caracteres")
    @Column (name = "apellido", nullable = false, length = 60)
    private String apellido;
    @NotNull(message = "Ingresa un DNI")
    @NotEmpty(message = "Ingresa un DNI")
    @NotBlank(message = "Ingresa un nombre")
    @Size(max =10, message = "El DNI debe tener 10 digitos")
    @Column (name = "dni", nullable = false, unique = true, length = 10)
    private String dni;
}
