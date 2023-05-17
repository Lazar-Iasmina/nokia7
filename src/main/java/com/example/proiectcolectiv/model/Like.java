package com.example.proiectcolectiv.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "likeID")
    private Integer likeID;
    @Column(name="postID")
    private Integer postID;
    @Column(name="userID")
    private String userID;
    @Column(name="valLike")
    private Integer valLike;

    public Like(Integer likeID, Integer postID, String userID, Integer valLike) {
        this.likeID = likeID;
        this.postID = postID;
        this.userID = userID;
        this.valLike = valLike;
    }
    public Like(){}
    public Integer getLikeID() {
        return likeID;
    }

    public void setLikeID(Integer likeID) {
        this.likeID = likeID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getValLike() {
        return valLike;
    }

    public void setValLike(Integer valLike) {
        this.valLike = valLike;
    }
}
