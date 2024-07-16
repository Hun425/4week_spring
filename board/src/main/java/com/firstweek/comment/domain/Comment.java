package com.firstweek.comment.domain;

import com.firstweek.board.entity.Post;
import com.firstweek.security.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    private String content;

    @CreationTimestamp
    private LocalDateTime created_at;

    private Integer likes =0;
    private Integer dislikes =0;
    private Integer reply_count =0;

    @Override
    public String toString() {
        return "Comment{" + "id=" + id
                + ", author=" + author
                + ", content='" + content
                + '\'' + ", created_at=" + created_at
                + ", likes=" + likes
                + ", dislikes=" + dislikes
                + ", reply_count=" + reply_count + '}';
    }
}
