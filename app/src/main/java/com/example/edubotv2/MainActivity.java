package com.example.edubotv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        Button btnRegister = findViewById(R.id.btnregister);
        Button btnLogin = findViewById(R.id.btnlogin);

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PantallaRegistro.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PantallaInicioSesion.class);
            startActivity(intent);
        });
    }
}