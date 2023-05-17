package com.example.proiectcolectiv.repository;


import com.example.proiectcolectiv.model.Comment;
import com.example.proiectcolectiv.model.Dislike;
import com.example.proiectcolectiv.model.Like;
import org.springframework.data.jpa.repository.Query;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface DislikesRepository extends JpaRepository<Dislike, Integer>{
    @Query(value="select count(valDislike) from dislikes where userID=:uid", nativeQuery=true)
    public Integer getDislikePerUser(@Param("uid") String uid);
    @Query(value="select count(valDislike) from dislikes where postID=:postID", nativeQuery = true)
    public Integer getDislikePerPost(@Param("postID") Integer postID);

    @Query(value="select * from dislikes where userID=:uid and postID=:postID",nativeQuery = true)
    public Dislike getDacaExistaDislike(@Param("uid") String uid, @Param("postID") Integer postID);
    @Modifying
    @Query(value="delete from dislikes where userID=:uid and postID=:postID",nativeQuery = true)
    public void deleteDislike(@Param("uid") String uid, @Param("postID") Integer postID);
    @Modifying
    @Query(value="insert into dislikes (dislikeID,postID,userID,valDislike) values(:dislikeID,:postID,:userID,:valDislike)",nativeQuery = true)
    public void insertDislike(@Param("dislikeID") Integer dislikeID,@Param("postID") Integer postID,@Param("userID") String userID,@Param("valDislike") Integer valDislike);
    @Query(value="select max(dislikeID) from dislikes",nativeQuery = true)
    public Integer getMaxDislike();
}
