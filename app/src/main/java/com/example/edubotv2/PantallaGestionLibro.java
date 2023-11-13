package com.example.edubotv2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.edubotv2.R;

import java.io.File;
import java.io.FileOutputStream;

public class PantallaGestionLibro extends AppCompatActivity {

    Button btnLeerPdf, btnLeerPdf2, btnLeerPdf3, btnLeerPdf4, btnLeerPdf5, btnLeerPdf6, btnLeerPdf7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_gestionar_libros);

        btnLeerPdf = findViewById(R.id.btnCrearPdf);
        btnLeerPdf2 = findViewById(R.id.btnCrearPdf2);
        btnLeerPdf3 = findViewById(R.id.btnCrearPdf3);
        btnLeerPdf4 = findViewById(R.id.btnCrearPdf4);
        btnLeerPdf5 = findViewById(R.id.btnCrearPdf5);
        btnLeerPdf6 = findViewById(R.id.btnCrearPdf6);
        btnLeerPdf7 = findViewById(R.id.btnCrearPdf7);

        btnLeerPdf.setOnClickListener(view -> abrirPdf());
        btnLeerPdf2.setOnClickListener(view -> abrirPdf2());
        btnLeerPdf3.setOnClickListener(view -> abrirPdf3());
        btnLeerPdf4.setOnClickListener(view -> abrirPdf4());
        btnLeerPdf5.setOnClickListener(view -> abrirPdf5());
        btnLeerPdf6.setOnClickListener(view -> abrirPdf6());
        btnLeerPdf7.setOnClickListener(view -> abrirPdf7());
    }

    public void abrirPdf() {
            Intent intent = new Intent(this, PantallaVisualizarPdf.class);
            startActivity(intent);
    }
    public void abrirPdf2() {
            Intent intent = new Intent(this, PantallaVisualizarPdf2.class);
            startActivity(intent);
    }
    public void abrirPdf3() {
            Intent intent = new Intent(this, PantallaVisualizarPdf3.class);
            startActivity(intent);
    }
public void abrirPdf4() {
            Intent intent = new Intent(this, PantallaVisualizarPdf4.class);
            startActivity(intent);
    }
    public void abrirPdf5() {
        Intent intent = new Intent(this, PantallaVisualizarPdf5.class);
        startActivity(intent);
    }
public void abrirPdf6() {
            Intent intent = new Intent(this, PantallaVisualizarPdf6.class);
            startActivity(intent);
    }
public void abrirPdf7() {
            Intent intent = new Intent(this, PantallaVisualizarPdf7.class);
            startActivity(intent);
    }



}