package com.example.bhupesh.swachhnew;

/**
 * Created by bhupesh on 29/4/17.
 */

public class Users {

    String email,username,uid;

    public Users() {
    }

    public Users(String email, String username, String uid) {
        this.email = email;
        this.username = username;
        this.uid = uid;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
