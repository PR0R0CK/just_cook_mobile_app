package com.example.justcook.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.Commentary;
import com.example.justcook.model.RecipeBook;
import com.example.justcook.model.User;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class RecipeDetailsActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private ArrayList<RecipeBook> recipesBook = new ArrayList<>();
    private RecipeBook recipe;

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
        User user = new User(
                intent.getStringExtra("userId"),
                intent.getStringExtra("username"),
                intent.getStringExtra("email")
        );

        recipe = new RecipeBook(
                user,
                intent.getStringExtra("recipeId"),
                intent.getStringExtra("name"),
                intent.getStringExtra("type"),
                intent.getStringExtra("picture"),
                intent.getStringExtra("ingredients"),
                intent.getStringExtra("recipe"),
                intent.getStringExtra("difficulty"),
                intent.getStringExtra("rate")
        );


        ((TextView) findViewById(R.id.title_textView_details)).setText(recipe.getName());
        //DODANIE SKŁADNIKÓW I KROKÓW DO ODPOWIADAJĄCYCH IM LAYOUTÓW
        insertDetails(recipe.getIngredients(),recipe.getRecipe());
        ((Button)findViewById(R.id.like_button_details)).setText(recipe.getRate());

        //TODO: DODAĆ SPRAWDZANIE CZY OBECNY UŻYTKOWNIK JEST WŁAŚCICIELEM
        enableEditing();
    }

    private void insertDetails(String ingredients, String recipe) {
        String databaseDelimiter = getString(R.string.databaseDelimiter);
        String[] ingr = ingredients.split(databaseDelimiter);
        String[] steps = recipe.split(databaseDelimiter);
        Log.d("Test: ",ingr[0]);
        for(int i=0; i<ingr.length; i++){
            appendIngredient(ingr[i]);
        }
        for(int i=0; i<steps.length; i++){
            appendStep(steps[i]);
        }
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

    public void likeRecipe(View view) {
        Button button = findViewById(R.id.like_button_details);
        int likes = Integer.parseInt(String.valueOf(button.getText()));
        //TODO: AKTUALIZACJA POLUBIEŃ W BAZIE, PIERWSZE - NOWE POLUBIENIE, DRUGIE - ANULOWANIE POLUBIENIA
        if(button.getTag().equals("0")){
            button.setText(String.valueOf(likes+1));
            button.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.btn_star_big_on,0,0,0);
            button.setTag("1");
            Toast.makeText(this, "You like this recipe", Toast.LENGTH_SHORT).show();
        }else if(button.getTag().equals("1")){
            button.setText(String.valueOf(likes-1));
            button.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.btn_star,0,0,0);
            button.setTag("0");
            Toast.makeText(this, "You no longer like this recipe", Toast.LENGTH_SHORT).show();
        }
        timeout(view);


    }

    private void timeout(final View view) {
        view.setEnabled(false);
        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        view.setEnabled(true);
                    }
                });
            }
        }, 1000);
    }

    public void enableEditing(){
        findViewById(R.id.edit_button_details).setVisibility(View.VISIBLE);
        findViewById(R.id.delete_button_details).setVisibility(View.VISIBLE);
    }

    public void editRecipe(View view) {
        Intent intent = new Intent(this, EditRecipeActivity.class);
        //TODO: putExtra
        intent.putExtra("userId",recipe.getUser().getUserId());
        intent.putExtra("username", recipe.getUser().getUsername());
        intent.putExtra("email",recipe.getUser().getEmail());
        intent.putExtra("recipeId", recipe.getRecipeId());
        intent.putExtra("name", recipe.getName());
        intent.putExtra("type", recipe.getType());
        intent.putExtra("picture", recipe.getPicture());
        intent.putExtra("ingredients", recipe.getIngredients());
        intent.putExtra("userId", recipe.getUser().getUsername());
        intent.putExtra("recipe", recipe.getRecipe());
        intent.putExtra("difficulty", recipe.getDifficulty());
        intent.putExtra("rate", recipe.getRate());
        startActivity(intent);
    }

    public void deleteRecipe(View view) {
        Toast.makeText(this, "usuniecie trza zrobic dopiero Pepega", Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder alert = new AlertDialog.Builder(RecipeDetailsActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.delete_dialog,null);
        Button delete = (Button)mView.findViewById(R.id.delete_dialog);
        Button cancel = (Button)mView.findViewById(R.id.abort_dialog);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecipeDetailsActivity.this, "*usunięcie przepisu tutaj*", Toast.LENGTH_SHORT).show();



                alertDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

