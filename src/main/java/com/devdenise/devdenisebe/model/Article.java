package com.devdenise.devdenisebe.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Article")
@Table(name = "article", schema = "public")
public class Article extends BaseArticle<Article> {


    @Column(columnDefinition="TEXT[]")
    @OneToMany(mappedBy = "article", targetEntity = ArticleSection.class, fetch = FetchType.LAZY)
    private List<String> sections;

    @Column(columnDefinition="TEXT[]")
    @OneToMany(mappedBy = "article", targetEntity = ArticleResource.class, fetch = FetchType.LAZY)
    private List<String> resources;




}
