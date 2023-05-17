package com.example.proiectcolectiv.controller;

import com.example.proiectcolectiv.exceptions.UserUnknownException;
import com.example.proiectcolectiv.model.*;
import com.example.proiectcolectiv.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("")
public class LikeController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BadgesRepository badgesRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private DislikesRepository dislikesRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/like")
    @Transactional
    public Object postLike(@RequestBody Like like) {
        Like ln = new Like();
        Dislike dln = new Dislike();
        if (like.getValLike() == 1) {
            ln = likesRepository.getDacaExistaLike(like.getUserID(), like.getPostID());
            if (ln == null) {
                Integer x=0;
                if(likesRepository.getMaxLike()==null){
                    x=0;
                }
                else{
                    x=likesRepository.getMaxLike();
                }
                dislikesRepository.deleteDislike(like.getUserID(),like.getPostID());
                likesRepository.insertLike(x + 1, like.getPostID(), like.getUserID(), like.getValLike());
                return new ResponseEntity<>(new ReturnMessage("HTTP status will be CREATED"), HttpStatus.CREATED);
            } else {
                likesRepository.deleteLike(like.getUserID(), like.getPostID());
                return new ResponseEntity<>(new ReturnMessage("HTTP status will be OK"), HttpStatus.OK);
            }
        } else if (like.getValLike() == -1) {
            dln = dislikesRepository.getDacaExistaDislike(like.getUserID(), like.getPostID());
            if (dln == null) {
                Integer x=0;
                if(dislikesRepository.getMaxDislike()==null){
                    x=0;
                }
                else{
                    x=dislikesRepository.getMaxDislike();
                }
                likesRepository.deleteLike(like.getUserID(),like.getPostID());
                dislikesRepository.insertDislike(x + 1, like.getPostID(), like.getUserID(), like.getValLike());
                return new ResponseEntity<>(new ReturnMessage("HTTP status will be CREATED"), HttpStatus.CREATED);
            } else {
                dislikesRepository.deleteDislike(like.getUserID(), like.getPostID());
                return new ResponseEntity<>(new ReturnMessage("HTTP status will be OK"), HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(new Eroare(), HttpStatus.NOT_ACCEPTABLE);

        }

    }
}
