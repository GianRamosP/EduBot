package com.example.edubotv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class PreguntasAdapter extends ArrayAdapter<String> {

    private ArrayList<String> preguntasList;
    private Context context;

    public PreguntasAdapter(Context context, ArrayList<String> preguntasList) {
        super(context, 0, preguntasList);
        this.context = context;
        this.preguntasList = preguntasList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pregunta, parent, false);
        }

        // Obtiene la pregunta en la posición actual
        String pregunta = preguntasList.get(position);

        // Actualiza el TextView con la pregunta
        TextView textViewPregunta = convertView.findViewById(R.id.textViewPregunta);
        textViewPregunta.setText(pregunta);

        // Puedes hacer lo mismo para otros elementos del diseño aquí si es necesario

        return convertView;
    }
}
