package com.example.justcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private EditText etUsername,etEmail,etPassword;
    private String username,email,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.username_editText_register);
        etEmail = findViewById(R.id.email_editText_register);
        etPassword = findViewById(R.id.password_editText_register);

        username = etUsername.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        Log.d("MainActivit", "Username: " + username);
        Log.d("BEFMainActivitREGISTER", "Email: " + email);
        Log.d("BEFMainActivitREGISTER", "Password: " + password);

        //Firebase Authentication
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        findViewById(R.id.register_button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
//                username = etUsername.getText().toString();
//                email = etEmail.getText().toString();
//                password = etPassword.getText().toString();

                Log.d("MainActivit","Button was clicked: "+counter + " times");
                Log.d("MainActivit", "Username: " + username);
                Log.d("MainActivit", "Email: " + email);
                Log.d("MainActivit", "Password: " + password);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d("MAIN ACTIVITY", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    Log.d("MAIN ACTIVITY","failed !!!!!!!!!");
                                    Toast.makeText(MainActivity.this, "ERROR",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        findViewById(R.id.alreadyHaveAccount_textView_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivit","LogIn Activity");
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
