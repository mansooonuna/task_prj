package com.mailplug.homework.article.dto;

import com.mailplug.homework.article.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDto {
    @Size(max = 100, message = "글의 제목은 100자 이내로 작성해주세요.")
    private String title;
    private String contents;
    private Board board;
}
