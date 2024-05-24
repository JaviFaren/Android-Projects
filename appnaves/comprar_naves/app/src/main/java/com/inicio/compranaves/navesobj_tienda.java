package com.inicio.compranaves;

public class navesobj_tienda {
    public String nombre, fabricante;
    public int personal, pasajeros, velocidad;
    public long precio, carga;
    public float longitud;

    public navesobj_tienda(String nombre, String fabricante, long precio, long carga, int personal, int pasajeros, int velocidad, float longitud) {
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.precio = precio;
        this.carga = carga;
        this.personal = personal;
        this.pasajeros = pasajeros;
        this.velocidad = velocidad;
        this.longitud = longitud;
    }
}
