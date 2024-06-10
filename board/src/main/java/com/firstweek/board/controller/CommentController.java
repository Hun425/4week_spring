package com.firstweek.board.controller;

import com.firstweek.board.entity.Comment;
import com.firstweek.board.entity.Post;
import com.firstweek.board.service.BoardService;
import com.firstweek.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @Autowired
    private final BoardService boardService;

    @Autowired
    private final CommentService commentService;

    public CommentController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @PostMapping("/post/{id}")
    @ResponseBody
    public ResponseEntity<String> postComment(@PathVariable("id") int postId, @RequestBody Comment comment) {

        Post post = boardService.getPost(postId);

        if(post == null) {
            return ResponseEntity.notFound().build();
        }

        comment.setPost(post);

        Comment savedComment = commentService.savedComment(comment);

        return ResponseEntity.ok().body(savedComment.toString());
    }


}
