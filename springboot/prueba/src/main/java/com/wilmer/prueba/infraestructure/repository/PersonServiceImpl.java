package com.wilmer.prueba.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import com.wilmer.prueba.aplication.service.PersonService;
import com.wilmer.prueba.domain.Passport;
import com.wilmer.prueba.domain.Person;
import com.wilmer.prueba.domain.Rol;
import com.wilmer.prueba.domain.dto.PersonRequest;
import com.wilmer.prueba.domain.dto.PersonResponse;
import com.wilmer.prueba.infraestructure.error.RolDuplicateException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
// @Primary
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PassportRepository passportRepository;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository, PassportRepository passportRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passportRepository = passportRepository;

    }


    @Override
    public List<PersonResponse> findAllByFilter(String filter, String value) {

        if (filter.toLowerCase().equals("name") && !value.isEmpty()) {
            return personRepository.findByNameContains(value).stream().map((person) -> {
                new PersonResponse();
                PersonResponse response = new PersonResponse();
                response.setName(person.getName());
                response.setSurname(person.getLastName());
                response.setSkill(person.getLanguage());
                response.setPassport(person.getPassport() != null);
                return response;

            }).toList();

        } else if (filter.toLowerCase().equals("language") && !value.isEmpty()) {
            return personRepository.findByLanguageEquals(value).stream().map((person) -> {
                new PersonResponse();
                PersonResponse response = new PersonResponse();
                response.setName(person.getName());
                response.setSurname(person.getLastName());
                response.setSkill(person.getLanguage());
                response.setPassport(person.getPassport() != null);
                return response;

            }).toList();
        }

        return personRepository.findAll().stream().map((person) -> {
            new PersonResponse();
            PersonResponse response = new PersonResponse();
            response.setName(person.getName());
            response.setSurname(person.getLastName());
            response.setSkill(person.getLanguage());
            response.setPassport(person.getPassport() != null);
            return response;

        }).toList();// http://localhost:8080/api/users?filter=language&value=python
                    // http://localhost:8080/api/users?filter=name&value=a
    }

    @Override
    public PersonResponse patchPerson(long id, PersonRequest personDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario solicitado"));

        if (personDto.getName() != null) {
            person.setName(personDto.getName());
        }

        if (personDto.getSurname() != null) {
            person.setLastName(personDto.getSurname());
        }

        if (personDto.getSkill() != null) {
            person.setLanguage(personDto.getSkill());
        }

        personRepository.save(person);

        PersonResponse response = new PersonResponse();
        response.setName(person.getName());
        response.setSurname(person.getLastName());
        response.setSkill(person.getLanguage());
        response.setPassport(person.getPassport() != null);
        return response;

    }

    @Override
    public PersonResponse createNewUser(PersonRequest personDto) {
        Optional<Person> person = personRepository
                .findPersonByPassportNumber(personDto.getPassport());
        // Validamos que el usuario no este registrado
        if (person.isPresent()) {
            throw new RolDuplicateException("El Usuario ya e encuentra registrado", HttpStatus.CONFLICT);
        }
        // Buscamos el rol para el usuario
        Rol userRol = roleRepository.findById(7L)
                .orElseThrow(() -> new EntityNotFoundException("No se encuenta el rol "));

        // Creamos El Usuario temporal
        Person newPerson = new Person();
        newPerson.setName(personDto.getName());
        newPerson.setLastName(personDto.getSurname());
        newPerson.setLanguage(personDto.getSkill());
        newPerson.setRole(userRol);

        // Guardamos Nuestro nuevo registro
        Person save = personRepository.save(newPerson);

        // creamos el Passport Temporal
        Passport passport = new Passport();
        passport.setPerson(save);
        passport.setNumber(personDto.getPassport());

        //Guardar el passport
        passportRepository.save(passport);

        save.setPassport(passport);

        

        // Mapeo de Person a PersonResponse
        PersonResponse response = new PersonResponse();
        response.setName(save.getName());
        response.setSurname(save.getLastName());
        response.setSkill(save.getLanguage());
        response.setPassport(save.getPassport() != null);

        return response;
    }
}
