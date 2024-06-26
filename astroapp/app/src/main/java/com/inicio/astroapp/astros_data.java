package com.inicio.astroapp;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class astros_data {

    //Lista donde se almacenan todos los astros registrados por el usuario
    public static List <astros_obj> lista_astros = new ArrayList<>();



    //Conversores de Java-JSON y JSON-Java para persistencia de datos
    public static String convertirAJson() {
        Gson gson = new Gson();
        String json = gson.toJson(lista_astros);
        return json;
    }

    public static void convertirAJava(String json) {
        Gson gson = new Gson();
        lista_astros = gson.fromJson(json, new TypeToken<List<astros_obj>>(){}.getType());
    }
}
