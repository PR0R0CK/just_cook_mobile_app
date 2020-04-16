package com.example.justcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private String email,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.email_editText_login);
        etPassword = findViewById(R.id.password_editText_login);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.login_button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                Log.d("###MainActivit", "Email: " + email);
                Log.d("###MainActivit", "Password: " + password);
                Toast.makeText(LoginActivity.this, "Email: "+email+", Password: "+password,
                        Toast.LENGTH_SHORT).show();

                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("###Login","loginWithEmail: success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    verifyingUser(user);
                                } else {
                                    Log.w("###Warning","loginWithEmail: failed",task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        findViewById(R.id.backToRegister_editText_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void verifyingUser(FirebaseUser user) {
        if (user != null) {
            if (user.isEmailVerified()) {
                Log.d("###UserEmail","EmailVerified");
            } else {
                Log.d("###UserEmail","EmailNOTVerified");
            }
        } else {
            Log.d("###NoUser","Incorrect User");
        }
    }
}
