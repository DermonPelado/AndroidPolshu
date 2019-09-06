package com.dermonzeky.tpmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentMovies extends Fragment {

    private String opcionElegida = "";
    private ListView lstCategorias;
    private TextView txvMensajes;
    private ArrayList<String> listaCategoriasTraidas;
    private MovieAdapter adapter;

    public View onCreateView(LayoutInflater infladorLayout, ViewGroup grupoVista, Bundle datos) {
        View vistaDevolver = infladorLayout.inflate(R.layout.fragment_movies, grupoVista, false);
        obtenerReferencias(vistaDevolver);
        setearListeners();
        llamarTareaAsinc();
        return vistaDevolver;
    }

    private void obtenerReferencias(View vista) {
        lstCategorias = vista.findViewById(R.id.lstCategorias);
        txvMensajes = vista.findViewById(R.id.txvMensajes);
        listaCategoriasTraidas = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listaCategoriasTraidas);
    }

    private void setearListeners() {
        //lstCategorias.setOnItemClickListener(lstCategorias_ItemClick);
    }

    private void llamarTareaAsinc() {
        TareaAsincronicaMovies asincCategorias = new TareaAsincronicaMovies();
        asincCategorias.setLstCategorias(lstCategorias); //Paso el ListView
        asincCategorias.setTxvMensajes(txvMensajes); //Paso el TextView
        asincCategorias.setAdapter(adapter); //Paso el adapter
        asincCategorias.execute();
        Log.d("Pase", "Pase");
    }

    //region SETTERS
    public void setOpcionElegida(String opcionElegida) {
        this.opcionElegida = opcionElegida;
    }
    //endregion
}
