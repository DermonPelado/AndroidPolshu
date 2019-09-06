package com.dermonzeky.tpmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayList<Movies> {

    private List<Movies> moviesList = new ArrayList<>();
    private Context _MiContexto;

    public MovieAdapter (ArrayList<Movies> ListaDePeliculas, Context ContextoAUsar) {
        moviesList = ListaDePeliculas;
        _MiContexto = ContextoAUsar;
    }

    public String getItem (int PosicionAObtener) {
        return null;
    }

    public long getItemId (int PosicionAObtener) { return PosicionAObtener; }


    public View getView (int PosicionActual, View VistaActual, ViewGroup GrupoActual) {
        View VistaADevolver;
        VistaADevolver = null;
        LayoutInflater InfladorDeLayouts;
        InfladorDeLayouts = (LayoutInflater)_MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver=InfladorDeLayouts.inflate(R.layout.movies_item, GrupoActual, false);
        TextView txtTitulo;
        txtTitulo = (TextView) VistaADevolver.findViewById(R.id.txtTitulo);
        String TextoDeLaPosicionActual;
        TextoDeLaPosicionActual = getItem(PosicionActual);
        txtTitulo.setText(TextoDeLaPosicionActual);
        return VistaADevolver;
    }



}
