package com.firstweek.board.service;

import com.firstweek.board.dto.BoardResponseDto;
import com.firstweek.exception.CustomException;
import com.firstweek.exception.NewException;
import com.firstweek.security.domain.CustomUser;
import com.firstweek.board.entity.Post;
import com.firstweek.board.exception.PostNotFoundException;
import com.firstweek.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public ResponseEntity<BoardResponseDto> savePost(CustomUser user, Post post){

        if(post.getTitle()==null || post.getTitle().equals("")|| post.getTitle().trim().isEmpty()
                || post.getContent()==null || post.getContent().equals("") || post.getContent().trim().isEmpty()){
            throw new CustomException(NewException.NOT_VALID_ERROR);
        }

        post.setAuthor_id(user.getUserId());
        boardRepository.save(post);

        return new ResponseEntity<>(new BoardResponseDto(post), HttpStatus.CREATED);
    }


    public ResponseEntity<List<BoardResponseDto>> listPost(){
        Iterable<Post> allPost = boardRepository.findAll();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for (Post post : allPost) {
            boardResponseDtoList.add(new BoardResponseDto(post));
        }
        return ResponseEntity.ok(boardResponseDtoList);
    }

    public ResponseEntity<BoardResponseDto> getPost(int postId){
            Post post = boardRepository.findById(postId).orElseThrow(()->new CustomException(NewException.POST_NOT_FOUND));
        return new ResponseEntity<>(new BoardResponseDto(post), HttpStatus.OK);
    }

    public ResponseEntity<BoardResponseDto> deletePost(int postId){

        Post post = boardRepository.findById(postId).orElseThrow(()->new CustomException(NewException.POST_NOT_FOUND));
        boardRepository.delete(post);

        return new ResponseEntity<>(new BoardResponseDto(post), HttpStatus.OK);
    }

    public ResponseEntity<BoardResponseDto> likePost(int postId){
        Post post = boardRepository.findById(postId).orElseThrow(()->new CustomException(NewException.POST_NOT_FOUND));

        int like = post.getLikes();
        post.setLikes(++like);
        boardRepository.save(post);

        return ResponseEntity.ok(new BoardResponseDto(post));//이런 형식으로도 가능
    }

    public ResponseEntity<BoardResponseDto> dislikePost(int postId){
        Post post = boardRepository.findById(postId).orElseThrow(()->new CustomException(NewException.POST_NOT_FOUND));

        int dislike = post.getDislikes();
        post.setDislikes(++dislike);
        boardRepository.save(post);
        return new ResponseEntity<>(new BoardResponseDto(post), HttpStatus.OK);
    }

    public ResponseEntity<BoardResponseDto> updatePost(int postId, Post post){
        Post nowPost = boardRepository.findById(postId).orElse(null);

        if(post.getTitle()==null || post.getTitle().equals("")|| post.getTitle().trim().isEmpty()
                || post.getContent()==null || post.getContent().equals("") || post.getContent().trim().isEmpty()){
            throw new CustomException(NewException.NOT_VALID_ERROR);
        }

        nowPost.setTitle(post.getTitle());
        nowPost.setContent(post.getContent());

        boardRepository.save(nowPost);

        return new ResponseEntity<>(new BoardResponseDto(post), HttpStatus.OK);
    }
}
