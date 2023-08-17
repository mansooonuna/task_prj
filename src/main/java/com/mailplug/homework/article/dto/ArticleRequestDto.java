package com.mailplug.homework.article.dto;

import com.mailplug.homework.article.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDto {
    private String title;
    private String contents;
    private Board board;
}
