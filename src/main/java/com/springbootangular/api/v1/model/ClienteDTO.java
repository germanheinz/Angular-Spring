package com.springbootangular.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
}
