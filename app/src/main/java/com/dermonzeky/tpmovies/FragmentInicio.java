package com.dermonzeky.tpmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;

public class FragmentInicio extends Fragment {
    Spinner spnOpciones;
    Button btnBuscar, btnMirar;

    public View onCreateView(LayoutInflater infladorLayout, ViewGroup grupoVista, Bundle datos) {
        View vistaDevolver = infladorLayout.inflate(R.layout.fragment_inicio, grupoVista, false);
        obtenerReferencias(vistaDevolver);
        setearListeners();
        return vistaDevolver;
    }

    private void obtenerReferencias(View vistaDevolver) {
        btnBuscar = vistaDevolver.findViewById(R.id.btnBuscar);
        btnMirar = vistaDevolver.findViewById(R.id.btnMirar);
    }

    private void setearListeners() {
        btnBuscar.setOnClickListener(btnBuscar_Click);
        btnMirar.setOnClickListener(btnMirar_Click);
    }

    View.OnClickListener btnBuscar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener btnMirar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void eligioMostrarMovies() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.mostrarLayoutMovies();
    }

}