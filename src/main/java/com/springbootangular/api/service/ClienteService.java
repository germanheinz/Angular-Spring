package com.springbootangular.api.service;

import com.springbootangular.api.v1.model.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();
}
