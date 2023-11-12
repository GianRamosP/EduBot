package com.example.edubotv2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edubotv2.R;
import com.example.edubotv2.model.Freq;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FreqAdapter extends FirestoreRecyclerAdapter<Freq, FreqAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FreqAdapter(@NonNull FirestoreRecyclerOptions<Freq> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Freq Freq) {
        viewHolder.pregunta.setText(Freq.getPregunta());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_freq_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pregunta;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pregunta = itemView.findViewById(R.id.pregunta);
        }
    }
}
