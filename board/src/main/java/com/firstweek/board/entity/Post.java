package com.firstweek.board.entity;

import com.firstweek.comment.domain.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private Integer views = 0;
    private Integer comment_count;
    private Integer author_id;
    private Integer likes = 0;
    private Integer dislikes = 0;

    @CreationTimestamp
    private LocalDateTime created_at;

    @OneToMany(mappedBy="post", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\'' + ", views=" + views + ", comment_count=" + comment_count + ", author_id='" + author_id + '\'' + ", likes=" + likes + ", dislikes=" + dislikes + ", created_at=" + created_at + ", comments=" + comments + '}';
    }
}
