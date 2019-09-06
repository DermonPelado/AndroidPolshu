package com.dermonzeky.tpmovies;

import android.graphics.Region;
import android.media.Image;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import org.json.JSONObject;

public class TareaAsincronicaMovies extends AsyncTask<Void, Void, String> {

    private ListView lstCategorias;
    private TextView txvMensajes;
    private MovieAdapter adapter;

    //ArrayList<Movies> ListPeliculas = new ArrayList<Movies>();

    @Override
    protected void onPreExecute() {
        /* Apenas empieza muestro el mensaje de cargando categorias */
        lstCategorias.setVisibility(View.GONE);
        txvMensajes.setVisibility(View.VISIBLE);
        txvMensajes.setText("CARGANDO CATEGORIAS...");
    }

    @Override
    protected String doInBackground(Void... voids) {
        String JSONToString = "";
        try {
            URL laRuta = new URL("http://www.omdbapi.com/?apikey=b02c95cb&s=toy");
            HttpURLConnection miConexion = (HttpURLConnection) laRuta.openConnection();
            Log.d("AccesoAPI", "Me conecto");
            if (miConexion.getResponseCode() == 200) {
                Log.d("AccesoAPI", "Conexion OK");
                InputStream cuerpoRespuesta = miConexion.getInputStream();
                InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta, "UTF-8");
                BufferedReader r = new BufferedReader(lectorRespuesta);
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');
                }
                JSONToString = total.toString();


                Log.d("Ver", JSONToString);
                procesarJsonLeido();
            }
            else {
                Log.d("AccesoAPI", "ERROR en la conexion");
            }
            miConexion.disconnect();
        } catch(Exception e) {
            Log.d("AccesoAPI", "ERROR - " + e.getMessage());
        }
        return JSONToString;
    }

    @Override
    protected void onPostExecute(String  texto) {
        super.onPostExecute(texto);
       /*
        JsonParser parser = new JsonParser();
        JsonObject objGlobal = parser.parse(JSONToString).getAsJsonObject();
        JsonArray arraySearch = objGlobal.getAsJsonArray("Search");

        Movies[] Peliculas = new Gson().fromJson(arraySearch, Movies[].class);
        Log.d("Ver3", Peliculas[0].toString());
        **/

        Resultado miREsultado = new Gson().fromJson(texto, Resultado.class);
        Integer Total = miREsultado.totalResults;
        Log.d("Resultados: ", Total.toString());
        //miREsultado.Search[0]



        if (miREsultado.Search.length > 0) {
            lstCategorias.setAdapter(adapter); //Meto los objetos de la ArrayList al ListView
            lstCategorias.setVisibility(View.VISIBLE);
            txvMensajes.setVisibility(View.GONE);
        }
        else {
            lstCategorias.setVisibility(View.GONE);
            txvMensajes.setVisibility(View.VISIBLE);
            txvMensajes.setText("No se pudo traer ninguna categoria. Intente nuevamente");
        }
    }

    private void procesarJsonLeido() {
        //JsonReader jsonLeido = new JsonReader(JsonCrudo);
        /*
        try {
            jsonLeido.beginObject();
            while (jsonLeido.hasNext()) {
                String nombreElementoActual = jsonLeido.nextName();
                Log.d("LecturaJSON", "Elemento actual: " + nombreElementoActual);
                if (nombreElementoActual.equals("cantidad_de_categorias")) {
                    int intCantCategorias = jsonLeido.nextInt();
                    Log.d("LecturaJSON", "Cantidad de categorias: ".concat(Integer.toString(intCantCategorias)));
                }
                else {
                    jsonLeido.beginArray();
                    while (jsonLeido.hasNext()) {
                        jsonLeido.beginObject();
                        while (jsonLeido.hasNext()) {
                            nombreElementoActual = jsonLeido.nextName();
                            if (nombreElementoActual.equals("nombre")) {
                                String valorElementoActual = jsonLeido.nextString();
                                //Log.d("LecturaJSON", "Valor leido: " + valorElementoActual);
                                listaCategoriasTraidas.add(valorElementoActual);
                            }
                            else {
                                jsonLeido.skipValue();
                            }
                        }
                        jsonLeido.endObject();
                    }
                    jsonLeido.endArray();
                }
            }
            jsonLeido.endObject();
        }
        catch (Exception e) {
            Log.d("LecturaJSON", "ERROR, " + e.getMessage());
        }
        */
    }

    //region Setters

    public void setLstCategorias(ListView lstCategorias) {
        this.lstCategorias = lstCategorias;
    }

    public void setTxvMensajes(TextView txvMensajes) {
        this.txvMensajes = txvMensajes;
    }

    public void setAdapter(MovieAdapter adapter) {
        this.adapter = adapter;
    }

    //endregion


}
