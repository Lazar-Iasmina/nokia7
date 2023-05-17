package com.example.proiectcolectiv.repository;


import com.example.proiectcolectiv.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Modifying
    @Query(value = "insert into favorite (idFav,postID,userID) values(:idFav,:postID,:userID) on duplicate Key update idFav=:idFav,postID=:postID,userID=:userID",nativeQuery = true)
    public void saveFavorite(@Param("idFav")Integer idFav,@Param("postID") Integer postID, @Param("userID") String userID);
    @Query(value = "select max(idFav) from favorite",nativeQuery = true)
    public Integer getMaxFav();
    @Modifying
    @Query(value="delete from favorite where userID=:userID and postId=:postID", nativeQuery = true)
    public void deleteFav(@Param("postID")Integer postID,@Param("userID") String userID);
    @Query(value="select distinct postID from favorite where userID=:userID",nativeQuery = true)
    public List<Integer> getFavorite(@Param("userID") String userID);

}
