package com.wilmer.prueba.domain;


import jakarta.validation.constraints.Size;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoleRequest implements Serializable {
    @NotBlank(message = "El nombre del Rol es Requerido")
    @Size(min = 1, max = 10)
    @NotNull(message = "El Rol no puede ser nulo")
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    


   
    
}
