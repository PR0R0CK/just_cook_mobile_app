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
    private EditText etEmail,etPassword;
    private String email,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail =
        email = this.<EditText>findViewById(R.id.email_editText_login).getText().toString();
        password = this.<EditText>findViewById(R.id.password_editText_login).getText().toString();

        Log.d("BEFMainActivitREGISTER", "Email: " + email);
        Log.d("BEFMainActivitREGISTER", "Password: " + password);

        //Firebase Authentication
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        findViewById(R.id.register_button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                email = this.<EditText>findViewById(R.id.email_editText_login).getText().toString();
                password = this.<EditText>findViewById(R.id.password_editText_login).getText().toString();
                Log.d("MainActivit","Button was clicked: "+counter + " times");
                Log.d("MainActivit", "Email: " + email);
                Log.d("MainActivit", "Password: " + password);

//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//
//                                    Log.d("MAIN ACTIVITY", "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                } else {
//                                    Log.d("MAIN ACTIVITY","failed !!!!!!!!!");
//                                    Toast.makeText(MainActivity.this, "ERROR",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
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
