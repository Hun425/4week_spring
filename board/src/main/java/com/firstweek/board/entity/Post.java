package com.firstweek.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String title;
    private String content;
    private int views = 0;
    private int comment_count;
    private String author_id;
    private int likes = 0;
    private int dislikes = 0;

    @CreationTimestamp
    private LocalDateTime created_at;
}
