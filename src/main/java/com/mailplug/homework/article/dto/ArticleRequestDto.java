package com.mailplug.homework.article.dto;

import com.mailplug.homework.article.entity.Board;
import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private String title;
    private String contents;
    private Board board;
}
