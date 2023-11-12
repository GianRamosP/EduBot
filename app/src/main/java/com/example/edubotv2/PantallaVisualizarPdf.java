package com.example.edubotv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.pdfview.PDFView;


public class PantallaVisualizarPdf extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_visualizar_pdf);

        PDFView pdfView = findViewById(R.id.vistaPdf);
        pdfView.fromAsset("libro-comprension.pdf")
                .show();
    }
}