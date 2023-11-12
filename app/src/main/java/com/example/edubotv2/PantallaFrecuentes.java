package com.example.edubotv2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edubotv2.adapter.FreqAdapter;
import com.example.edubotv2.model.Freq;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class PantallaFrecuentes extends AppCompatActivity {
    RecyclerView mRecycler;
    FreqAdapter mAdapter;
    FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_frecuentes);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recycleViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("preguntas");

        FirestoreRecyclerOptions<Freq> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Freq>().setQuery(query, Freq.class).build();
        mAdapter = new FreqAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.startListening();
    }

}

