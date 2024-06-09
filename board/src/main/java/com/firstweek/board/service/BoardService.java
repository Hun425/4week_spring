package com.firstweek.board.service;

import com.firstweek.board.entity.Post;
import com.firstweek.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void savePost(Post post){

        boardRepository.save(post);
    }

    public void getPost(Post post){


    }
}
