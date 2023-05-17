package com.example.proiectcolectiv.controller;

import com.example.proiectcolectiv.exceptions.EmailException;
import com.example.proiectcolectiv.exceptions.UserUnknownException;
import com.example.proiectcolectiv.model.*;
import com.example.proiectcolectiv.model.Eroare;
import com.example.proiectcolectiv.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BadgesRepository badgesRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private DislikesRepository dislikesRepository;
    @Autowired
    private InterestRepository interestRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/{id}")
    @Transactional
    public Object getDummyUserPremium(@PathVariable("id") String id) {
        try {
            UserPremium3 a = new UserPremium3();
            User user = userRepository.getUserID(id);
            a.setUid(user.getUid());
            a.setPhotoURL(user.getPhotoURL());
            a.setDisplayName(userRepository.getUserDisplayName(id));
            UserPremium.Stats stats = new UserPremium.Stats();
            stats.setQuestions(postRepository.getUserQuestions(id));
            a.setInterests(interestRepository.getInterestOfUser(user.getUid()));
            if(likesRepository.getLikePerUser(id)==null){
                stats.setLikes(0);
            }
            else{
                stats.setLikes(likesRepository.getLikePerUser(id));
            }
            if(dislikesRepository.getDislikePerUser(id)==null){
                stats.setDislikes(0);

            }
            else{
                stats.setDislikes(dislikesRepository.getDislikePerUser(id));
            }
            a.setStats(stats);
            List<Badges> b = badgesRepository.getUserBadges(id);
            Map<String, List<String>> m = new HashMap<>();
            m.put("bronze",new ArrayList<String>());
            m.put("silver",new ArrayList<String>());
            m.put("gold",new ArrayList<String>());
            for (Badges i : b) {
                if (m.containsKey(i.getBadgeType())) {
                    List<String> l = new ArrayList<>();
                    l.addAll(m.get(i.getBadgeType()));
                    l.add(i.getIdBadges());
                    m.put(i.getBadgeType(), l);

                } else {
                    List<String> list = new ArrayList<>();
                    list.add(i.getIdBadges());
                    m.put(i.getBadgeType(), list);
                }
            }
            a.setBadges(m);

            List<Question> result = new ArrayList<>();
            //questions.setQuestionID();
            List<Post> post = postRepository.getPostbyID(id);
            //System.out.println("A");
            for (Post i : post) {
                Question q = new Question();
                q.setQuestionID(i.getPostID());
                User1 u = new User1();
                if(userRepository.getUserDisplayName(i.getUserID())==null){
                    u.setDisplayName("");
                }
                else{
                    u.setDisplayName(userRepository.getUserDisplayName(i.getUserID()));
                }
                if(userRepository.getUserURL(i.getUserID())==null){
                    u.setPhotoURL("");
                }
                else{
                    u.setPhotoURL(userRepository.getUserURL(i.getUserID()));
                }

                q.setUser(u);
                List<String> label = labelRepository.getPostbyID(i.getPostID());
                q.setQuestionLabels(label);
                if(i.getQuestionContent()==null){
                    q.setQuestionContent("");
                }
                else{
                    q.setQuestionContent(i.getQuestionContent());
                }
                if(i.getQuestionTitle()==null){
                    q.setQuestionTitle("");
                }
                else{
                    q.setQuestionTitle(i.getQuestionTitle());
                }
                if(likesRepository.getLikePerPost(i.getPostID())==null){
                    q.setLikes(0);
                }
                else{
                    q.setLikes(likesRepository.getLikePerPost(i.getPostID()));
                }
                if(dislikesRepository.getDislikePerPost(i.getPostID())==null){
                    q.setDislikes(0);
                }
                else{
                    q.setDislikes(dislikesRepository.getDislikePerPost(i.getPostID()));
                }
                if(i.getCategory()==null){
                    q.setCategory("");
                }
                else{
                    q.setCategory(i.getCategory());
                }
                if(i.getQuestionCode()==null){
                    q.setCode("");
                }
                else{
                    q.setCode(i.getQuestionCode());
                }

                List<Comment> comment = commentRepository.getPostbyID(i.getPostID());
                List<Answer> answers = new ArrayList<>();
                for (Comment c : comment) {
                    Answer answer = new Answer();
                    if(c.getCommentCode()==null){
                        answer.setCode("");
                    }
                    else{
                        answer.setCode(c.getCommentCode());
                    }

                    User1 user1 = new User1();
                    if(userRepository.getUserURL(c.getUserID())==null){
                        user1.setPhotoURL("");
                    }
                    else{
                        user1.setPhotoURL(userRepository.getUserURL(c.getUserID()));
                    }
                    if(userRepository.getUserDisplayName(c.getUserID())==null){
                        user1.setDisplayName("");
                    }
                    else{
                        user1.setDisplayName(userRepository.getUserDisplayName(c.getUserID()));

                    }
                    answer.setUser(user1);
                    if(c.getCommentTitle()==null){
                        answer.setAnswerTitle("");
                    }
                    else{
                        answer.setAnswerTitle(c.getCommentTitle());
                    }
                    if(c.getContent()==null){
                        answer.setAnswerDetails("");
                    }
                    else{
                        answer.setAnswerDetails(c.getContent());
                    }

                    answers.add(answer);
                }
                q.setAnswers(answers);
                result.add(q);
            }
            a.setQuestions(result);
            List<Question> favorite=new ArrayList<>();
            List<Integer> postFav=new ArrayList<>();
            postFav=favoriteRepository.getFavorite(id);
            //Post x=new Post();
            for(Integer j:postFav){
                Post i=new Post();
                i=postRepository.getID(j);
                Question q = new Question();
                q.setQuestionID(i.getPostID());
                User1 u = new User1();
                if(userRepository.getUserDisplayName(i.getUserID())==null){
                    u.setDisplayName("");
                }
                else{
                    u.setDisplayName(userRepository.getUserDisplayName(i.getUserID()));
                }
                if(userRepository.getUserURL(i.getUserID())==null){
                    u.setPhotoURL("");
                }
                else{
                    u.setPhotoURL(userRepository.getUserURL(i.getUserID()));
                }

                q.setUser(u);
                List<String> label = labelRepository.getPostbyID(i.getPostID());
                q.setQuestionLabels(label);
                if(i.getQuestionContent()==null){
                    q.setQuestionContent("");
                }
                else{
                    q.setQuestionContent(i.getQuestionContent());
                }
                if(i.getQuestionTitle()==null){
                    q.setQuestionTitle("");
                }
                else{
                    q.setQuestionTitle(i.getQuestionTitle());
                }
                if(i.getLikes()==null){
                    q.setLikes(0);
                }
                else{
                    q.setLikes(i.getLikes());
                }
                if(i.getDisikes()==null){
                    q.setDislikes(0);
                }
                else{
                    q.setDislikes(i.getDisikes());
                }
                if(i.getCategory()==null){
                    q.setCategory("");
                }
                else{
                    q.setCategory(i.getCategory());
                }
                if(i.getQuestionCode()==null){
                    q.setCode("");
                }
                else{
                    q.setCode(i.getQuestionCode());
                }

                List<Comment> comment = commentRepository.getPostbyID(i.getPostID());
                List<Answer> answers = new ArrayList<>();
                for (Comment c : comment) {
                    Answer answer = new Answer();
                    if(c.getCommentCode()==null){
                        answer.setCode("");
                    }
                    else{
                        answer.setCode(c.getCommentCode());
                    }

                    User1 user1 = new User1();
                    if(userRepository.getUserURL(c.getUserID())==null){
                        user1.setPhotoURL("");
                    }
                    else{
                        user1.setPhotoURL(userRepository.getUserURL(c.getUserID()));
                    }
                    if(userRepository.getUserDisplayName(c.getUserID())==null){
                        user1.setDisplayName("");
                    }
                    else{
                        user1.setDisplayName(userRepository.getUserDisplayName(c.getUserID()));

                    }
                    answer.setUser(user1);
                    if(c.getCommentTitle()==null){
                        answer.setAnswerTitle("");
                    }
                    else{
                        answer.setAnswerTitle(c.getCommentTitle());
                    }
                    if(c.getContent()==null){
                        answer.setAnswerDetails("");
                    }
                    else{
                        answer.setAnswerDetails(c.getContent());
                    }

                    answers.add(answer);
                }
                q.setAnswers(answers);
                favorite.add(q);

            }
            a.setFavorites(favorite);
            return a;
        } catch (Exception e) {
            //HttpStatus.NOT_FOUND, "entity not found"
            return new ResponseEntity<>(new Eroare(),HttpStatus.NOT_FOUND);
            //return new Eroare();
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users")
    @Transactional
    public Object createUser(@RequestBody UserInteresat newUser){
        //System.out.println("am intrat in POST");
        User a = new User(newUser.getUid(),newUser.getDisplayName(),newUser.getPhotoURL(), newUser.getEmail(), newUser.getPassword(), newUser.getJoinDate(), newUser.getPosition());
       // System.out.println("Acum se salveaza");
        try {
            if(userRepository.getEmail(newUser.getEmail())!= null){
                throw new EmailException("Email already used");
            }
            else {
                userRepository.saveUser(a.getUid(), a.getDisplayName(), a.getPhotoURL(), a.getEmail(), a.getPassword(), a.getJoinDate(), a.getPosition());
                for(String i : newUser.getInterests()){
                    Integer nr=interestRepository.getMaxInteres();
                    if(nr==null){
                        nr=0;
                    }
                    interestRepository.insertInterese(nr,a.getUid(),i);
                }

                return new ResponseEntity<>(new ReturnMessage("HTTP status will be CREATED"), HttpStatus.CREATED);
            }
        }
        catch (EmailException e){
            //e.printStackTrace();
            return new ResponseEntity<>(new Eroare("Email already used"),HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception e){
            return new ResponseEntity<>(new Eroare(),HttpStatus.NOT_ACCEPTABLE);
        }


    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/users/{uid}")
    @Transactional
    public Object alterUser(@RequestBody UserInteresat alterUser){
        try {
            User a = new User(alterUser.getUid(), alterUser.getDisplayName(), alterUser.getPhotoURL(), alterUser.getEmail(), alterUser.getPassword(), alterUser.getJoinDate(), alterUser.getPosition());
            if (userRepository.getUID(a.getUid()) == null) {
                throw new UserUnknownException();
            }
            for(String i : alterUser.getInterests()){
                Integer nr=interestRepository.getMaxInteres();
                if(nr==null){
                    nr=0;
                }
                interestRepository.insertInterese(nr,a.getUid(),i);
            }
            userRepository.saveUser(a.getUid(), a.getDisplayName(), a.getPhotoURL(), a.getEmail(), a.getPassword(), a.getJoinDate(), a.getPosition());
            return new ResponseEntity<>(new ReturnMessage("HTTP status will be OK"), HttpStatus.OK);
        }catch(UserUnknownException e){
            return new ResponseEntity<>(new ReturnMessage("HTTP status will be NOT_FOUND"), HttpStatus.NOT_FOUND);
        }

    }



}
