package com.devdenise.devdenisebe.controller;


import com.devdenise.devdenisebe.model.Article;
import com.devdenise.devdenisebe.model.ArticleCard;
import com.devdenise.devdenisebe.model.Project;
import com.devdenise.devdenisebe.service.DevDeniseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("*")
@RestController
public class DevDeniseController {

    @Autowired
    private DevDeniseService devDeniseService;

    private final Logger log = LoggerFactory.getLogger(DevDeniseController.class);


    @Operation(summary = "Retrieves the articles' outline")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Outlines found "),
            @ApiResponse(responseCode = "404", description = "Outlines not found")})
    @GetMapping(value = "get-articles-cards")
    public ResponseEntity<List<ArticleCard>> getArticlesOutline(){
        log.debug("REST request to get articles cards");
        try {
            return ResponseEntity.ok().body(devDeniseService.getArticlesCards());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Articles abstract not found");
        }
    }


    @Operation(summary = "Retrieves article by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article found "),
            @ApiResponse(responseCode = "404", description = "Article not found")})
    @GetMapping(value = "get-article/{id}")
    public ResponseEntity<Article> getArticle(
            @Parameter (description = "need an id to find an article", required = true)
            @PathVariable Long id){
        log.debug("REST request to get article by id");
        //TODO headers
        try {
            return ResponseEntity.ok().body(devDeniseService.getArticle(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Article with id %d not found", id));
        }
    }

    @Operation(summary = "Retrieves all the projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects found"),
            @ApiResponse(responseCode = "404", description = "Projects not found")})
    @GetMapping(value = "get-projects")
    public ResponseEntity<List<Project>> getProjects(){
        log.debug("REST request to get all the projects");
        //TODO headers
        try {
            return ResponseEntity.ok().body(devDeniseService.getProjects());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projects not found");
        }
    }
}
