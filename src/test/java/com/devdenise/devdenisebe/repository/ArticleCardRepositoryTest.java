package com.devdenise.devdenisebe.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ArticleCardRepositoryTest {

    @Autowired
    ArticleCardRepository articleCardRepository;


    @Test
    public void contexLoads() throws Exception {
        assertThat(articleCardRepository).isNotNull();
    }
}
