package com.springbootangular.api.controller;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.service.ClienteService;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public List<ClienteDTO> index() {
        return clienteService.findAll();
    }


}
