package com.firstweek.board.dto;

import com.firstweek.board.entity.Post;
import com.firstweek.comment.domain.Comment;
import com.firstweek.comment.dto.CommentResponseDto;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {

    private Integer id;

    private String title;
    private String content;
    private Integer views;
    private Integer comment_count;
    private Integer author_id;
    private Integer likes;
    private Integer dislikes ;


    private LocalDateTime created_at;


    private List<CommentResponseDto> comments;

    public BoardResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.comment_count = post.getComment_count();
        this.author_id = post.getAuthor_id();
        this.likes = post.getLikes();
        this.dislikes = post.getDislikes();
        this.created_at = post.getCreated_at();
        this.comments = post.getComments().stream()
                .map(comment -> new CommentResponseDto(comment))
                .collect(Collectors.toList());
    }
}
