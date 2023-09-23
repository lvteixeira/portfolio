package com.acme.articleApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/articles")
public class ArticlesController {
    //Dependency Injection
    private ArticleService articleService;

    @Autowired
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
        //this.article = article;
    }

    //Article Model
    public static class Article {
        private int id;
        private String title;
        private String body;

        public Article() {}

        public Article(int id, String title, String body) {
            this.id = id;
            this.title = title;
            this.body = body;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    //Article Service
    @Service
    public static class ArticleService {
        private ArrayList<Article> articles = new ArrayList<>();
        private int nextId = 1;

        public List<Article> getAll() {
            return articles;
        }

        public Optional<Article> findById(int id) {
            return articles.stream().filter(article -> article.getId() == id).findFirst();
        }

        public void add(Article article) {
            article.setId(nextId++);
            articles.add(article);
        }

        public void update(Article updated, int id) {
            articles.stream()
                    .filter(article -> article.getId() == id)
                    .findFirst()
                    .ifPresent(article -> {
                        article.setTitle(updated.getTitle());
                        article.setBody(updated.getBody());
                    });
        }

        public void delete(int id) {
            int aIndex = id - 1;
            articles.remove(aIndex);
        }

        public void clear(){
            articles.clear();
        }
    }

    //Article Controller
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAll();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") int id) {
        Optional<Article> article = articleService.findById(id);

        if (article.isPresent()) {
            return ResponseEntity.ok(article.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        articleService.add(article);
        return ResponseEntity.ok(article);
    }

    @PutMapping("{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") int id, @RequestBody Article updatedArticle) {
        Optional<Article> existingArticle = articleService.findById(id);

        if (existingArticle.isPresent()) {
            articleService.update(updatedArticle, id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") int id) {
        Optional<Article> articleToDelete = articleService.findById(id);

        if (articleToDelete.isPresent()) {
            articleService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}