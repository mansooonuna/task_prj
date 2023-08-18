package com.mailplug.homework.article.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR"),
    NOT_AUTHORIZED_USER(HttpStatus.BAD_REQUEST, "작성자만 수정,삭제할 수 있습니다."),
    NON_TITLE(HttpStatus.BAD_REQUEST, "글의 제목이 없습니다."),
    NON_CONTENT(HttpStatus.BAD_REQUEST, "글의 내용이 없습니다."),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 게시글이 존재하지 않습니다." );

    private final HttpStatus httpStatus;
    private final String data;

}