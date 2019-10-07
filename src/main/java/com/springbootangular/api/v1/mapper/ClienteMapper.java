package com.springbootangular.api.v1.mapper;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO customerToCustomerDTO(Cliente cliente);

    Cliente customerDtoToCustomer(ClienteDTO customerDTO);
}
