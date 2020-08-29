package com.devdenise.devdenisebe.repository;

import com.devdenise.devdenisebe.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {


    @Query("SELECT a.title, a.outline FROM Article a")
    List<Article> getArticlesOutlines();





}
