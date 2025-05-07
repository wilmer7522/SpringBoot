package com.wilmer.prueba.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wilmer.prueba.aplication.service.RolService;
import com.wilmer.prueba.domain.Rol;
import com.wilmer.prueba.infraestructure.error.RolDuplicateException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class RolServiceImpl implements RolService {
    private final RoleRepository roleRepository;

    
    

    public RolServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Rol removeRol(Long id) {
        Optional<Rol> rol = roleRepository.findById(id);

        if (!rol.isPresent()){
            throw new EntityNotFoundException("El rol no se encuentra registrado");// condicion para eliminar que no tenga asociado
        }
        if (!rol.get().getPersons().isEmpty()) {
            throw new EntityNotFoundException("El rol no encuentra asociado con usuarios");
        
        }

        roleRepository.deleteById(id);
        return rol.get();

        
    }

     @Override
    public List<Rol> findAllRolesByFilter(String filter, String value){
        return roleRepository.findAll();
    }

    @Override
    public Rol createNewRol(String name) {
        Rol newRol = new Rol();
        newRol.setName(name);
       // roleRepository.save(newRol);

        if(getRolByName(name).isPresent()){
            throw new RolDuplicateException("El Rol " +name+ " ya esta registrado", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return roleRepository.save(newRol);
    }

    private Optional<Rol> getRolByName(String rolName){
         return roleRepository.findByName(rolName);

    }

}
