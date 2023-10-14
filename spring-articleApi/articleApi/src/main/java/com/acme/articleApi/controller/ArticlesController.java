package com.acme.articleApi.controller;

import com.acme.articleApi.dto.ArticleDTO;
import com.acme.articleApi.model.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acme.articleApi.service.ArticleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("articles")
public class ArticlesController {
    //Dependency Injection
    private ArticleService articleService;

    @Autowired
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //Article Controller
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleEntity> articles = articleService.getAll();
        List<ArticleDTO> dtos = articleService.convertEntityListToDTOList(articles);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable("id") Long id) {
        Optional<ArticleEntity> article = articleService.findById(id);

        if (article.isPresent()) {
            ArticleDTO dto = articleService.convertEntityToDTO(article.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO createPayload) {
        ArticleEntity obj = articleService.convertDTOToEntity(createPayload);
        articleService.add(obj);
        ArticleDTO createdArticle = articleService.convertEntityToDTO(obj);
        return ResponseEntity.status(201).body(createdArticle);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateArticle(@PathVariable("id") Long id, @RequestBody ArticleDTO updatePayload) {
        Optional<ArticleEntity> existingArticle = articleService.findById(id);

        if (existingArticle.isPresent()) {
            ArticleEntity obj = articleService.convertDTOToEntity(updatePayload);
            articleService.update(obj, id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        Optional<ArticleEntity> articleToDelete = articleService.findById(id);

        if (articleToDelete.isPresent()) {
            articleService.delete(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}