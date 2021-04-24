package com.example.gossip;

public class MyFriend {
    private String currentUser,tobeFriend;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getTobeFriend() {
        return tobeFriend;
    }

    public void setTobeFriend(String tobeFriend) {
        this.tobeFriend = tobeFriend;
    }

    public MyFriend(String currentUserEmail, String toBeFriendEmail) {


    }

    public MyFriend() {
    }
}
