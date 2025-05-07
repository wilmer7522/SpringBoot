package com.wilmer.prueba.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilmer.prueba.domain.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {

}
