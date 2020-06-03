package com.example.justcook.model;

import com.google.firebase.database.DatabaseReference;

public class RecipeBook {
    private String userId;
    private String recipeId;
    private String picture;
    private String ingredients;
    private String recipe;
    private String difficulty;
    private String rate;

    public RecipeBook(){}

    public RecipeBook(String userId, String recipeId, String picture, String ingredients, String recipe, String difficulty, String rate) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.picture = picture;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.difficulty = difficulty;
        this.rate = rate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RecipeBook{" +
                "userId='" + userId + '\'' +
                ", recipeId='" + recipeId + '\'' +
                ", picture='" + picture + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", recipe='" + recipe + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
