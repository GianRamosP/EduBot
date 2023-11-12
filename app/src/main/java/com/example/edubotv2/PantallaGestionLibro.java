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

    Button btnGenerarPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_gestionar_libros);


        btnGenerarPdf = findViewById(R.id.btnCrearPdf);

        btnGenerarPdf.setOnClickListener(view -> abrirPdf());
    }

    public void abrirPdf() {


            Intent intent = new Intent(this, PantallaVisualizarPdf.class);
            startActivity(intent);






    }
}