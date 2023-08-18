package com.mailplug.homework.article.controller;

import com.mailplug.homework.article.dto.ArticleRequestDto;
import com.mailplug.homework.article.entity.Article;
import com.mailplug.homework.article.entity.Board;
import com.mailplug.homework.article.service.ArticleService;
import com.mailplug.homework.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DisplayName("Article Controller 테스트 - POST, PUT, DELETE")
class ArticleControllerTest {
    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        articleController = new ArticleController(articleService);
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    Article article = Article.builder().id(1L).title("title").contents("contents").name(Board.MAIN).userId("userId").build();
    ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목").contents("내용").name(Board.MAIN).build();

    @DisplayName("[POST] 게시글 등록 성공 테스트")
    @Test
    void addArticle() {
        // Given
        Message expectedMessage = new Message("게시글 등록 성공", article);
        when(articleService.addArticle(anyString(), any(ArticleRequestDto.class))).thenReturn(ResponseEntity.ok(expectedMessage));
        // When
        ResponseEntity<Message> response = articleController.addArticle("userId", articleRequestDto);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }

    @DisplayName("[PUT] 게시글 수정 성공 테스트")
    @Test
    void updateArticle() {
        // Given
        Message expectedMessage = new Message("게시글 수정 성공", article);
        when(articleService.updateArticle(anyLong(), anyString(), any(ArticleRequestDto.class))).thenReturn(ResponseEntity.ok(expectedMessage));
        // When
        ResponseEntity<Message> response = articleController.updateArticle(1L, "userId", articleRequestDto);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }

    @DisplayName("[DELETE] 게시글 삭제 성공 테스트")
    @Test
    void deleteArticle() {
        // Given
        Message expectedMessage = new Message("게시글 삭제 성공", null);
        when(articleService.deleteArticle(anyLong(), anyString())).thenReturn(ResponseEntity.ok(expectedMessage));
        // When
        ResponseEntity<Message> response = articleController.deleteArticle(1L, "userId");
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }
}