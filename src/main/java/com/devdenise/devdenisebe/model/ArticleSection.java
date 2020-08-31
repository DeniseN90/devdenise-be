package com.devdenise.devdenisebe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
    private Long id;

    @Column(columnDefinition="TEXT")
    private String section;

    @ManyToOne
    @JoinColumn( name = "article_id", nullable = false)
    @JsonIgnore
    private Article article;
}
