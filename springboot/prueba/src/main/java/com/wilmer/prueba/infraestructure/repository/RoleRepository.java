package com.wilmer.prueba.infraestructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilmer.prueba.domain.Rol;

public interface RoleRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByName(String name);


}
