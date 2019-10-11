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
    @NotEmpty
    @Size(min=3, max=12)
    private String nombre;
    @NotEmpty
    private String apellido;

    //@JsonProperty("customer_url")
    private String customerUrl;
}
