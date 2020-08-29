package com.devdenise.devdenisebe.model;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseArticle<T extends BaseArticle> {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column
    private String title;

    @Column
    private String outline;
}
