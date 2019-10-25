package com.springbootangular.api.controller;


import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http:localhost:4200"})
@RestController
@RequestMapping(ClienteController.BASE_URL)
public class FacturaController {

    private ClienteService clienteService;

    public FacturaController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping("/factura/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Factura show(@PathVariable Long id){
        return clienteService.findFacturaById(id);
    }
}
