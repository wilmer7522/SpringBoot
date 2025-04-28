package com.wilmer.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilmer.prueba.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameContains(String name);
    List<Person> findByLanguageEquals(String language);
    
} 
