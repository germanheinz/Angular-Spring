package com.springbootangular.api.service;

import com.springbootangular.api.controller.ClienteController;
import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.domain.Producto;
import com.springbootangular.api.repository.ClienteRepository;
import com.springbootangular.api.repository.FacturaRepository;
import com.springbootangular.api.repository.ProductoRepository;
import com.springbootangular.api.v1.mapper.ClienteMapper;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {


    private ClienteRepository clienteRepository;

    private ClienteMapper clienteMapper;

    private FacturaRepository facturaRepository;

    private ProductoRepository productoRepository;


    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper, FacturaRepository facturaRepository, ProductoRepository productoRepository) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteRepository
                .findAll()
                .stream()
                .map(customer -> {
                    ClienteDTO clienteDTO = clienteMapper.customerToCustomerDTO(customer);
                    //clienteDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return clienteDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
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
        //returnDto.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

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

    @Override
    public Cliente findByIdCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    // ****** Metodos Factura ****** //

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    @Transactional
    public void deleteFacturaById(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Producto> findByNombre(String term) {
        return productoRepository.findByNombre(term);
    }


    private String getCustomerUrl(Long id) {
        return ClienteController.BASE_URL + "/" + id;
    }
}

