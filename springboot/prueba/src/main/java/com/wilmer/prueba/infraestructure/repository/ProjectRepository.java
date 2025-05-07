package com.wilmer.prueba.infraestructure.repository;

import com.wilmer.prueba.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;;



public interface ProjectRepository  extends JpaRepository<Project, Long>{

}
