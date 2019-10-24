package com.springbootangular.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootangular.api.domain.Factura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    //Esta anotacion con mensaje es para manejar los errores y que sean en spanish
    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(min=3, max=12, message = "Debe ser entre 4 y 12")
    private String nombre;
    @NotEmpty(message = "El campo no puede estar vacio")
    private String apellido;

    //@JsonProperty("customer_url")
    private String customerUrl;

    private String foto;

    @JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;
}
