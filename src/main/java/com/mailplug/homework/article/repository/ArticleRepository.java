package com.mailplug.homework.article.repository;

import com.mailplug.homework.article.entity.Article;
import com.mailplug.homework.article.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT a FROM Article a WHERE a.name = :name ")
    List<Article> findAllByBoard(@Param("name") Board name, Pageable pageable);

    @Query(value = "SELECT a FROM Article a WHERE a.name = :name AND a.title LIKE %:searchKeyword%")
    List<Article> findAllByBoardAndSearchKeyword(Board name, String searchKeyword, Pageable pageable);
}
