package com.example.edubotv2;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class PantallaFrecuentes extends AppCompatActivity {

    private ListView listViewPreguntas;
    private ArrayList<String> preguntasList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_frecuentes);

        // Inicializar Firebase Authentication
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            // Inicializar el ListView
            listViewPreguntas = findViewById(R.id.lvPreguntas);
            preguntasList = new ArrayList<>();

            // Obtener preguntas del usuario desde Firestore
            obtenerPreguntasUsuario(userId);
        } else {
            // El usuario no está autenticado, maneja este caso según tus necesidades
        }
    }

    private void obtenerPreguntasUsuario(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference preguntasRef = db.collection("preguntas");

        preguntasRef.whereEqualTo("userId", userId).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Se han obtenido las preguntas del usuario
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Recupera las preguntas del documento
                        String pregunta = document.getString("pregunta");
                        // Agrega `pregunta` a tu lista de preguntas
                        preguntasList.add(pregunta);
                    }

                    // Crea una instancia del adaptador personalizado
                    PreguntasAdapter adapter = new PreguntasAdapter(this, preguntasList);

                    // Configura el adaptador en el ListView
                    listViewPreguntas.setAdapter(adapter);
                })
                .addOnFailureListener(e -> {
                    // Maneja errores aquí
                });
    }

}

