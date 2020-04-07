package com.example.justcook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText email = findViewById(R.id.email_editText_login);
        EditText password = findViewById(R.id.password_editText_login);
        Button register = findViewById(R.id.register_button_login);

        String testEmail = email.getText().toString();
        String testPassword = password.getText().toString();

        Log.d("MainActivit","Email: "+testEmail);
        Log.d("MainActivit","Email: "+testPassword);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                Log.d("MainActivit","Button was clicked: "+counter + " times");
            }
        });

        TextView alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount_textView_register);
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivit","LogIn Activity");
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
