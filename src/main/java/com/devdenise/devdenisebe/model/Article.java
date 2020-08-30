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
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "article_sections", joinColumns = @JoinColumn(name = "article_id"))
    private List<String> sections;

    @Column(columnDefinition = "TEXT[]" )
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "article_resources", joinColumns = @JoinColumn(name = "article_id"))
    private List<String> resources;


}
