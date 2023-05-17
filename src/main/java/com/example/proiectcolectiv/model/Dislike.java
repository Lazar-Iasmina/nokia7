package com.example.proiectcolectiv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dislikes")
public class Dislike {
    @Id
    @Column(name = "dislikeID")
    private Integer likeID;
    @Column(name="postID")
    private Integer postID;
    @Column(name="userID")
    private String userID;
    @Column(name="valDislike")
    private Integer valDislike;

    public Dislike(Integer likeID, Integer postID, String userID, Integer valDislike) {
        this.likeID = likeID;
        this.postID = postID;
        this.userID = userID;
        this.valDislike = valDislike;
    }
    public Dislike(){}
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

    public Integer getValDislike() {
        return valDislike;
    }

    public void setValDislike(Integer valDislike) {
        this.valDislike = valDislike;
    }
}
