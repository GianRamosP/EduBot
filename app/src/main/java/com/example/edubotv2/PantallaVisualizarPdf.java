package com.example.edubotv2;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.joanzapata.pdfview.PDFView;
import java.io.File;

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
        // Ruta al directorio donde se almacenan los PDFs
        String rutaPDFs = Environment.getExternalStorageDirectory().getPath() + "/EduBot/pdfs/";

        // Nombre del archivo PDF que deseas mostrar
        String nombrePDF = "libro-comunicacion.pdf";

        // Ruta completa al archivo PDF
        String filePath = rutaPDFs + nombrePDF;
        File file = new File(filePath);

        if (file.exists()) {
            pdfView.fromFile(file)
                    .defaultPage(0)
                    .onLoad(nbPages -> Log.d("PDFView", "Carga completa: " + nbPages + " páginas cargadas"))
                    .load();
        } else {
            Log.e("PDFView", "El archivo PDF no existe en la ruta especificada: " + filePath);
        }
    }
}
