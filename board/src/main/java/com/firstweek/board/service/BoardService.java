package com.firstweek.board.service;

import com.firstweek.security.domain.CustomUser;
import com.firstweek.board.entity.Post;
import com.firstweek.board.exception.PostNotFoundException;
import com.firstweek.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void savePost(CustomUser user, Post post){

        boardRepository.save(post);
    }

    public List<Post> listPost(){

        return boardRepository.findAll();
    }

    public Post getPost(int postId){

        return boardRepository.findById(postId).orElse(null);
    }

    public Post deletePost(int postId){

        Post post = boardRepository.findById(postId).orElseThrow(()->new PostNotFoundException(postId+"번 게시글이 존재하지 않습니다."));
        boardRepository.delete(post);

        return post;
    }

    public Post likePost(int postId){
        Post post = boardRepository.findById(postId).orElseThrow(()->new PostNotFoundException(postId+"번 게시글이 존재하지 않습니다."));

        int like = post.getLikes();
        post.setLikes(++like);
        boardRepository.save(post);

        return post;
    }

    public Post dislikePost(int postId){
        Post post = boardRepository.findById(postId).orElseThrow(()->new PostNotFoundException(postId+"번 게시글이 존재하지 않습니다."));

        int dislike = post.getDislikes();
        post.setDislikes(++dislike);
        boardRepository.save(post);
        return post;
    }
}
