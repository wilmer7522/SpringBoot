package com.wilmer.prueba.domain;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "personas")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String lastName;
    @Column(name = "programing_language")
    private String language;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference // marcar el lado que no se serializa
    private Rol role;


    @OneToOne(mappedBy = "person", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Passport passport;

    @ManyToMany
    @JoinTable(name = "personas_project",
    joinColumns = @JoinColumn(name = "persona_id", foreignKey = @ForeignKey(name = "fk_persona_id_projects")),
    inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @JsonBackReference
    private List<Project> projects = new ArrayList<>();

    public Person(){}

    public Person(long id, String name, String lastName, String language) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.language = language;
    }

  


}

    
