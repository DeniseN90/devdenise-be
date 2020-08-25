package com.devdenise.devdenisebe.service.impl;

import com.devdenise.devdenisebe.model.Article;
import com.devdenise.devdenisebe.model.Project;
import com.devdenise.devdenisebe.repository.ArticleRepository;
import com.devdenise.devdenisebe.repository.ProjectRepository;
import com.devdenise.devdenisebe.service.DevDeniseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevDeniseServiceImpl implements DevDeniseService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public List<Object> getArticlesOutlines() {
        return articleRepository.getArticlesOutlines();
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }
}
