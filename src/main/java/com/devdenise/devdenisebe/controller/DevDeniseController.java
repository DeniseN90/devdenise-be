package com.devdenise.devdenisebe.controller;


import com.devdenise.devdenisebe.model.Article;
import com.devdenise.devdenisebe.model.Project;
import com.devdenise.devdenisebe.service.DevDeniseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class DevDeniseController {

    @Autowired
    private DevDeniseService devDeniseService;

    private final Logger log = LoggerFactory.getLogger(DevDeniseController.class);

    @GetMapping("get-abstracts")
    public List<Object> getArticlesOutline(){
        log.debug("REST request to get articles abstracts");
        return devDeniseService.getArticlesOutlines();
    }
    @GetMapping("get-article/{id}")
    public Article getArticle(@PathVariable Long id){
        log.debug("REST request to get article by id");
        return devDeniseService.getArticle(id);
    }


    @GetMapping("get-projects}")
    public List<Project> getProjects(){
        log.debug("REST request to get projects");
        return devDeniseService.getProjects();
    }
}
