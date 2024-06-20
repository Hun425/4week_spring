package com.firstweek.comment.service;

import com.firstweek.comment.dto.CommentRequestDto;
import com.firstweek.comment.dto.CommentResponseDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<CommentResponseDto> savedComment(CommentRequestDto commentRequestDto);
}
