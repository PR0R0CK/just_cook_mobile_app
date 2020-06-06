package com.example.justcook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.Comment;

import java.util.ArrayList;

public class RecipeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
        comments.add(new Comment("User1","Comment1","1"));
        comments.add(new Comment("User2","Comment2","1"));
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

    public void sendComment(View view) {
        //TODO: TUTAJ NALEŻY WSTAWIĆ LOGIKĘ WSTAWIANIA KOMENTARZA DO BAZY DANYCH
        EditText editText = findViewById(R.id.comment_EditText_details);
        String comment = editText.getText().toString(); //TODO: GOTOWA ZAWARTOŚĆ KOMENTARZA
        editText.setText("");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Toast toast = Toast.makeText(getApplicationContext(), "Your comment is being sent", Toast.LENGTH_SHORT);
        toast.show();
    }
}
