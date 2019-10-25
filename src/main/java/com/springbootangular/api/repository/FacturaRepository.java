package com.springbootangular.api.repository;

import com.springbootangular.api.domain.Factura;
import org.springframework.data.repository.CrudRepository;

public interface FacturaRepository extends CrudRepository<Factura, Long> {
}
