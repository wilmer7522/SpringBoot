package com.wilmer.prueba.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilmer.prueba.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameContains(String name);
    List<Person> findByLanguageEquals(String language);
    Optional<Person> findPersonByPassportNumber(String number);
    
} 
