package com.springbootangular.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Double getImporte(){
        return cantidad.doubleValue() * producto.getPrecio();
    }


    private static final long serialVersionUID = 1L;
}
