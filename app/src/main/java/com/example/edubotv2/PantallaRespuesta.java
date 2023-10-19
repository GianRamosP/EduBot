package com.example.edubotv2;

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

public class PantallaRespuesta extends AppCompatActivity {

    private EditText eTxtRespuesta;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_respuesta);

        eTxtRespuesta = findViewById(R.id.eTxtRespuesta);
        Button btnEnviarRespuesta = findViewById(R.id.btnEnviarRespuesta);

        db = FirebaseFirestore.getInstance();

        btnEnviarRespuesta.setOnClickListener(view -> enviarRespuesta());
    }

    private void enviarRespuesta() {
        String respuesta = eTxtRespuesta.getText().toString();

        // Verificar si la respuesta no está vacía
        if (!respuesta.isEmpty()) {
            // Obtener el ID del usuario actual
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String userId = user.getUid();

                // Crear un mapa con los datos de la respuesta
                Map<String, Object> respuestaData = new HashMap<>();
                respuestaData.put("respuesta", respuesta);
                respuestaData.put("userId", userId); // Asociar la respuesta con el ID del usuario

                // Subir respuestas a Firestore
                db.collection("respuestas")
                        .add(respuestaData)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(PantallaRespuesta.this, "Respuesta enviada correctamente", Toast.LENGTH_SHORT).show();
                                eTxtRespuesta.setText("");
                            } else {
                                Toast.makeText(PantallaRespuesta.this, "Error al enviar la respuesta", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                // Por seguridad, verificar si el usuario está autenticado antes de enviar una respuesta
                Toast.makeText(PantallaRespuesta.this, "Por favor, inicia sesión antes de enviar una respuesta.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa una respuesta antes de enviarla.", Toast.LENGTH_SHORT).show();
        }
    }
}
