package com.devdenise.devdenisebe.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project", schema = "public")
public class Project implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column
    private String title;

    @Column
    private String link;

    @Column
    private String imageLink;

    @Column
    private String description;

}
