package com.example.edubotv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PantallaInicioSesion extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_iniciosesion);

        editTextEmail = findViewById(R.id.eTxtEmailLogin);
        editTextPassword = findViewById(R.id.eTxtContraseniaLogin);
        Button btnLogin = findViewById(R.id.btniniciosesion);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        Toast.makeText(PantallaInicioSesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        // Habilitar la persistencia de la sesión para mantener al usuario conectado
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            currentUser.getIdToken(true); // Refresca el token de autenticación para mantener la sesión activa
                        }

                        // Redirigir a la pantalla principal después del inicio de sesión
                        Intent intent = new Intent(PantallaInicioSesion.this, PantallaPrincipal.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual para que el usuario no pueda volver atrás
                    } else {
                        // Si el inicio de sesión falla, muestra un mensaje al usuario
                        Toast.makeText(PantallaInicioSesion.this, "Fallo en el inicio de sesión. Por favor, verifica tus credenciales.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
