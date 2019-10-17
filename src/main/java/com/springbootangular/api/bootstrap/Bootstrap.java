package com.springbootangular.api.bootstrap;

import com.springbootangular.api.domain.Role;
import com.springbootangular.api.domain.Usuario;
import com.springbootangular.api.repository.ClienteRepository;
import com.springbootangular.api.domain.Cliente;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        customer1.setId(1L);
        customer1.setNombre("Michale");
        customer1.setApellido("Phelps");

        clienteRepository.save(customer1);

        Cliente customer2 = new Cliente();
        customer2.setId(2L);
        customer2.setNombre("Usain");
        customer2.setApellido("Bolt");

        clienteRepository.save(customer2);

        Cliente customer3 = new Cliente();
        customer2.setId(2L);
        customer2.setNombre("Michael");
        customer2.setApellido("Jordan");

        clienteRepository.save(customer3);

        Usuario usuario = new Usuario();
        usuario.setUsername("test");
        usuario.setPassword("$2a$10$cevX0glCYkZ4jyfefyYzNuNmDXXTyiRYCZ0y1nuDnAuGw/5gxjLMi");

        Role roleAdmin = new Role();
        roleAdmin.setId(1l);
        roleAdmin.setNombre("ROLE_ADMIN");
        Role roleUser = new Role();
        roleUser.setId(2l);
        roleUser.setNombre("ROLE_USER");

        List<Role> roles = new ArrayList<Role>();
        roles.add(roleUser);
        roles.add(roleAdmin);

        usuario.setRoles(roles);

        System.out.println("Roles del Usuario Guardado" + usuario.getRoles());
        System.out.println("Customers Loaded: " + clienteRepository.count());
    }
}
