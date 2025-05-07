package com.wilmer.prueba.infraestructure.controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wilmer.prueba.aplication.service.PersonService;
import com.wilmer.prueba.domain.dto.PersonRequest;
import com.wilmer.prueba.domain.dto.PersonResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final PersonService personService;//inyeccion de dependencias

    public UserController(PersonService personService){
        this.personService = personService;
    }


    @GetMapping("/users")
    public List<PersonResponse> findAll(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue = "") String value
    ){

        List<PersonResponse> results = personService.findAllByFilter(filter, value);

        return results;
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<PersonResponse> partialUpdatePerson(@PathVariable Long id, @RequestBody PersonRequest personDto){

        return ResponseEntity.ok().body(personService.patchPerson(id, personDto));
    }

    @PostMapping("/users")
    public ResponseEntity<PersonResponse> createNewUser(@Valid @RequestBody PersonRequest personDto) {
        return new ResponseEntity<PersonResponse>(
            personService.createNewUser(personDto),
            HttpStatusCode.valueOf(201)
        );
        
        
    }
    

}
