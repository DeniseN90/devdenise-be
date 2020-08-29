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




    @Column(columnDefinition="TEXT")
    private String text;

    @Column(columnDefinition = "TEXT[]")
    @ElementCollection(targetClass=String.class)
    private List<String> resources;
}
