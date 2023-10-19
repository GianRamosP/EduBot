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

public class PantallaRegistro extends AppCompatActivity {

    EditText eTxtUsuario, eTxtContrasenia, eTxtContrasenia2, eTxtEmail;
    Button btnRegistro;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registro);

        eTxtUsuario = findViewById(R.id.eTxtUsuario);
        eTxtContrasenia = findViewById(R.id.eTxtContrasenia);
        eTxtContrasenia2 = findViewById(R.id.eTxtContrasenia2);
        eTxtEmail = findViewById(R.id.eTxtEmail);
        btnRegistro = findViewById(R.id.btnregistro);
        mAuth = FirebaseAuth.getInstance();

        btnRegistro.setOnClickListener(view -> registrarUsuario());
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private boolean validarEntrada(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            mostrarMensaje("Por favor, completa todos los campos.");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            mostrarMensaje("Las contraseñas no coinciden.");
            return false;
        }
        // Espacio en caso de que falte agregar mas validaciones (Agregar HASH)
        // Formato de correo
        // Formato de la contra
        return true;
    }

    private void registrarUsuario() {
        String email = eTxtEmail.getText().toString();
        String password = eTxtContrasenia.getText().toString();
        String confirmPassword = eTxtContrasenia2.getText().toString();

        if (!validarEntrada(email, password, confirmPassword)) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            String nombreUsuario = eTxtUsuario.getText().toString();

                            Map<String, Object> userData = new HashMap<>();
                            userData.put("nombreUsuario", nombreUsuario);
                            userData.put("email", email);

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("usuarios").document(userId)
                                    .set(userData)
                                    .addOnSuccessListener(aVoid -> {
                                        mostrarMensaje("Datos del usuario guardados en Firestore");

                                        Intent intent = new Intent(PantallaRegistro.this, PantallaPrincipal.class);
                                        startActivity(intent);
                                        finish();
                                    })
                                    .addOnFailureListener(e -> mostrarMensaje("Error al guardar datos del usuario en Firestore"));
                        }
                    } else {
                        // Si falla Registro
                        // Me manda cual es el error
                        String errorMessage = task.getException().getMessage();
                        mostrarMensaje("Fallo en el registro: " + errorMessage);
                    }
                });
    }
}