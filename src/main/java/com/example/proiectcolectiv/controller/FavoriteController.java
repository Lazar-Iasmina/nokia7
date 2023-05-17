package com.example.proiectcolectiv.controller;
import com.example.proiectcolectiv.model.Favorite;
import com.example.proiectcolectiv.model.ReturnMessage;
import com.example.proiectcolectiv.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class FavoriteController {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private PostRepository postRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/favorite")
    @Transactional
    public Object postFavorite(@RequestBody Favorite fav) {
        Integer nr = 0;
        if (favoriteRepository.getMaxFav() == null) {
            nr = 0;
        } else {
            nr = favoriteRepository.getMaxFav() + 1;
        }
        favoriteRepository.saveFavorite(nr, fav.getPostID(), fav.getUserID());
        return new ResponseEntity<>(new ReturnMessage("HTTP status will be CREATED"), HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/favorite/{pid}/{uid}")
    @Transactional
    public Object deleteFavorite(@PathVariable("pid") Integer pid,@PathVariable("uid") String uid) {
        favoriteRepository.deleteFav(pid,uid);
        return new ResponseEntity<>(new ReturnMessage("HTTP status will be OK"), HttpStatus.OK);

    }
}
