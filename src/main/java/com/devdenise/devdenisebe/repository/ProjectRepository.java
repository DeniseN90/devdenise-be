package com.devdenise.devdenisebe.repository;

import com.devdenise.devdenisebe.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    List<Project> findAll();
}
