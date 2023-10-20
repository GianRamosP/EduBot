package com.example.edubotv2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PantallaGestionLibro extends AppCompatActivity {

    private String fileUrl = "https://firebasestorage.googleapis.com/v0/b/my-application-52c41.appspot.com/o/libro-comprension.pdf?alt=media&token=daaf485a-1d67-4a7b-941a-eb3e90be55d1";
    private String fileName = "libro-comunicacion.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_gestionar_libros);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Agrega libros a la tabla
        addRowToTable(tableLayout, "Libro Comunicacion");
        addRowToTable(tableLayout, "Libro Comprensión Lectora");
    }

    private void addRowToTable(TableLayout tableLayout, String title) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);

        TextView textViewTitle = new TextView(this);
        textViewTitle.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textViewTitle.setPadding(8, 8, 8, 8);
        textViewTitle.setText(title);
        textViewTitle.setGravity(Gravity.CENTER);

        Button buttonView = new Button(this);
        buttonView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonView.setPadding(8, 8, 8, 8);
        buttonView.setText("Visualizar");
        buttonView.setGravity(Gravity.CENTER);
        buttonView.setOnClickListener(view -> leerPDF());

        Button buttonDownload = new Button(this);
        buttonDownload.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonDownload.setPadding(8, 8, 8, 8);
        buttonDownload.setText("Descargar");
        buttonDownload.setGravity(Gravity.CENTER);
        buttonDownload.setOnClickListener(view -> {
            downloadPDF();
        });

        row.addView(textViewTitle);
        row.addView(buttonView);
        row.addView(buttonDownload);

        tableLayout.addView(row);
    }

    private void leerPDF() {
        // Código para abrir la pantalla de visualización de PDF
        Intent intent = new Intent(PantallaGestionLibro.this, PantallaVisualizarPdf.class);
        startActivity(intent);
    }

    private void downloadPDF() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                startDownload();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            startDownload();
        }
    }

    private void startDownload() {
        String directorioPDFs = Environment.getExternalStorageDirectory().getPath() + "/EduBot/pdfs/";

        File directorio = new File(directorioPDFs);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        String filePath = directorioPDFs + fileName;

        PDFDownloader downloader = new PDFDownloader();
        downloader.execute(fileUrl, filePath);
    }

    private class PDFDownloader extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            String fileUrl = strings[0];
            String filePath = strings[1];

            try {
                URL url = new URL(fileUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream input = new BufferedInputStream(connection.getInputStream());
                FileOutputStream output = new FileOutputStream(filePath);

                byte[] data = new byte[1024];
                int count;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
                return true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                showToast("PDF descargado exitosamente");
            } else {
                showToast("Error al descargar el PDF");
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
