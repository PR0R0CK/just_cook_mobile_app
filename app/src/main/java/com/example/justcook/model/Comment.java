package com.example.justcook.model;

public class Comment {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    private String username;
    private String comment;
    private String recipeId;

    public Comment(String username, String comment, String recipeId) {
        this.username = username;
        this.comment = comment;
        this.recipeId = recipeId;
    }

}
