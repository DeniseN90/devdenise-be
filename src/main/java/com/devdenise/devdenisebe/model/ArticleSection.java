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
@Entity(name = "ArticleSection")
@Table(name = "article_section", schema = "public")
public class ArticleSection {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column(columnDefinition="TEXT")
    private String section;

    @ManyToOne
    @JoinColumn( name = "article_id", nullable = false)
    @JsonIgnore
    private Article article;
}
