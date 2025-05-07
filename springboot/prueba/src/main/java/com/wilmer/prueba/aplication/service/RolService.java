package com.wilmer.prueba.aplication.service;

import java.util.List;

import com.wilmer.prueba.domain.Rol;

public interface RolService {

        public List<Rol> findAllRolesByFilter(String filter, String Value);
    public Rol createNewRol(String name);
    public Rol removeRol(Long id);
}
