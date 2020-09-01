package com.devdenise.devdenisebe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "ArticleResource")
@Table(name = "article_resource", schema = "public")
public class ArticleResource {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column(columnDefinition="TEXT")
    private String resource;

    @ManyToOne
    @JoinColumn( name = "article_id", nullable = false)
    @JsonIgnore
    private Article article;

}
