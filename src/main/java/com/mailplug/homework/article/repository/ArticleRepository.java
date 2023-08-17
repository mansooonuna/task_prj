package com.mailplug.homework.article.repository;

import com.mailplug.homework.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
