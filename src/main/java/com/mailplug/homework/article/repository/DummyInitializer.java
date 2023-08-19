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
        for (int i = 1; i <= 10; i++) {
            Article article = Article.builder().title("제목" + i).contents("메인게시판 텍스트 내용" + i).userId("testUser").name(Board.MAIN).build();
            articleRepository.save(article);
        }

        for (int i = 11; i <= 20; i++) {
            Article article = Article.builder().title("제목" + i).contents("자유게시판 텍스트 내용" + i).userId("testUser").name(Board.FREE).build();
            articleRepository.save(article);
        }

        for (int i = 21; i <=30 ; i++) {
            Article article = Article.builder().title("제목" + i).contents("공지게시판 텍스트 내용" + i).userId("testUser").name(Board.NOTICE).build();
            articleRepository.save(article);
        }

    }
}
