package com.example.justcook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;
import com.example.justcook.model.User;

public class EditRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Odebranie przepisu
        Intent intent = getIntent();
        User user = new User(
                intent.getStringExtra("userId"),
                intent.getStringExtra("username"),
                intent.getStringExtra("email")
        );

        RecipeBook recipe = new RecipeBook(
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

        //Setup spinnerów
        Spinner spinner = findViewById(R.id.difficulty_spinner_edit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficulty, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinnerType = findViewById(R.id.type_spinner_edit);
        adapter = ArrayAdapter.createFromResource(this, R.array.types, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);



        initRecipeDetails(recipe);

    }

    public void ingredientAdd(String content) {
        LinearLayout ll = findViewById(R.id.ingreduints_linearLayout_edit);
        View v = LayoutInflater.from(this).inflate(R.layout.ingredient_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ((EditText) v).setText(content);
        ll.addView(v);
    }

    public void stepAdd(String content) {
        LinearLayout ll = findViewById(R.id.steps_linearLayout_edit);
        View v = LayoutInflater.from(this).inflate(R.layout.step_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ((EditText) v).setText(content);
        ll.addView(v);
    }

    public void editDatabaseRecipe(View view) {
    }

    public void initRecipeDetails(RecipeBook recipe){
        String databaseDelimiter = getString(R.string.databaseDelimiter);
        ((EditText)findViewById(R.id.name_editText_edit)).setText(recipe.getName());
        Spinner spinner = findViewById(R.id.difficulty_spinner_edit);
        Spinner spinnerType = findViewById(R.id.type_spinner_edit);
        switch (recipe.getDifficulty()){
            case ("Easy"):
                spinner.setSelection(0);
                break;
            case ("Intermediate"):
                spinner.setSelection(1);
                break;
            case ("Challenging"):
                spinner.setSelection(2);
                break;
        }
        switch (recipe.getType()){
            case ("Main course"):
                spinnerType.setSelection(0);
                break;
            case ("Dessert"):
                spinnerType.setSelection(1);
                break;
            case ("Soup"):
                spinnerType.setSelection(2);
                break;
        }
        String[] ingr = recipe.getIngredients().split(databaseDelimiter);
        String[] steps = recipe.getRecipe().split(databaseDelimiter);
        for (String s:ingr) {
            ingredientAdd(s);
        }
        for (String s:steps){
            stepAdd(s);
        }
    }

    public void ingredientAddCenter(View view) {
        view.setAlpha(0f);
        view.setEnabled(false);
        LinearLayout ll = findViewById(R.id.ingreduints_linearLayout_edit);
        Button left = findViewById(R.id.ingredient_left_minus_edit);
        left.setAlpha(1f);
        left.setEnabled(true);
        Button right = findViewById(R.id.ingredient_right_plus_edit);
        right.setAlpha(1f);
        right.setEnabled(true);
        View v = LayoutInflater.from(this).inflate(R.layout.ingredient_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
    }

    public void ingredientAddLeft(View view) {
        LinearLayout ll = findViewById(R.id.ingreduints_linearLayout_edit);
        if(ll.getChildCount()==1){
            Button center = findViewById(R.id.ingredient_center_plus_edit);
            center.setAlpha(1f);
            center.setEnabled(true);
            view.setAlpha(0f);
            view.setEnabled(false);
            Button right = findViewById(R.id.ingredient_right_plus_edit);
            right.setAlpha(0f);
            right.setEnabled(false);
        }
        ll.removeViewAt(ll.getChildCount()-1);
    }

    public void ingredientAddRight(View view) {
        LinearLayout ll = findViewById(R.id.ingreduints_linearLayout_edit);
        View v = LayoutInflater.from(this).inflate(R.layout.ingredient_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
    }

    public void stepAddCenter(View view) {
        view.setAlpha(0f);
        view.setEnabled(false);
        LinearLayout ll = findViewById(R.id.steps_linearLayout_edit);
        Button left = findViewById(R.id.steps_left_minus_edit);
        left.setAlpha(1f);
        left.setEnabled(true);
        Button right = findViewById(R.id.steps_right_plus_edit);
        right.setAlpha(1f);
        right.setEnabled(true);
        View v = LayoutInflater.from(this).inflate(R.layout.step_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
    }

    public void stepAddRight(View view) {
        LinearLayout ll = findViewById(R.id.steps_linearLayout_edit);
        View v = LayoutInflater.from(this).inflate(R.layout.ingredient_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
    }

    public void stepAddLeft(View view) {
        LinearLayout ll = findViewById(R.id.steps_linearLayout_edit);
        if(ll.getChildCount()==1){
            Button center = findViewById(R.id.steps_center_plus_edit);
            center.setAlpha(1f);
            center.setEnabled(true);
            view.setAlpha(0f);
            view.setEnabled(false);
            Button right = findViewById(R.id.steps_right_plus_edit);
            right.setAlpha(0f);
            right.setEnabled(false);
        }
        ll.removeViewAt(ll.getChildCount()-1);
    }
    public void editRecipeInDatabase(View view) {
        //GOTOWE DANE DO DODANIA vvvvvvvvvvvvvvvv
        String recipeName = ((EditText) findViewById(R.id.name_editText_edit)).getText().toString();
        String recipeDifficulty = ((Spinner) findViewById(R.id.difficulty_spinner_edit)).getSelectedItem().toString();
        String recipeType = ((Spinner) findViewById(R.id.type_spinner_edit)).getSelectedItem().toString();
        String recipeIngredients = "";
        String recipeSteps = "";
        //KONIEC GOTOWYCH DANYCH DO DODANIA^^^^^^
        LinearLayout ingredientLayout = findViewById(R.id.ingreduints_linearLayout_edit);
        LinearLayout stepsLayout = findViewById(R.id.steps_linearLayout_edit);
        if(ingredientLayout.getChildCount()<1){
            Toast.makeText(this, "Recipe needs at least 1 ingredient", Toast.LENGTH_SHORT).show();
        }else if(stepsLayout.getChildCount()<1){
            Toast.makeText(this, "Recipe needs at least 1 step", Toast.LENGTH_SHORT).show();
        }else{
            String databaseDelimiter = getString(R.string.databaseDelimiter);

            for(int i =0; i< ingredientLayout.getChildCount(); i++){
                recipeIngredients += ((EditText) ingredientLayout.getChildAt(i)).getText().toString();
                recipeIngredients += databaseDelimiter;
            }
            Log.d("Ingredients: ", recipeIngredients);

            for(int i=0; i< stepsLayout.getChildCount();i++){
                recipeSteps += ((EditText) stepsLayout.getChildAt(i)).getText().toString();
                recipeSteps += databaseDelimiter;
            }
            Log.d("Steps: ", recipeSteps);
            //TODO: EDYCJA WARTOŚCI W BAZIE
            //TUTAJ EDYCJA DANYCH W BAZIE
            //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            //Po upewnieniu się że działa, odkomentować vvvvv
//            finish();
//            Toast.makeText(this, "Your recipe will be changed soon", Toast.LENGTH_SHORT).show();
        }

    }
}
