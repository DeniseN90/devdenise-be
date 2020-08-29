package com.devdenise.devdenisebe.service;

import com.devdenise.devdenisebe.model.Article;
import com.devdenise.devdenisebe.model.ArticleCard;
import com.devdenise.devdenisebe.model.Project;

import java.util.List;

public interface DevDeniseService {

    List<ArticleCard> getArticlesCards();

    Article getArticle(Long id);

    List<Project> getProjects();

    
}
