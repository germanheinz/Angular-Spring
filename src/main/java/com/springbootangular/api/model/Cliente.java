package com.gh.springboot.api.model;

import lombok.*;
import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    //La anotacion column va para las diferentes columnas en la tabla
    //pero especificamente para especificar un nombre diferente en que va a mappear en la tabla
    @Column(name="create_at")
    //Anotacion para transformar el valor en un tipo DATE en la tabla
    @Temporal(TemporalType.DATE)
    private Date createAt;

}
