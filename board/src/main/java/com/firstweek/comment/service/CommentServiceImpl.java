package com.firstweek.comment.service;

import com.firstweek.board.entity.Post;
import com.firstweek.board.repository.BoardRepository;
import com.firstweek.comment.domain.Comment;
import com.firstweek.comment.dto.CommentRequestDto;
import com.firstweek.comment.dto.CommentResponseDto;
import com.firstweek.comment.repository.CommentRepository;
import com.firstweek.security.domain.User;
import com.firstweek.security.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentServiceImpl {

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public ResponseEntity<CommentResponseDto> savedComment(CommentRequestDto commentRequestDto) {

        Optional<Post> optionalPost = boardRepository.findById(commentRequestDto.getPostId());
        Optional<User> optionalUser = userRepository.findById(commentRequestDto.getUserId());

        if (optionalPost.isEmpty() || optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글 또는 유저를 찾을 수 없습니다.");
        }

        Post post = optionalPost.get();
        User user = optionalUser.get();

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setAuthor(user);
        comment.setContent(commentRequestDto.getContent());


        commentRepository.save(comment);

        return new ResponseEntity(comment, HttpStatus.OK);
    }


}
