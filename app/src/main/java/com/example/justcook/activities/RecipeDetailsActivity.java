package com.example.justcook.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.Commentary;
import com.example.justcook.model.RecipeBook;
import com.example.justcook.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private ArrayList<RecipeBook> recipesBook = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mDatabase = FirebaseDatabase.getInstance();

        //PLACEHOLDER KOMENTARZY
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
        //KONIEC PLACEHOLDERA KOMENTARZY
        initRecyclerView(comments);

        ////////////////////////////////////////
        //ODEBRANIE INFORMACJI O PRZEPISIE
        ////////////////////////////////////////
        Intent intent = getIntent();
        RecipeBook recipe = new RecipeBook(

        );
        //TODO: zmienic przekazywanie
//        RecipeBook recipe = new RecipeBook(
//                intent.getStringExtra("userId"),
//                intent.getStringExtra("recipeId"),
//                intent.getStringExtra("name"),
//                intent.getStringExtra("type"),
//                intent.getStringExtra("picture"),
//                intent.getStringExtra("ingredients"),
//                intent.getStringExtra("recipe"),
//                intent.getStringExtra("difficulty"),
//                intent.getStringExtra("rate")
//        );


        TextView recipeName = findViewById(R.id.title_textView_details);
        recipeName.setText(recipe.getName());

        //DODANIE SKŁADNIKÓW I KROKÓW PRZEPISU
        //KAŻDE UŻYCIE FUNKCJI DODAJE 1 TEXTVIEW Z 1 SKŁADNIKIEM / 1 KROKIEM PRZEPISU
        appendIngredient(recipe.getIngredients());
        appendIngredient("drugi składnik");
        appendStep(recipe.getRecipe());
        appendStep("drugi krok");


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

    public void sendComment(View view) {
        //TODO: TUTAJ NALEŻY WSTAWIĆ LOGIKĘ WSTAWIANIA KOMENTARZA DO BAZY DANYCH
        EditText editText = findViewById(R.id.comment_EditText_details);
        String comment = editText.getText().toString(); //TODO: GOTOWA ZAWARTOŚĆ KOMENTARZA
        editText.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        Toast toast = Toast.makeText(getApplicationContext(), "Your comment is being sent", Toast.LENGTH_SHORT);
        toast.show();
    }
    //ABY DODAĆ SKŁADNIK / KROK PRZEPISU NALEŻY UŻYWAĆ TYLKO I WYŁĄCZNIE TYCH 2 FUNKCJI
    public void appendIngredient(String ingredient){
        TextView tv = new TextView(new ContextThemeWrapper(this, R.style.ingredient_style), null, 0);
        tv.setText(ingredient);
        LinearLayout ingredientsLayout = findViewById(R.id.ingredients_linearLayout_details);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,16);
        tv.setLayoutParams(params);
        ingredientsLayout.addView(tv);
    }
    public void appendStep(String step){
        TextView tv = new TextView(new ContextThemeWrapper(this, R.style.step_style), null, 0);
        tv.setText(step);
        LinearLayout stepsLayout = findViewById(R.id.steps_linearLayout_details);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,24);
        tv.setLayoutParams(params);
        stepsLayout.addView(tv);
    }
    @Override
    public void onBackPressed() {
        TextView textView = findViewById(R.id.comments_toggle_details);
        String val = textView.getText().toString();
        if(val == "Hide comments"){
            toggleComments(textView);
        }else{
            super.onBackPressed();
        }
    }
}
