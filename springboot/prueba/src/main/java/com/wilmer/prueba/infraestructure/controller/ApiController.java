package com.wilmer.prueba.infraestructure.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import com.wilmer.prueba.aplication.service.PersonService;
import com.wilmer.prueba.domain.Person;
import com.wilmer.prueba.domain.Rol;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    private final PersonService personService;//inyeccion de dependencias

    public ApiController(/*@Qualifier("personServicePImpl")*/PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/users")
    public List<Person> findAll(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue = "") String value
    ){

        List<Person> results = personService.findAllByFilter(filter, value);

        return results;
    }

    @GetMapping("/roles")
    public List<Rol> findAllRoles(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue = "") String value
    ){

        List<Rol> results = personService.findAllRolesByFilter(filter, value);

        return results;
    }

     /*@GetMapping("/users")
    public List<Person> findAll(
        @RequestParam(required = false) String language, @RequestParam(required = false) String name) {

        if (name != null && !name.isEmpty()){
            return personService.findAllFilter(name);
        }

        if (language != null && !language.isEmpty()) {
            return personService.findAllFilter(language);
        }
        return personService.findAll();
    }*/


}

/*  otra forma de hacerlo
 @GetMapping("/users")
public List<Person> findAll(
    @RequestParam(required = false) String language,
    @RequestParam(required = false) String name
) {
    List<Person> people = personRepository.findAll();

    if (language != null && !language.isEmpty()) {
        people = people.stream()
            .filter(person -> person.getProgramingLanguage().equalsIgnoreCase(language))
            .collect(Collectors.toList());
    }

    if (name != null && !name.isEmpty()) {
        people = people.stream()
            .filter(person -> person.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    }

    return people;
}

 */



