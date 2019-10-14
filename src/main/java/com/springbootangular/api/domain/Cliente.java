package com.springbootangular.api.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//Anotacion de lombock, simplifica clase, sin tener que visualizar setters y getters
@Data
//Anotacion para marcar que la clase va a ser una Entidad en la tabla
@Entity
//Anotacion usada en caso de que la tabla se quiera llamar diferente al de la clase
@Table(name="clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    //La anotacion column va para las diferentes columnas en la tabla
    //pero especificamente para especificar un nombre diferente en que va a mappear en la tabla
    @Column(name="customer_url")
    //Anotacion para transformar el valor en un tipo DATE en la tabla
    //@Temporal(TemporalType.DATE)
    private String customerUrl;

    private String foto;

}
