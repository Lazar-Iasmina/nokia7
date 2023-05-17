package com.example.proiectcolectiv.repository;

import com.example.proiectcolectiv.model.Comment;
import com.example.proiectcolectiv.model.Dislike;
import com.example.proiectcolectiv.model.Interest;
import com.example.proiectcolectiv.model.Like;
import org.springframework.data.jpa.repository.Query;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Integer>{
   @Query(value="select distinct interes from interests where userID=:userID",nativeQuery = true)
   public List<String> getInterestOfUser(@Param("userID") String userID);
   @Query(value="select max(interestID) from interests",nativeQuery = true)
   public Integer getMaxInteres();
   @Modifying
   @Query(value="insert into interests (interestID,userID,interes) values(:interestID,:userID,:interes)",nativeQuery = true)
   public void insertInterese(@Param("interestID")Integer interestID,@Param("userID") String userID,@Param("interes") String interes);


}
