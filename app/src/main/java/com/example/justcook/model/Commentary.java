package com.example.justcook.model;

public class Commentary {
    private String commentId;
    private String recipeId;
    private String username;
    private String comment;

    public Commentary(String username, String comment, String recipeId) {
        this.recipeId = recipeId;
        this.username = username;
        this.comment = comment;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

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
}
