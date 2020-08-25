package com.devdenise.devdenisebe.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article", schema = "public")
public class Article implements Serializable {


    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id;

    @Column
    private String title;

    @Column
    private String outline;

    @Column(columnDefinition="TEXT")
    private String text;

    @Column(columnDefinition = "TEXT[]")
    @ElementCollection(targetClass=String.class)
    private List<String> resources;
}
