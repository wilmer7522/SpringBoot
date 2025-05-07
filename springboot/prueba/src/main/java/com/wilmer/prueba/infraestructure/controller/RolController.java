package com.wilmer.prueba.infraestructure.controller;

import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.wilmer.prueba.aplication.service.ProjectService;
import com.wilmer.prueba.aplication.service.RolService;
import com.wilmer.prueba.domain.Rol;
import com.wilmer.prueba.domain.RoleRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RolController {

    
    
    private final RolService rolService;
    

    public RolController(ProjectService projectService, RolService rolService){
        
        this.rolService = rolService;
       
    }

    

    @GetMapping("/roles")
    public List<Rol> findAllRoles(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue = "") String value
    ){

        List<Rol> results = rolService.findAllRolesByFilter(filter, value);

        return results;
    }
    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Rol newRol(@Valid @RequestBody RoleRequest role) {   
        return rolService.createNewRol(role.getName());
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Rol> removeRol(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(rolService.removeRol(id));

        
    }
}

    
    


   

    



    