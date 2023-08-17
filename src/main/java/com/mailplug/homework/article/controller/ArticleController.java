package com.mailplug.homework.article.controller;

import com.mailplug.homework.article.dto.ArticleRequestDto;
import com.mailplug.homework.article.service.ArticleService;
import com.mailplug.homework.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    // 게시판에 속한 게시글 목록 조회
    @GetMapping
    public ResponseEntity<Message> getArticlesList(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return articleService.getArticles(pageable);
    }

    // 게시글 단건 조회
    @GetMapping("/{articleId}")
    public ResponseEntity<Message> getArticle(@PathVariable Long articleId) {
        return articleService.getArticle(articleId);
    }

    // 게시글 등록
    @PostMapping("/add")
    public ResponseEntity<Message> addArticle(@RequestHeader(name = "X-USERID") String userId,
                                              @RequestBody ArticleRequestDto requestDto) {
        return articleService.addArticle(userId, requestDto);
    }

    // 게시글 수정
    @PutMapping("/{articleId}")
    public ResponseEntity<Message> updateArticle(@PathVariable Long articleId,
                                                 @RequestHeader(name = "X-USERID") String userId,
                                                 @RequestBody ArticleRequestDto requestDto) {
        return articleService.updateArticle(articleId, userId, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Message> deleteArticle(@PathVariable Long articleId,
                                                 @RequestHeader(name = "X-USERID") String userId) {
        return articleService.deleteArticle(articleId, userId);
    }
}
