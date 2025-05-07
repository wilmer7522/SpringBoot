package com.wilmer.prueba.aplication.service;

import java.util.List;

import com.wilmer.prueba.domain.dto.PersonRequest;
import com.wilmer.prueba.domain.dto.PersonResponse;




public interface PersonService {

    public List<PersonResponse> findAllByFilter(String filter, String value);
    public PersonResponse patchPerson(long id, PersonRequest personDto);
    public PersonResponse createNewUser(PersonRequest personDto);
    
    

}




