package com.example.edubotv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PantallaPregunta extends AppCompatActivity {

    private EditText eTxtPregunta;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_pregunta);

        eTxtPregunta = findViewById(R.id.etxtpregunta);
        Button btnEnviarPregunta = findViewById(R.id.btnpregunta);

        db = FirebaseFirestore.getInstance();

        btnEnviarPregunta.setOnClickListener(view -> enviarPregunta());
    }

    private void enviarPregunta() {
        String pregunta = eTxtPregunta.getText().toString();

        // Verificar si esta vacio el eTxt
        if (!pregunta.isEmpty()) {
            // Obterner usuario
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                // Pedir ID
                String userId = user.getUid();

                // Mapa con las preugntas
                Map<String, Object> preguntaData = new HashMap<>();
                preguntaData.put("pregunta", pregunta);
                preguntaData.put("userId", userId); // Asociar la pregunta con el ID del usuario

                // Subir preguntas a firestore
                db.collection("preguntas")
                        .add(preguntaData)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(PantallaPregunta.this, "Pregunta enviada correctamente", Toast.LENGTH_SHORT).show();
                                eTxtPregunta.setText("");
                            } else {
                                Toast.makeText(PantallaPregunta.this, "Error al enviar la pregunta", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                // Por seguridad identificar si se esta logeado
                Intent intent = new Intent(PantallaPregunta.this, PantallaInicioSesion.class);
                startActivity(intent);
                finish();
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa una pregunta antes de enviarla.", Toast.LENGTH_SHORT).show();
        }
    }
}
