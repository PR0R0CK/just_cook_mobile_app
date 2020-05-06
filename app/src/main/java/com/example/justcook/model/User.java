package com.example.justcook.model;

public class User {
    private String userId;
    private String username;
    private String email;

    public User(){}

    public User(String userId, String username, String email) {
        this.setUserId(userId);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
