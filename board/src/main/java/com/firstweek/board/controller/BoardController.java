package com.firstweek.board.controller;

import com.firstweek.board.entity.Post;
import com.firstweek.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/post") // localhost:8080/post
    public String boardWriteForm(){

        return "boardwrite";
    }

    @PostMapping("/post")
    public String boardPost(Post post){


        boardService.savePost(post);

        return "";
    }

    @GetMapping("/post/list")
    public String boardList(){


        return "";
    }
}
