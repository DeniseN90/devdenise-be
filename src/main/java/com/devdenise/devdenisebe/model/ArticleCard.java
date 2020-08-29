package com.devdenise.devdenisebe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ArticleCard")
@Table(name = "article", schema = "public")
public class ArticleCard extends BaseArticle<ArticleCard>{
}
