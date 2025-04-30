package com.wilmer.prueba.aplication.service;

import java.util.List;

import com.wilmer.prueba.domain.Person;
import com.wilmer.prueba.domain.Rol;



public interface PersonService {

    public List<Person> findAllByFilter(String filter, String value);
    public List<Rol> findAllRolesByFilter(String filter, String Value);

}




