package com.firstweek.comment.controller;

import com.firstweek.board.dto.BoardResponseDto;
import com.firstweek.board.repository.BoardRepository;
import com.firstweek.comment.domain.Comment;
import com.firstweek.board.entity.Post;
import com.firstweek.board.service.BoardServiceImpl;
import com.firstweek.comment.dto.CommentRequestDto;
import com.firstweek.comment.dto.CommentResponseDto;
import com.firstweek.comment.service.CommentServiceImpl;
import com.firstweek.security.domain.User;
import com.firstweek.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @Autowired
    private final BoardServiceImpl boardService;

    @Autowired
    private final CommentServiceImpl commentService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    public CommentController(BoardServiceImpl boardService, CommentServiceImpl commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @PostMapping("/post/{id}")
    @ResponseBody
    public ResponseEntity<CommentResponseDto> postComment(
            @PathVariable("id") int postId,
            @RequestParam String content,
            @AuthenticationPrincipal UserDetails userDetails) {



        Post post = boardRepository.findById(postId).orElse(null);

        if(post == null) {
            return ResponseEntity.notFound().build();
        }

        User author = userRepository.findByUsername(userDetails.getUsername()).orElseGet(null);

        if(author == null) {
            return ResponseEntity.notFound().build();
        }

        CommentRequestDto commentRequestDto = new CommentRequestDto();

        commentRequestDto.setContent(content);
        commentRequestDto.setPostId(post.getId());
        commentRequestDto.setUserId(author.getId());

        CommentResponseDto comment = commentService.savedComment(commentRequestDto).getBody();

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }



}
