package com.firstweek.comment.dto;

import com.firstweek.board.entity.Post;
import com.firstweek.comment.domain.Comment;
import com.firstweek.security.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Integer id;

    private Post post;

    private User author;

    private String content;


    private LocalDateTime created_at;

    private Integer likes =0;
    private Integer dislikes =0;
    private Integer reply_count =0;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.post = comment.getPost();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
        this.created_at = comment.getCreated_at();
        this.likes = comment.getLikes();
        this.dislikes = comment.getDislikes();
        this.reply_count = comment.getReply_count();
    }
}
