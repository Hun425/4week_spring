package com.firstweek.board.controller;

import com.firstweek.board.dto.BoardResponseDto;
import com.firstweek.security.domain.CustomUser;
import com.firstweek.board.entity.Post;
import com.firstweek.board.exception.PostNotFoundException;
import com.firstweek.board.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private final BoardServiceImpl boardService;

    public BoardController(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/post") // localhost:8080/post
    public ResponseEntity<String> boardWriteForm(){

        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<BoardResponseDto> boardPost(@AuthenticationPrincipal CustomUser user, Post post){
        return boardService.savePost(user,post);
    }

    @GetMapping("/")
    public ResponseEntity<String> boardIndex(){ // 메인 홈페이지라서 일반 스트링 사용
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @GetMapping("/post/list")
    @ResponseBody
    public ResponseEntity<List<BoardResponseDto>> boardList(Model model){
        return boardService.listPost();
    }

    @GetMapping("/post/{id}")
    @ResponseBody
    public ResponseEntity<BoardResponseDto> boardDetail(@PathVariable("id") int id){
        return boardService.getPost(id);
    }

    @DeleteMapping("/post/{id}")
    @ResponseBody
    public ResponseEntity<BoardResponseDto> deletePost(@PathVariable("id") int id){
        return boardService.deletePost(id);
    }

    @PostMapping("/post/update/{id}")
    @ResponseBody
    public ResponseEntity<BoardResponseDto> updatePost(@AuthenticationPrincipal CustomUser user, @PathVariable("id") int id, Post post){

        return boardService.updatePost(id,post);
    }

    @PostMapping("/post/{id}/like")
    @ResponseBody
    public ResponseEntity<BoardResponseDto> likePost(@AuthenticationPrincipal CustomUser user, @PathVariable("id") int id){
        return boardService.likePost(id);
    }

    @PostMapping("/post/{id}/dislike")
    @ResponseBody
    public ResponseEntity<BoardResponseDto> dislikePost(@AuthenticationPrincipal CustomUser user, @PathVariable("id") int id){
        return boardService.dislikePost(id);
    }


}
