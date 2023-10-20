package com.example.edubotv2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.joanzapata.pdfview.PDFView;

public class PantallaVisualizarPdf extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_visualizar_pdf);

        pdfView = findViewById(R.id.pdfView);
        mostrarPDF();
    }

    private void mostrarPDF() {
        pdfView.fromAsset("libro-comunicacion.pdf")
                .defaultPage(0)
                .onLoad(nbPages -> Log.d("PDFView", "Carga completa: " + nbPages + " páginas cargadas"))
                .load();
    }
}
