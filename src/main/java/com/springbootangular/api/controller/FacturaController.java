package com.springbootangular.api.controller;


import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.domain.Producto;
import com.springbootangular.api.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("factura/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clienteService.delete(id);
    }
     @GetMapping("/factura/filter-productos/{term}")
    public List<Producto> filtrarProductos(@PathVariable String term){
        return clienteService.findByNombre(term);
     }

     @PostMapping("/facturas")
     @ResponseStatus(HttpStatus.CREATED)
     public Factura crear(@RequestBody Factura factura){
        return clienteService.saveFactura(factura);
     }
}
