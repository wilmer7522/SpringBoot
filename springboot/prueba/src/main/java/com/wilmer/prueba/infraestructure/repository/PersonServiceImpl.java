package com.wilmer.prueba.infraestructure.repository;

import java.util.List;

import com.wilmer.prueba.aplication.service.PersonService;
import com.wilmer.prueba.domain.Person;
import com.wilmer.prueba.domain.Rol;

//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;



@Service
//@Primary
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository){
            this.personRepository = personRepository;
            this.roleRepository = roleRepository;
    }

    @Override
    public List<Person> findAllByFilter(String filter, String value){

        if (filter.toLowerCase().equals("name") && !value.isEmpty()){
           return personRepository.findByNameContains(value);
        }else if (filter.toLowerCase().equals("language") && !value.isEmpty()){
            return personRepository.findByLanguageEquals(value);
        }
        
        return personRepository.findAll();// http://localhost:8080/api/users?filter=language&value=python  http://localhost:8080/api/users?filter=name&value=a
    }

    @Override
    public List<Rol> findAllRolesByFilter(String filter, String value){
        return roleRepository.findAll();
    }

    
}
