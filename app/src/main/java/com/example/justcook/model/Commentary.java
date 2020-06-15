package com.example.justcook.model;

public class Commentary {
    private String commentId;
    private RecipeBook recipeBook;
    private User user;
    private String comment;

    public Commentary() {}
    public Commentary(String commentId, RecipeBook recipeBook, User user, String comment) {
        this.commentId = commentId;
        this.recipeBook = recipeBook;
        this.user = user;
        this.comment = comment;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public RecipeBook getRecipeBook() {
        return recipeBook;
    }

    public void setRecipeBook(RecipeBook recipeBook) {
        this.recipeBook = recipeBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
