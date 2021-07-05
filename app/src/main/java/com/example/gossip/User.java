package com.example.gossip;

public class User {
    public User(){}

    private String UID,Fullname,DOB,Email;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public User(String uid,String fullname, String DOB, String email) {
        UID=uid;
        Fullname = fullname;
        this.DOB = DOB;
        Email = email;
    }
}
