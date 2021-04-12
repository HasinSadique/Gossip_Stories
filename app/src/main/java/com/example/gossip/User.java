package com.example.gossip;

public class User {
    public User(){}

    private String Fullname,DOB,Email;

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

    public User(String fullname, String DOB, String email) {
        Fullname = fullname;
        this.DOB = DOB;
        Email = email;
    }
}
