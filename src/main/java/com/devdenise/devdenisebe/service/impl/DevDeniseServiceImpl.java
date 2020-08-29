package com.devdenise.devdenisebe.service.impl;

import com.devdenise.devdenisebe.model.Article;
import com.devdenise.devdenisebe.model.ArticleCard;
import com.devdenise.devdenisebe.model.Project;
import com.devdenise.devdenisebe.repository.ArticleRepository;
import com.devdenise.devdenisebe.repository.ArticlesOutlinesRepository;
import com.devdenise.devdenisebe.repository.ProjectRepository;
import com.devdenise.devdenisebe.service.DevDeniseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevDeniseServiceImpl implements DevDeniseService {

    private final Logger log = LoggerFactory.getLogger(DevDeniseService.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ArticlesOutlinesRepository articlesOutlinesRepository;


    @Override
    public List<ArticleCard> getArticlesCards() {
        log.info(articlesOutlinesRepository.findAll().toString());
        return articlesOutlinesRepository.findAll();
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
