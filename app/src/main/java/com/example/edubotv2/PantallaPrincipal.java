package com.example.edubotv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaPrincipal extends AppCompatActivity {
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        Button btnPregunta = findViewById(R.id.btnagregarlibro);
        Button btnVerLibros = findViewById(R.id.btnlibros);
        Button btnFrecuentes = findViewById(R.id.btnfrecuentes);

        btnPregunta.setOnClickListener(view -> {
            //Intent intent = new Intent(PantallaPrincipal.this, PantallaPreguntas.class);
            //startActivity(intent);
        });

        btnVerLibros.setOnClickListener(view -> {
            //Intent intent = new Intent(PantallaPrincipal.this, PantallaGestionLibro.class);
            //startActivity(intent);
        });

        btnFrecuentes.setOnClickListener(view -> {
            //Intent intent = new Intent(PantallaPrincipal.this, PantallaFrecuentes.class);
            //startActivity(intent);
        });
    }



}
