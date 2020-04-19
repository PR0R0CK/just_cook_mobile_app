package com.example.justcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private int counter = 0;
    private EditText etUsername,etEmail,etPassword;
    private String username,email,password;
    private FirebaseAuth mAuth;
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.username_editText_register);
        etEmail = findViewById(R.id.email_editText_register);
        etPassword = findViewById(R.id.password_editText_register);

        //Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.register_button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                isChecked = ((CheckBox)findViewById(R.id.data_checkBox_register)).isChecked();

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
//                                            FirebaseUser user = mAuth.getCurrentUser();
                                            startActivity(new Intent(RegisterActivity.this, ConfirmationActivity.class));
                                        }
                                    }
                                })
                        .addOnFailureListener(RegisterActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("RegisterActivity","Failed to create user... ",e.getCause());
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
}
