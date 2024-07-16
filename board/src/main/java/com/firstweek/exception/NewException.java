package com.firstweek.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum NewException { // 예외 발생시, body에 실어 날려줄 상태, message 커스텀

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 유저를 찾을 수 없습니다."),
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED,  "토큰이 만료되었습니다. 다시 로그인 해주세요."),
    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당하는 사용자를 찾을 수 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 글을 찾을 수 없습니다."),
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당하는 댓글을 찾을 수 없습니다."),
    AUTHORIZED_FAILED(HttpStatus.UNAUTHORIZED,"권한이 없습니다."),

    NULL_POINT_ERROR(HttpStatus.NOT_FOUND,  "NullPointerException 발생"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_ERROR(HttpStatus.NOT_FOUND,"올바른 값을 입력해주세요");


    // 1. status = 날려줄 상태코드
    // 2. message = 발생한 예외에 대한 설명.

    private final HttpStatus status;
    private final String message;

    NewException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
