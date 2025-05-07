package com.wilmer.prueba.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Service;
import com.wilmer.prueba.aplication.service.ProjectService;
import com.wilmer.prueba.domain.Project;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }
}
