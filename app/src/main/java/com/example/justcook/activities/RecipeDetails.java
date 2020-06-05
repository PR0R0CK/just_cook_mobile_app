package com.example.justcook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.justcook.R;
import com.example.justcook.model.Comment;

import java.util.ArrayList;

public class RecipeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        TextView recipeSteps = findViewById(R.id.steps_textView_details);
        TextView recipeIngredients = findViewById(R.id.ingredients_textView_details);

        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
        initRecyclerView(comments);

    }
    private void initRecyclerView(ArrayList<Comment> comments){
        RecyclerView recyclerView = findViewById(R.id.comments_recyclerView_details);
        CommentRecyclerViewAdapter adapter = new CommentRecyclerViewAdapter(comments,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
