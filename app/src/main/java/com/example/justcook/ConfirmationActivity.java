package com.example.justcook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        //TODO: edytowanie zawartości @+id/username_TextView_confirmation na nazwę zalogowanego użytkownia
    }

    public void confirmationContinue(View view) {
        //TODO: przeniesienia użytkownika do głównego activity użytkowego aplikacji
    }
}
