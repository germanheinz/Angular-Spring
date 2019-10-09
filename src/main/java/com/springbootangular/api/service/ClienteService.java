package com.springbootangular.api.service;

import com.springbootangular.api.v1.model.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO findById(Long id);

    ClienteDTO save(ClienteDTO clienteDTO);

    ClienteDTO saveCustomerByDTO(Long id, ClienteDTO customerDTO);

    void delete(Long id);


}
