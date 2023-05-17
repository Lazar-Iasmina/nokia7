package com.example.proiectcolectiv.model;

public class AnswerCuID {
    private Integer answerID;
    private String code;
    private UserNewAdd user;
    private String answerTitle;
    private String answerDetails;

    public AnswerCuID(Integer answerID, String code, UserNewAdd user, String answerTitle, String answerDetails) {
        this.answerID = answerID;
        this.code = code;
        this.user = user;
        this.answerTitle = answerTitle;
        this.answerDetails = answerDetails;
    }
    public AnswerCuID(){}

    public Integer getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserNewAdd getUser() {
        return user;
    }

    public void setUser(UserNewAdd user) {
        this.user = user;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public String getAnswerDetails() {
        return answerDetails;
    }

    public void setAnswerDetails(String answerDetails) {
        this.answerDetails = answerDetails;
    }

    @Override
    public String toString() {
        return "AnswerCuID{" +
                "answerID=" + answerID +
                ", code='" + code + '\'' +
                ", user=" + user +
                ", answerTitle='" + answerTitle + '\'' +
                ", answerDetails='" + answerDetails + '\'' +
                '}';
    }
}
