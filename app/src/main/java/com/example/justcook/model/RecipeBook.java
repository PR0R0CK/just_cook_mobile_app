package com.example.justcook.model;

import com.google.firebase.database.DatabaseReference;

public class RecipeBook {
    private User user;
    private String recipeId;
    private String name;
    private String type;
    private String picture;
    private String ingredients;
    private String recipe;
    private String difficulty;
    private String rate;

    public RecipeBook(){}

    public RecipeBook(User user, String recipeId, String name, String type, String picture, String ingredients, String recipe, String difficulty, String rate) {
        this.user = user;
        this.recipeId = recipeId;
        this.name = name;
        this.type = type;
        this.picture = picture;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.difficulty = difficulty;
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
