package com.example.edubotv2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pdfview.PDFView;

public class PantallaVisualizarPdf7 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_visualizar_pdf7);

        PDFView pdfView = findViewById(R.id.vistaPdf7);
        pdfView.fromAsset("libro-comprension7.pdf")
                .show();
    }
}