package com.example.justcook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.content.res.Resources;
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

public class NewRecipeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //Ustawienie śmigła poziomu trudności
        Spinner spinner = findViewById(R.id.difficulty_spinner_new);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficulty, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinnerType = findViewById(R.id.type_spinner_new);
        adapter = ArrayAdapter.createFromResource(this, R.array.types, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

//        ingredientAddCenter(findViewById(R.id.ingredient_center_plus));
//        stepAddCenter(findViewById(R.id.steps_center_plus));


    }

    public void ingredientAddCenter(View view) {
        Log.d("test", "center");
        LinearLayout ll = findViewById(R.id.ingreduints_linearLayout_new);
        view.setAlpha(0f);
        view.setEnabled(false);
        View v = LayoutInflater.from(this).inflate(R.layout.ingredient_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
        Button left = findViewById(R.id.ingredient_left_minus);
        left.setAlpha(1f);
        left.setEnabled(true);
        Button right = findViewById(R.id.ingredient_right_plus);
        right.setAlpha(1f);
        right.setEnabled(true);
        //ll.removeViewAt(0);
    }

    public void ingredientAddRight(View view) {
        Log.d("test", "right");
        LinearLayout ll = findViewById(R.id.ingreduints_linearLayout_new);
        View v = LayoutInflater.from(this).inflate(R.layout.ingredient_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
    }

    public void ingredientAddLeft(View view) {
        Log.d("test", "left");
        LinearLayout ll = findViewById(R.id.ingreduints_linearLayout_new);
        if(ll.getChildCount()==1){
            Button center = findViewById(R.id.ingredient_center_plus);
            center.setAlpha(1f);
            center.setEnabled(true);
            view.setAlpha(0f);
            view.setEnabled(false);
            Button right = findViewById(R.id.ingredient_right_plus);
            right.setAlpha(0f);
            right.setEnabled(false);
        }
        ll.removeViewAt(ll.getChildCount()-1);
    }
    public void stepAddCenter(View view) {
        Log.d("test", "center");
        LinearLayout ll = findViewById(R.id.steps_linearLayout_new);
        view.setAlpha(0f);
        view.setEnabled(false);
        View v = LayoutInflater.from(this).inflate(R.layout.step_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
        Button left = findViewById(R.id.steps_left_minus);
        left.setAlpha(1f);
        left.setEnabled(true);
        Button right = findViewById(R.id.steps_right_plus);
        right.setAlpha(1f);
        right.setEnabled(true);
        //ll.removeViewAt(0);
    }

    public void stepAddRight(View view) {
        Log.d("test", "right");
        LinearLayout ll = findViewById(R.id.steps_linearLayout_new);
        View v = LayoutInflater.from(this).inflate(R.layout.step_input_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        v.setLayoutParams(params);
        ll.addView(v);
    }

    public void stepAddLeft(View view) {
        Log.d("test", "left");
        LinearLayout ll = findViewById(R.id.steps_linearLayout_new);
        if(ll.getChildCount()==1){
            Button center = findViewById(R.id.steps_center_plus);
            center.setAlpha(1f);
            center.setEnabled(true);
            view.setAlpha(0f);
            view.setEnabled(false);
            Button right = findViewById(R.id.steps_right_plus);
            right.setAlpha(0f);
            right.setEnabled(false);
        }
        ll.removeViewAt(ll.getChildCount()-1);
    }

    public void addNewRecipeToDatabase(View view) {
        //GOTOWE DANE DO DODANIA vvvvvvvvvvvvvvvv
        String recipeName = ((EditText) findViewById(R.id.name_editText_new)).getText().toString();
        String recipeDifficulty = ((Spinner) findViewById(R.id.difficulty_spinner_new)).getSelectedItem().toString();
        String recipeType = ((Spinner) findViewById(R.id.type_spinner_new)).getSelectedItem().toString();
        String recipeIngredients = new String();
        String recipeSteps = new String();
        //KONIEC GOTOWYCH DANYCH DO DODANIA^^^^^^
        LinearLayout ingredientLayout = findViewById(R.id.ingreduints_linearLayout_new);
        LinearLayout stepsLayout = findViewById(R.id.steps_linearLayout_new);
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
            recipeIngredients = recipeIngredients.substring(0,recipeIngredients.length() -1);
            Log.d("Ingredients: ", recipeIngredients);

            for(int i=0; i< stepsLayout.getChildCount();i++){
                recipeSteps += ((EditText) stepsLayout.getChildAt(i)).getText().toString();
                recipeSteps += databaseDelimiter;
            }
            recipeSteps = recipeSteps.substring(0,recipeIngredients.length() -1);
            Log.d("Steps: ", recipeSteps);
            //TODO: DODANIE WARTOŚCI DO BAZY
            //AREK, JAK CHCESZ DODAWAĆ DO BAZY TO W TYM MIEJSCU
            //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        }

    }
}
