package com.springbootangular.api.service;

import com.springbootangular.api.domain.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
}
