package com.inicio.compranaves;

import java.util.Comparator;

public class naves_obj {
    public String nombre;
    public int personas;
    public long precio, carga;

    public naves_obj(String nombre, long carga, int personas, long precio) {
        this.nombre = nombre;
        this.carga = carga;
        this.personas = personas;
        this.precio = precio;
    }
}
