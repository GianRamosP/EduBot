package com.example.edubotv2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pdfview.PDFView;

public class PantallaVisualizarPdf2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_visualizar_pdf2);

        PDFView pdfView = findViewById(R.id.vistaPdf2);
        pdfView.fromAsset("libro-comprension2.pdf")
                .show();
    }
}