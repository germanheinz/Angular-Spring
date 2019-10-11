package com.springbootangular.api.controller;


import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.service.ClienteService;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping({"page/{page}"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Cliente> getListCustomers(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return clienteService.findAll(pageable);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getCustomerById(@PathVariable Long id) {
        return clienteService.findById(id);

    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    //public ClienteDTO createdCustomer(@PathVariable Long Id, @RequestBody ClienteDTO clienteDTO){
    public ResponseEntity<?> createdCustomer(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result) {

        ClienteDTO clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo" + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            //return clienteService.save(clienteDTO);
            clienteService.save(clienteDTO);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se ha creado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
/*
* Para validar todas las anotaciones en el modelo Entity Clientes se debe anotar @Valid seguido de @RequestBody + el objeto

* Despues BindingResult result nos dira los errores que hubieron
*
* se hace un condicional para obtenerlos en caso de que hubiesen
* * */
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id, BindingResult result) {

        ClienteDTO cliente = clienteService.findById(id);

        ClienteDTO clienteUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (cliente == null) {
            response.put("mensaje", "Error el cliente con el id".concat(id.toString().concat("no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo" + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            clienteUpdated = clienteService.saveCustomerByDTO(id, clienteDTO);
        } catch (DataAccessException e) {
            response.put("mensaje", "Erorr al actualizar el cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente actualizado con exito");
        response.put("cliente", clienteUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clienteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el cliente de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Todo ok");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
