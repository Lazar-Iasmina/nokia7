package com.example.proiectcolectiv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.sql.Date;
import java.util.List;

public class UserInteresat {

    private String uid;
    private String displayName;
    private String photoURL;
    private String email;
    private String password;
    private Date joinDate;
    private String position;
    private List<String> interests;

    public UserInteresat(String uid, String displayName, String photoURL, String email, String password, Date joinDate, String position, List<String> interests) {
        this.uid = uid;
        this.displayName = displayName;
        this.photoURL = photoURL;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
        this.position = position;
        this.interests = interests;
    }
    public UserInteresat(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
