package com.acme.articleApi.service;

import com.acme.articleApi.dto.ArticleDTO;
import com.acme.articleApi.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.articleApi.model.ArticleEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;

        //POST e PUT endpoints recebem payload sem ID, que deve ser ignorado pelo metodo de conversao DTO -> Entity
        //caso contrario eh populado como null, o que pode causar comportamento inesperado.

        //TypeMap personalizado para DTO -> Entity
        TypeMap<ArticleDTO, ArticleEntity> typeMap = this.modelMapper.createTypeMap(ArticleDTO.class, ArticleEntity.class);

        //Ignora o mapeamento do campo "id"
        typeMap.addMappings(mapper -> mapper.skip(ArticleEntity::setId));
    }

    public ArticleDTO convertEntityToDTO(ArticleEntity entity){
        return modelMapper.map(entity, ArticleDTO.class);
    }

    public ArticleEntity convertDTOToEntity(ArticleDTO dto) {
        return modelMapper.map(dto, ArticleEntity.class);
    }

    public List<ArticleDTO> convertEntityListToDTOList(List<ArticleEntity> entityList) {
        java.lang.reflect.Type targetListType = new TypeToken<List<ArticleDTO>>() {}.getType();
        return modelMapper.map(entityList, targetListType);
    }

    public List<ArticleEntity> getAll() {
        return articleRepository.findAll();
    }

    public Optional<ArticleEntity> findById(Long id) {
        return articleRepository.findById(id);
    }

    public void add(ArticleEntity article) {
        articleRepository.save(article);
        articleRepository.flush();
    }

    public void update(ArticleEntity updated, Long id) {
        List<ArticleEntity> articles = this.getAll();
        articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .ifPresent(article -> {
                    article.setTitle(updated.getTitle());
                    article.setBody(updated.getBody());
                    articleRepository.flush();
                });
    }

    public void delete(Long id) {
        Optional<ArticleEntity> articleToDelete = this.findById(id);
        articleToDelete.ifPresent(article -> articleRepository.deleteById(id));
        articleRepository.flush();
    }

    public void clear() {
        articleRepository.deleteAll();
        articleRepository.flush();
    }
}
