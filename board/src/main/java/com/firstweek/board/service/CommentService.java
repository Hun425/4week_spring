package com.firstweek.board.service;

import com.firstweek.board.entity.Comment;
import com.firstweek.board.entity.Post;
import com.firstweek.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment savedComment(Comment comment) {
        return commentRepository.save(comment);
    }


}
