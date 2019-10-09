package com.springbootangular.api.controller;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.service.ClienteService;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(ClienteController.BASE_URL)
public class ClienteController {

    public static final String BASE_URL = "/api/clientes";

    @Autowired
    public ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> getListCustomers() {
        return clienteService.findAll();
    }
    @GetMapping({"/id"})
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getCustomerById(@PathVariable Long id){
        return clienteService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO createdCustomer(@PathVariable Long Id, @RequestBody ClienteDTO clienteDTO){
        return clienteService.save(clienteDTO);
    }
    @PutMapping({"/id"})
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO updateCustomer(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        return clienteService.saveCustomerByDTO(id, clienteDTO);
    }

}
