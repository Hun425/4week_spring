package com.firstweek.comment.service;

import com.firstweek.comment.domain.Comment;
import com.firstweek.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
