package com.devdenise.devdenisebe.repository;

import com.devdenise.devdenisebe.model.ArticleCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesOutlinesRepository extends JpaRepository<ArticleCard, Long> {
}
