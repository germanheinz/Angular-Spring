package com.springbootangular.api.service;

import com.springbootangular.api.v1.model.ClienteDTO;
import com.springbootangular.api.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();

    Page<Cliente> findAll(Pageable pageable);

    ClienteDTO findById(Long id);

    ClienteDTO save(ClienteDTO clienteDTO);

    ClienteDTO saveCustomerByDTO(Long id, ClienteDTO customerDTO);

    void delete(Long id);


}
