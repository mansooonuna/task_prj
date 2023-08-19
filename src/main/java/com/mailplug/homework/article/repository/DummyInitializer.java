package com.mailplug.homework.article.repository;

import com.mailplug.homework.article.entity.Article;
import com.mailplug.homework.article.entity.Board;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DummyInitializer {
    private final ArticleRepository articleRepository;

    public DummyInitializer(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostConstruct
    public void init() {
        Article test1 = Article.builder().title("제목1").contents("내용1").userId("testUser").name(Board.FREE).build();
        Article test2 = Article.builder().title("제목2").contents("내용2").userId("testUser").name(Board.FREE).build();
        Article test3 = Article.builder().title("제목3").contents("내용3").userId("testUser").name(Board.FREE).build();
        Article test4 = Article.builder().title("제목4").contents("내용4").userId("testUser").name(Board.FREE).build();
        Article test5 = Article.builder().title("제목5").contents("내용5").userId("testUser").name(Board.FREE).build();

        articleRepository.save(test1);
        articleRepository.save(test2);
        articleRepository.save(test3);
        articleRepository.save(test4);
        articleRepository.save(test5);
    }
}
