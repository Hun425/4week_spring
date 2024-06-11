package com.firstweek.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
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
    private int views = 0;
    private int comment_count;
    private int author_id;
    private int likes = 0;
    private int dislikes = 0;

    @CreationTimestamp
    private LocalDateTime created_at;

    @OneToMany(mappedBy="post", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\'' + ", views=" + views + ", comment_count=" + comment_count + ", author_id='" + author_id + '\'' + ", likes=" + likes + ", dislikes=" + dislikes + ", created_at=" + created_at + ", comments=" + comments + '}';
    }
}
