package com.springbootangular.api.dao;

import com.springbootangular.api.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Long> {
    Iterable<Cliente> findAll();
}
