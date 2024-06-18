package com.firstweek.board.service;

import com.firstweek.board.dto.BoardRequestDto;
import com.firstweek.board.dto.BoardResponseDto;
import com.firstweek.board.entity.Post;
import com.firstweek.security.domain.CustomUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {

    ResponseEntity<BoardResponseDto> savePost(CustomUser user, Post post);
    ResponseEntity<List<BoardResponseDto>> listPost();
    ResponseEntity<BoardResponseDto> deletePost(int postId);
    ResponseEntity<BoardResponseDto> getPost(int postId);
    ResponseEntity<BoardResponseDto> updatePost(int postId, Post post);


}
