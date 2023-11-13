package com.example.edubotv2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pdfview.PDFView;

public class PantallaVisualizarPdf5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_visualizar_pdf5);

        PDFView pdfView = findViewById(R.id.vistaPdf5);
        pdfView.fromAsset("libro-comprension5.pdf")
                .show();
    }
}