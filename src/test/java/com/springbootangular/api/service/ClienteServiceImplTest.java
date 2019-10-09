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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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

    @Test
    public void findById() {
        Cliente c1 = new Cliente();
        c1.setId(1L);
        c1.setNombre("Michael");
        c1.setApellido("Jordan");

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(c1));

        ClienteDTO clienteDTO = clienteService.findById(1L);

        assertEquals("Michael", clienteDTO.getNombre());

    }

    @Test
    public void save() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Michael");
        clienteDTO.setApellido("Jordan");

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setId(23L);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO savedDTO = clienteService.save(clienteDTO);

        assertEquals(cliente.getNombre(), savedDTO.getNombre());
        assertEquals("/api/clientes/23", savedDTO.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Michael");
        clienteDTO.setApellido("Jordan");

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setId(23L);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO clienteDTOSaved = clienteService.saveCustomerByDTO(23L, clienteDTO);

        assertEquals(cliente.getNombre(), clienteDTOSaved.getNombre());
        assertEquals("/api/clientes/23", clienteDTOSaved.getCustomerUrl());
    }

    @Test
    public void delete() {
        Long id = 23L;

        clienteRepository.deleteById(23L);
        verify(clienteRepository, times(1)).deleteById(anyLong());
    }
}
