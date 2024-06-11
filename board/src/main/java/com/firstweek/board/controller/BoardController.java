package com.firstweek.board.controller;

import com.firstweek.board.entity.CustomUser;
import com.firstweek.board.entity.Post;
import com.firstweek.board.entity.User;
import com.firstweek.board.exception.PostNotFoundException;
import com.firstweek.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public String boardPost(@AuthenticationPrincipal CustomUser user, Post post){

        post.setAuthor_id(user.getUserId());
        boardService.savePost(user,post);

        String result = post.toString();
        return result;
    }

    @GetMapping("/")
    public String boardIndex(){
        return "index";
    }

    @GetMapping("/post/list")
    @ResponseBody
    public String boardList(Model model){

        model.addAttribute("list",boardService.listPost());
        String postList = boardService.listPost().toString();
        return postList;
    }

    @GetMapping("/post/{id}")
    @ResponseBody
    public String boardDetail(@PathVariable("id") int id){
        String post = boardService.getPost(id).toString();

        return post;
    }

    @DeleteMapping("/post/{id}")
    @ResponseBody
    public ResponseEntity<String> deletePost(@PathVariable("id") int id){
        try{
        boardService.deletePost(id);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch (PostNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post/update/{id}")
    @ResponseBody
    public ResponseEntity<String> updatePost(@AuthenticationPrincipal CustomUser user, @PathVariable("id") int id, Post post){
        Post nowPost = boardService.getPost(id);

        nowPost.setAuthor_id(user.getUserId());
        nowPost.setTitle(post.getTitle());
        nowPost.setContent(post.getContent());

        boardService.savePost(user,nowPost);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @PostMapping("/post/{id}/like")
    @ResponseBody
    public ResponseEntity<String> likePost(@AuthenticationPrincipal CustomUser user, @PathVariable("id") int id){
        boardService.likePost(id);

        return new ResponseEntity<>("ok",HttpStatus.OK);
    }


}
