package com.inicio.astroapp;

import android.graphics.Bitmap;
import android.media.Image;

public class astros_obj {

    String nombre_astro, fecha_astro;
    int imagen_astro;


    public astros_obj(String nombre_astro, int imagen_astro, String fecha_astro) {
        this.nombre_astro = nombre_astro;
        this.imagen_astro = imagen_astro;
        this.fecha_astro = fecha_astro;
    }
}
