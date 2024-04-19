package com.inicio.astroapp;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class astros_data {

    public static List <astros_obj> lista_astros = new ArrayList<>();

    public static List<astros_obj> lista_prueba(){
        String[] nombres_astros = {"Sol", "Luna"};
        int[] imagenes_astros = {R.drawable.sol, R.drawable.satelite };

        for(int i=0; i<nombres_astros.length; i++){
            lista_astros.add(new astros_obj(nombres_astros[i], imagenes_astros[i]));
        }
        return  lista_astros;
    }
}
