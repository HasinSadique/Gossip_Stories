package com.example.gossip;

public class MyFriend {
    private String currentUserUID,tobeFriendUID;

    public String getCurrentUser() {
        return currentUserUID;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUserUID = currentUser;
    }

    public String getTobeFriend() {
        return tobeFriendUID;
    }

    public void setTobeFriend(String tobeFriend) {
        this.tobeFriendUID = tobeFriend;
    }

    public MyFriend(String currentUserEmail, String toBeFriendEmail) {


    }

    public MyFriend() {
    }
}
