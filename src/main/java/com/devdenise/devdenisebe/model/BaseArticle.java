package com.devdenise.devdenisebe.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseArticle<T extends BaseArticle> {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column
    private String title;

    @Column
    private String outline;
}
