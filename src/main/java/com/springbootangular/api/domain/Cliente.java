package com.springbootangular.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
    @Column(name="create_at")
    //Anotacion para transformar el valor en un tipo DATE en la tabla
    //@Temporal(TemporalType.DATE)
    private String customerUrl;

    private String foto;

    @JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<>();
    }
}
