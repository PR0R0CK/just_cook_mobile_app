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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private int counter = 0;
    private EditText etUsername,etEmail,etPassword;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.username_editText_register);
        etEmail = findViewById(R.id.email_editText_register);
        etPassword = findViewById(R.id.password_editText_register);

        //Firebase Authentication and Database
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

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
            }
        });
    }
    private void instantRegister() {
        counter++;
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
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
//                                          FirebaseUser user = mAuth.getCurrentUser();
//                                    saveUserToFirebaseDatabase(username,email,password);
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
//TODO saveUserToFirebaseDatabase
    private void saveUserToFirebaseDatabase(String username, String email, String password) {

    }
}
