package com.springbootangular.api.service;

import com.springbootangular.api.repository.ClienteRepository;
import com.springbootangular.api.v1.mapper.ClienteMapper;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {


    private ClienteRepository clienteRepository;

    private ClienteMapper clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {

        return clienteRepository
                .findAll()
                .stream()
                .map(customer -> {
                    ClienteDTO clienteDTO = clienteMapper.customerToCustomerDTO(customer);
                    //clienteDTO.setCreateAt(getCreateAt(Cliente.getId()));
                    return clienteDTO;
                })
                .collect(Collectors.toList());
    }
}

