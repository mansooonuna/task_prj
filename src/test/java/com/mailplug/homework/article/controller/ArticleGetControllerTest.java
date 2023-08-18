package com.mailplug.homework.article.controller;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Article Controller 테스트 - GET")
class ArticleGetControllerTest {
    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    Article article = Article.builder().id(1L).title("title").contents("contents").name(Board.MAIN).userId("userId").build();

    @DisplayName("[GET] 게시글 목록 조회 성공 테스트")
    @Test
    void getArticlesList() throws Exception {
        // Given
        List<Article> articleList = new ArrayList<>();
        Message expectedMessage = new Message("게시글 목록 조회 성공 - 게시글 없음", articleList);

        when(articleService.getArticles(any(Board.class), any(Pageable.class), anyString())).thenReturn(ResponseEntity.ok(expectedMessage));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/articles")
                        .param("name", "MAIN")
                        .param("searchKeyword", "키워드")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(expectedMessage.getMessage()));

        // Then
        verify(articleService, times(1)).getArticles(eq(Board.MAIN), any(Pageable.class), eq("키워드"));
    }


    @DisplayName("[GET] 게시글 단건 조회 성공 테스트")
    @Test
    void getArticle() throws Exception {
        // Given
        Message expectedMessage = new Message("게시글 단건 조회 성공", article);
        when(articleService.getArticle(anyLong())).thenReturn(ResponseEntity.ok(expectedMessage));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(expectedMessage.getMessage()));

        // Then
        verify(articleService, times(1)).getArticle(article.getId());
    }


}