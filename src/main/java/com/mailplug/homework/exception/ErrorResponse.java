package com.mailplug.homework.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private String message;
    private int status;
    private String data;

    // 에러 반환 형식
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .message(errorCode.getData())
                        .status(errorCode.getHttpStatus().value())
                        .build()
                );
    }

    // 에러 반환 형식
    public static ResponseEntity<ErrorResponse> toResponseEntityValid(String errorCode, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus.value())
                .body(ErrorResponse.builder()
                        .message(errorCode)
                        .status(httpStatus.value())
                        .build()
                );
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus httpStatus, String data) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .message(httpStatus.name())
                        .data(data)
                        .status(httpStatus.value())
                        .build()
                );
    }
}