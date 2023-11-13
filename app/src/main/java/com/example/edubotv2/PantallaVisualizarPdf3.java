package com.example.edubotv2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pdfview.PDFView;

public class PantallaVisualizarPdf3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_visualizar_pdf3);

        PDFView pdfView = findViewById(R.id.vistaPdf3);
        pdfView.fromAsset("libro-comprension3.pdf")
                .show();
    }
}