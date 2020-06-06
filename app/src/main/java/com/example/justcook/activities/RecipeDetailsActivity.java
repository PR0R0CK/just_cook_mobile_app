package com.example.justcook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.justcook.R;
import com.example.justcook.model.Commentary;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ArrayList<Commentary> comments = new ArrayList<>();
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        comments.add(new Commentary("User1","Comment1","1"));
        comments.add(new Commentary("User2","Comment2","1"));
        initRecyclerView(comments);

    }
    private void initRecyclerView(ArrayList<Commentary> comments){
        RecyclerView recyclerView = findViewById(R.id.comments_recyclerView_details);
        CommentRecyclerViewAdapter adapter = new CommentRecyclerViewAdapter(comments,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void toggleComments(View view) {
        final RecyclerView recycler = findViewById(R.id.comments_recyclerView_details);
        final ConstraintLayout layout = findViewById(R.id.commentLayout_details);
        ViewGroup.LayoutParams params=recycler.getLayoutParams();
        ViewGroup.LayoutParams paramsText=layout.getLayoutParams();
        TextView button = findViewById(R.id.comments_toggle_details);
        String text = (String) button.getText();
        if(text == "Hide comments"){
            button.setText("Show comments");
            params.height=1;
            paramsText.height=1;
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }else{
            button.setText("Hide comments");
            params.height=450;
            paramsText.height= ViewGroup.LayoutParams.WRAP_CONTENT;

        }

        recycler.setLayoutParams(params);
        layout.setLayoutParams(paramsText);
    }

}
