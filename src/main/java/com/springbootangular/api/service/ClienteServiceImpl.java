package com.springbootangular.api.service;

import com.springbootangular.api.controller.ClienteController;
import com.springbootangular.api.domain.Cliente;
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
                    clienteDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return clienteDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::customerToCustomerDTO)
                .map(clienteDTO -> {
                    clienteDTO.setCustomerUrl(getCustomerUrl(id));
                    return clienteDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public ClienteDTO save(ClienteDTO clienteDTO) {
        return saveAndReturnDTO(clienteMapper.customerDtoToCustomer(clienteDTO));
    }

    private ClienteDTO saveAndReturnDTO(Cliente cliente) {

        Cliente savedCustomer = clienteRepository.save(cliente);
        ClienteDTO returnDto = clienteMapper.customerToCustomerDTO(savedCustomer);
        returnDto.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnDto;
    }

    @Override
    public ClienteDTO saveCustomerByDTO(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.customerDtoToCustomer(clienteDTO);
        cliente.setId(id);

        return saveAndReturnDTO(cliente);
    }


    @Override
    @Transactional
    public void delete(Long id) {
       clienteRepository.deleteById(id);
    }

    private String getCustomerUrl(Long id) {
        return ClienteController.BASE_URL + "/" + id;
    }
}

