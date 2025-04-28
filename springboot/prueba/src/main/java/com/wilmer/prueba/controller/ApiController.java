package com.wilmer.prueba.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.wilmer.prueba.domain.Person;
import com.wilmer.prueba.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    private final PersonRepository personRepository;//inyeccion de dependencias

    public ApiController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    /*@GetMapping("/users")
    public List<Person> findAll(){

        List<Person> results = personRepository.findByNameContains("a");


        return results;
    }*/

     @GetMapping("/users")
    public List<Person> findAll(@RequestParam(required = false) String language, @RequestParam(required = false) String name) {

        if (name != null && !name.isEmpty()){
            return personRepository.findByNameContains(name);
        }

        if (language != null && !language.isEmpty()) {
            return personRepository.findByLanguageEquals(language);
        }
        return personRepository.findAll();
    }


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



