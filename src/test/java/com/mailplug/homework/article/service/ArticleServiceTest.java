package com.mailplug.homework.article.service;

import com.mailplug.homework.article.dto.ArticleRequestDto;
import com.mailplug.homework.article.entity.Article;
import com.mailplug.homework.article.entity.Board;
import com.mailplug.homework.exception.CustomException;
import com.mailplug.homework.article.repository.ArticleRepository;
import com.mailplug.homework.util.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.mailplug.homework.exception.ErrorCode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Article Service 테스트 - POST, PUT, DELETE")
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    Article article = Article.builder().id(1L).title("title").contents("contents").name(Board.MAIN).userId("testUser").build();

    @Nested
    @DisplayName("게시물 등록 성공 테스트")
    class addArticleTest_success {

        @DisplayName("게시글을 정상적으로 등록한다.")
        @Test
        void addArticle() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목").contents("내용").name(Board.FREE).build();

            // When
            ResponseEntity<Message> response = articleService.addArticle("testUser", articleRequestDto);

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 등록 성공", response.getBody().getMessage());
        }

        @DisplayName("게시글 작성 시 게시판을 선택하지 않으면 기본 게시판을 선택한다.")
        @Test
        void addArticle_mainBoard() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목").contents("내용").name(null).build();

            // When
            ResponseEntity<Message> response = articleService.addArticle("testUser", articleRequestDto);

            // Then
            Article article = (Article) response.getBody().getData();
            assertEquals(Board.MAIN, article.getName());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 등록 성공", response.getBody().getMessage());

        }
    }


    @Nested
    @DisplayName("게시물 등록 실패 테스트")
    class addArticleTest_fail {

        @DisplayName("등록하려는 게시글의 제목이 없다. (null)")
        @Test
        void addArticle_hasNotTitle() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().contents("내용").name(Board.MAIN).build();

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.addArticle("testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_TITLE);
        }

        @DisplayName("등록하려는 게시글의 제목이 없다. (no String)")
        @Test
        void addArticle_hasNotTitle_noString() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("").contents("내용").name(Board.MAIN).build();

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.addArticle("testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_TITLE);
        }

        @DisplayName("등록하려는 게시글의 내용이 없다. (null)")
        @Test
        void addArticle_hasNotContents() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목").name(Board.MAIN).build();

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.addArticle("testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_CONTENT);
        }

        @DisplayName("등록하려는 게시글의 내용이 없다. (no String)")
        @Test
        void addArticle_hasNotContents_noString() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목").contents("").name(Board.MAIN).build();

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.addArticle("testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_CONTENT);
        }
    }

    @Nested
    @DisplayName("게시물 수정 성공 테스트")
    class updateArticleTest_success {

        @DisplayName("게시글을 정상적으로 수정한다.")
        @Test
        void updateArticle() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목 수정").contents("내용 수정").name(null).build();
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            ResponseEntity<Message> response = articleService.updateArticle(article.getId(), article.getUserId(), articleRequestDto);

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 수정 성공", response.getBody().getMessage());
        }
    }

    @Nested
    @DisplayName("게시물 수정 실패 테스트")
    class updateArticleTest_fail {

        @DisplayName("작성자가 달라서 게시글을 수정하지 못한다.")
        @Test
        void updateArticle_notAuth() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목 수정").contents("내용 수정").build();
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.updateArticle(article.getId(), "testUser2", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NOT_AUTHORIZED_USER);
        }

        @DisplayName("등록하려는 게시글의 제목이 없다. (null)")
        @Test
        void updateArticle_hasNotTitle() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().contents("내용").name(Board.MAIN).build();
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.updateArticle(article.getId(), "testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_TITLE);
        }

        @DisplayName("등록하려는 게시글의 제목이 없다. (no String)")
        @Test
        void updateArticle_hasNotTitle_noString() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("").contents("내용").name(Board.MAIN).build();
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.updateArticle(article.getId(), "testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_TITLE);
        }

        @DisplayName("등록하려는 게시글의 내용이 없다. (null)")
        @Test
        void updateArticle_hasNotContents() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목").name(Board.MAIN).build();
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.updateArticle(article.getId(), "testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_CONTENT);
        }

        @DisplayName("등록하려는 게시글의 내용이 없다.(no String)")
        @Test
        void updateArticle_hasNotContents_noString() {
            // Given
            ArticleRequestDto articleRequestDto = ArticleRequestDto.builder().title("제목").contents("").name(Board.MAIN).build();
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.updateArticle(article.getId(), "testUser", articleRequestDto));

            // Then
            assertEquals(exception.getErrorCode(), NON_CONTENT);
        }
    }


    @Nested
    @DisplayName("게시물 삭제 성공 테스트")
    class deleteArticleTest_success {

        @DisplayName("게시글을 정상적으로 삭제한다.")
        @Test
        void deleteArticle() {
            // Given
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            ResponseEntity<Message> response = articleService.deleteArticle(article.getId(), article.getUserId());

            // Then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("게시글 삭제 성공", response.getBody().getMessage());
        }
    }

    @Nested
    @DisplayName("게시물 삭제 실패 테스트")
    class deleteArticleTest_fail {

        @DisplayName("작성자가 달라서 게시글을 삭제하지 못한다.")
        @Test
        void deleteArticle_notAuth() {
            // Given
            when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

            // When
            CustomException exception = assertThrows(CustomException.class, () -> articleService.deleteArticle(article.getId(), "testUser2"));

            // Then
            assertEquals(exception.getErrorCode(), NOT_AUTHORIZED_USER);
        }
    }

}