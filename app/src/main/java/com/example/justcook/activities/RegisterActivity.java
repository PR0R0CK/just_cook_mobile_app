package com.example.justcook.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;
import com.example.justcook.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private int counter = 0;
    private EditText etUsername,etEmail,etPassword;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    ArrayList<RecipeBook> recipesBook = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.username_editText_register);
        etEmail = findViewById(R.id.email_editText_register);
        etPassword = findViewById(R.id.password_editText_register);


        //Firebase Authentication and Database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

//        saveRecipeToFirebaseDatabase();

        findViewById(R.id.register_button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instantRegister();
            }
        });


        findViewById(R.id.alreadyHaveAccount_textView_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivit","LogIn Activity");
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void instantRegister() {
        counter++;
        final String username = etUsername.getText().toString();
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        boolean isChecked = ((CheckBox) findViewById(R.id.data_checkBox_register)).isChecked();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Empty fields!",Toast.LENGTH_LONG).show();
        } else {
            Log.d("RegisterActivity", "Button was clicked: " + counter + " times");
            Log.d("RegisterActivity", "Username: " + username);
            Log.d("RegisterActivity", "Email: " + email);
            Log.d("RegisterActivity", "Password: " + password);
            Log.d("RegisterActivity", "Checked: " + isChecked);
            if (isChecked) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("REGISTER", "createUserWithEmail:success");
                                    saveUserToFirebaseDatabase(username, email);
                                    startActivity(new Intent(RegisterActivity.this, ConfirmationActivity.class));
                                }
                            }
                        })
                        .addOnFailureListener(RegisterActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("RegisterActivity", "Failed to create user... ", e.getCause());
                                Toast.makeText(RegisterActivity.this, "Failed to create: "
                                        + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            } else {
                Toast.makeText(RegisterActivity.this, "You have to accept privacy policy first.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveUserToFirebaseDatabase(String username, String email) {
        String userId = FirebaseAuth.getInstance().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/users/"+userId);

        User user = new User(userId, username, email);

        ref.setValue(user)
                .addOnSuccessListener(RegisterActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("RegisterActivity","User has been saved to the FirebaseDatabase!!");
                    }
                })
                .addOnFailureListener(RegisterActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("RegisterActivity","The user has not been saved to the FirebaseDatabase!!");
                    }
                });
    }


    private void addRecipeToFirebaseDatabase(RecipeBook recipeBook){
        DatabaseReference ref = mDatabase.getReference("/recipes");
        String recipeId = ref.push().getKey();

        recipeBook.setRecipeId(recipeId);
        recipesBook.add(recipeBook);
        ref.setValue(recipesBook);
    }

    private void saveRecipeToFirebaseDatabase() {
        String userId = FirebaseAuth.getInstance().getUid();

        addRecipeToFirebaseDatabase(new RecipeBook(userId,"0", "Żur","Zupa","zdjZupy","jajko,kiełbasa,woda","Pokroic jajko i kelbase, wlac wode","łatwe","5"));
        addRecipeToFirebaseDatabase(new RecipeBook(userId,"1","Żurek","Zupa","zdjZupy","jajko,kiełbasa,woda","Pokroic jajko i kelbase, wlac wode","łatwe","5"));
        addRecipeToFirebaseDatabase(new RecipeBook(userId,"2","Mur","Drugie danie","zdj","jajko,kiełbasa,woda","Pokroic jajko i kelbase, wlac wode","łatwe","5"));
        addRecipeToFirebaseDatabase(new RecipeBook(userId,"3","Murek","Drugie danie","zdj","jajko,kiełbasa,woda","Pokroic jajko i kelbase, wlac wode","łatwe","5"));
        addRecipeToFirebaseDatabase(new RecipeBook(userId,"4","Siur","Deser","zdj","jajko,kiełbasa,woda","Pokroic jajko i kelbase, wlac wode","trudne","5"));
        addRecipeToFirebaseDatabase(new RecipeBook(userId,"5","Siurek","Deser","zdj","jajko,kiełbasa,woda","Pokroic jajko i kelbase, wlac wode","trudne","5"));

    }
}
