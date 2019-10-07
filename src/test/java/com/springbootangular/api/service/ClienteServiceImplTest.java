package com.springbootangular.api.service;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.repository.ClienteRepository;
import com.springbootangular.api.v1.mapper.ClienteMapper;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ClienteServiceImplTest {

    //Anotacion Mock para testing
    @Mock
    ClienteRepository clienteRepository;

    ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    ClienteService clienteService;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        clienteService = new ClienteServiceImpl(clienteRepository, clienteMapper);
    }

    @Test
    public void findAll() {
        Cliente c1 = new Cliente();
        c1.setNombre("testNombre");
        c1.setApellido("testApellido");

        Cliente c2 = new Cliente();
        c1.setNombre("testNombre");
        c1.setApellido("testApellido");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(c1,c2));

        List<ClienteDTO> clienteDTO = clienteService.findAll();

        assertEquals(2, clienteDTO.size());

    }
}
