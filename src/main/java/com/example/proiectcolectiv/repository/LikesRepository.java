package com.example.proiectcolectiv.repository;

import com.example.proiectcolectiv.model.Comment;
import com.example.proiectcolectiv.model.Like;
import org.springframework.data.jpa.repository.Query;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface LikesRepository extends JpaRepository<Like, Integer> {
    @Query(value="select count(valLike) from likes,post where post.userID=:uid and likes.postID=post.postID", nativeQuery=true)
    public Integer getLikePerUser(@Param("uid") String uid);
    @Query(value="select count(valLike) from likes where postID=:postID", nativeQuery = true)
    public Integer getLikePerPost(@Param("postID") Integer postID);
    @Query(value="select * from likes where userID=:uid and postID=:postID",nativeQuery = true)
    public Like getDacaExistaLike(@Param("uid") String uid, @Param("postID") Integer postID);
    @Modifying
    @Query(value="delete from likes where userID=:uid and postID=:postID",nativeQuery = true)
    public void deleteLike(@Param("uid") String uid, @Param("postID") Integer postID);
    @Modifying
    @Query(value="insert into likes (likeID,postID,userID,valLike) values(:likeID,:postID,:userID,:valLike)",nativeQuery = true)
    public void insertLike(@Param("likeID") Integer likeID,@Param("postID") Integer postID,@Param("userID") String userID,@Param("valLike") Integer valLike);
    @Query(value="select max(likeID) from likes",nativeQuery = true)
    public Integer getMaxLike();
}
