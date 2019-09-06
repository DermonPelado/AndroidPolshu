package com.dermonzeky.tpmovies;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    FragmentManager managerFragments;
    FragmentTransaction transacFragments;
    Fragment fragmentInicio, fragmentMovies;
    Button btnVolverInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerReferencias();
        setearListeners();
        mostrarLayoutInicio(); // Al principio muestro el Fragment Inicio
    }

    public void obtenerReferencias() {
        btnVolverInicio = findViewById(R.id.btnVolverInicio);
    }

    public void setearListeners() {
        btnVolverInicio.setOnClickListener(btnVolverInicio_Click);
    }

    public View.OnClickListener btnVolverInicio_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mostrarLayoutMovies();
        }
    };

    public void mostrarLayoutInicio() {
        managerFragments = getFragmentManager();
        fragmentInicio = new FragmentInicio();
        transacFragments = managerFragments.beginTransaction();
        transacFragments.replace(R.id.AlojadorDeFragments, fragmentInicio);
        transacFragments.commit();
    }

    public void mostrarLayoutMovies() {
        fragmentMovies = new FragmentMovies();
        //((FragmentNombre) fragmentNombre).opcionElegida = "nombre";
        transacFragments = managerFragments.beginTransaction();
        transacFragments.replace(R.id.AlojadorDeFragments, fragmentMovies);
        transacFragments.commit();
    }
    /*
    public void mostrarLayoutBuscar() {
        fragmentBuscar = new FragmentBuscar();
        //((FragmentNombre) fragmentNombre).opcionElegida = "nombre";
        transacFragments = managerFragments.beginTransaction();
        transacFragments.replace(R.id.AlojadorDeFragments, fragmentBuscar);
        transacFragments.commit();
    }*/
}
