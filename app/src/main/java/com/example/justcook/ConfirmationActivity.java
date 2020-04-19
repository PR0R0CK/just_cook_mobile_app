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
        //W nazewnictwie zmiennych używaj cammelCase
        // "username_TextView_confirmation" - źle
        // "username_textView_confirmation" - dobrze
        // Drobny szczegół, ale razi. Popraw w activity_confirmation
    }

    public void confirmationContinue(View view) {
        //TODO: przeniesienia użytkownika do głównego activity użytkowego aplikacji
    }
}
