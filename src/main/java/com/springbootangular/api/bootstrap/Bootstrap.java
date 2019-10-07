package com.springbootangular.api.bootstrap;

import com.springbootangular.api.repository.ClienteRepository;
import com.springbootangular.api.domain.Cliente;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public Bootstrap(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
    }

    private void loadCustomers() {
        //given
        Cliente customer1 = new Cliente();
        customer1.setId(1l);
        customer1.setNombre("Michale");
        customer1.setApellido("Weston");
        clienteRepository.save(customer1);

        Cliente customer2 = new Cliente();
        customer2.setId(2l);
        customer2.setNombre("Sam");
        customer2.setApellido("Axe");

        clienteRepository.save(customer2);

        System.out.println("Customers Loaded: " + clienteRepository.count());
    }
}
