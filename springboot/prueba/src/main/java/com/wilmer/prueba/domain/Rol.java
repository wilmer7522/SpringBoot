package com.wilmer.prueba.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Rol {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)//Indica que e bidireccional
    @JsonManagedReference // marcar el lado que si se serializa
    private List<Person> persons = new ArrayList<>();

    public Rol(){}

    public Rol(long id, String name) {
        this.id = id;
        this.name = name;
    }

    

    
}
