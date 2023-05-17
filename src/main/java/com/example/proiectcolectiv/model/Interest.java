package com.example.proiectcolectiv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "interests")

public class Interest {
    @Id
    @Column(name = "interestID")
    private Integer interestID;
    @Column(name="userID")
    private String userID;
    @Column(name="interes")
    private String interes;

    public Interest(Integer interestID, String userID, String interes) {
        this.interestID = interestID;
        this.userID = userID;
        this.interes = interes;
    }
    public Interest(){}
    public Integer getInterestID() {
        return interestID;
    }

    public void setInterestID(Integer interestID) {
        this.interestID = interestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }
}
