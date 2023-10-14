package com.acme.articleApi.model;

import jakarta.persistence.*;


@Entity
@Table(name="articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="articleTitle", length=50, nullable=false, unique=false)
    private String title;
    @Column(name="articleBody", length=1000000, nullable=true, unique=false)
    private String body;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }
}
