package com.example.justcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String testEmail = this.<EditText>findViewById(R.id.email_editText_login).getText().toString();
        String testPassword = this.<EditText>findViewById(R.id.password_editText_login).getText().toString();

        Log.d("MainActivit","Email: "+testEmail);
        Log.d("MainActivit","Email: "+testPassword);

        //Firebase Authentication
//        FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.register_button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                Log.d("MainActivit","Button was clicked: "+counter + " times");
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
