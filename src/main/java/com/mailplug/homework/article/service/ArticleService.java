package com.mailplug.homework.article.service;

import com.mailplug.homework.article.dto.ArticleRequestDto;
import com.mailplug.homework.article.entity.Article;
import com.mailplug.homework.article.exception.CustomException;
import com.mailplug.homework.article.repository.ArticleRepository;
import com.mailplug.homework.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.mailplug.homework.article.exception.ErrorCode.ARTICLE_NOT_FOUND;
import static com.mailplug.homework.article.exception.ErrorCode.NOT_AUTHORIZED_USER;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 게시물 목록 조회
    public ResponseEntity<Message> getArticles(Pageable pageable) {
        Page<Article> articleList = articleRepository.findAll(pageable);
        if (articleList.isEmpty()) {
            return new ResponseEntity<>(new Message("게시글 전체 목록 조회 성공 - 게시글 없음", articleList), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("게시글 전체 목록 조회 성공", articleList), HttpStatus.OK);
    }

    // 게시물 단건 조회
    public ResponseEntity<Message> getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomException(ARTICLE_NOT_FOUND)
        );
        return new ResponseEntity<>(new Message("게시글 단건 조회 성공", article), HttpStatus.OK);
    }

    // 게시물 등록
    @Transactional
    public ResponseEntity<Message> addArticle(String userId, ArticleRequestDto requestDto) {
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();

        Article article = Article.builder().title(title).contents(contents).userId(userId).build();
        articleRepository.save(article);
        return new ResponseEntity<>(new Message("게시글 등록 성공", article), HttpStatus.OK);
    }

    // 게시글 수정
    @Transactional
    public ResponseEntity<Message> updateArticle(Long articleId, String userId, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomException(ARTICLE_NOT_FOUND)
        );
        if (!article.getUserId().equals(userId)) throw new CustomException(NOT_AUTHORIZED_USER);

        String title = requestDto.getTitle();
        String contents = requestDto.getContents();
        article.update(title, contents);
        return new ResponseEntity<>(new Message("게시글 수정 성공", article), HttpStatus.OK);
    }

    // 게시글 삭제
    @Transactional
    public ResponseEntity<Message> deleteArticle(Long articleId, String userId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomException(ARTICLE_NOT_FOUND)
        );
        if (!article.getUserId().equals(userId)) throw new CustomException(NOT_AUTHORIZED_USER);
        articleRepository.deleteById(article.getId());
        return new ResponseEntity<>(new Message("게시글 삭제 성공", null), HttpStatus.OK);
    }
}
