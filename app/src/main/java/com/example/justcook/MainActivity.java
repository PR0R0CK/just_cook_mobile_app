package com.example.justcook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText email = findViewById(R.id.email_editText_register);
        EditText password = findViewById(R.id.password_editText_register);
        Button register = findViewById(R.id.register_button_register);

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
    }
}
