package com.mailplug.homework.article.service;

import com.mailplug.homework.article.entity.Article;
import com.mailplug.homework.article.entity.Board;
import com.mailplug.homework.article.exception.CustomException;
import com.mailplug.homework.article.repository.ArticleRepository;
import com.mailplug.homework.util.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mailplug.homework.article.exception.ErrorCode.ARTICLE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Article Service 테스트 - GET")
class ArticleGetServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    Article article = Article.builder().id(1L).title("title").contents("contents").name(Board.MAIN).userId("userId").build();
    Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createAt");

    @Nested
    @DisplayName("[GET] 게시글 목록 조회 성공 테스트")
    class getArticlesTest {

        @DisplayName("검색어를 입력하면 해당 검색어를 포함한 게시글을 반환한다.")
        @Test
        void getArticles_hasSearchKeyword() {
            // Given & When
            ResponseEntity<Message> response = articleService.getArticles(Board.MAIN, pageable, "키워드");

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 목록 조회 성공 - 게시글 없음", response.getBody().getMessage());
        }

        @DisplayName("검색어를 입력하지 않으면 모든 게시글을 반환한다.")
        @Test
        void getArticles_hasNotSearchKeyword() {
            // Given
            List<Article> articleList = new ArrayList<>();
            when(articleRepository.findAllByBoard(eq(Board.MAIN), eq(pageable))).thenReturn(articleList);

            // When
            ResponseEntity<Message> response = articleService.getArticles(Board.MAIN, pageable, null);

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 목록 조회 성공 - 게시글 없음", response.getBody().getMessage());
        }

        @DisplayName("게시판을 선택하지 않으면 기본 게시판을 조회한다.")
        @Test
        void getArticles_hasNotBoard() {
            // Given
            List<Article> articleList = new ArrayList<>();
            articleList.add(article);
            when(articleRepository.findAllByBoard(eq(Board.MAIN), eq(pageable))).thenReturn(articleList);

            // When
            ResponseEntity<Message> response = articleService.getArticles(null, pageable, null);

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 목록 조회 성공", response.getBody().getMessage());
        }
    }

    @Nested
    @DisplayName("[GET] 게시글 단건 조회 성공 테스트")
    class getArticleTest_success {

        @DisplayName("해당 게시글을 반환한다.")
        @Test
        void getArticle() {
            // Given
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            ResponseEntity<Message> response = articleService.getArticle(article.getId());

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 단건 조회 성공", response.getBody().getMessage());
        }

    }

    @Nested
    @DisplayName("[GET] 게시글 단건 조회 실패 테스트")
    class getArticleTest_fail {

        @DisplayName("해당 게시글이 DB에 존재하지 않는다.")
        @Test
        void getArticle() {
            // Given
            when(articleRepository.findById(anyLong())).thenReturn(Optional.empty());

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.getArticle(article.getId()));

            // Then
            assertEquals(exception.getErrorCode(), ARTICLE_NOT_FOUND);
        }
    }
}