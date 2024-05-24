package com.inicio.compranaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class confirmar_compraPag extends AppCompatActivity {

    public TextView textnombre, textfabricante, textpersonal, textpasajeros, textlongitud, textvelocidad, textcarga, textprecio;
    public Button compra, volver;

    private String nombre, fabricante, personal, pasajeros, velocidad, precio, carga, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_compra_pag);

        textnombre = findViewById(R.id.nombre_nave);
        textfabricante = findViewById(R.id.fabricante_nave);
        textpersonal = findViewById(R.id.personal_nave);
        textpasajeros = findViewById(R.id.pasajeros_nave);
        textlongitud = findViewById(R.id.longitud_nave);
        textvelocidad = findViewById(R.id.velocidad_nave);
        textcarga = findViewById(R.id.carga_nave);
        textprecio = findViewById(R.id.precio_nave);

        compra = findViewById(R.id.conf_compra);
        volver = findViewById(R.id.volver_atras);

        recoger_datos();

        compra.setOnClickListener(View -> compra_nave());
        volver.setOnClickListener(View -> finish());
    }

    public void recoger_datos(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        nombre = preferences.getString("nombre", "---");
        fabricante =preferences.getString("fabricante", "---");
        personal = preferences.getString("personal", "---");
        pasajeros = preferences.getString("pasajeros", "---");
        velocidad = preferences.getString("velocidad", "---");
        precio = preferences.getString("precio", "---");
        carga = preferences.getString("carga", "---");
        longitud = preferences.getString("longitud", "---");

        textnombre.setText(nombre);
        textfabricante.setText(fabricante);
        textpersonal.setText(personal + " tripulantes  \uD83E\uDDCD");
        textpasajeros.setText(pasajeros + " pasajeros  \uD83E\uDDCD");
        textvelocidad.setText(velocidad + " MGLT");
        textprecio.setText(precio + "  \uD83E\uDE99");
        textcarga.setText(carga + " KG  \uD83D\uDCE6");
        textlongitud.setText(longitud + " metros");


    }


    public void compra_nave(){
        Intent activity = new Intent(confirmar_compraPag.this, MainActivity.class);
        naves_obj nave_nueva = new naves_obj(nombre, Long.parseLong(carga), Integer.parseInt(pasajeros)+Integer.parseInt(personal), Long.parseLong(precio));
        naves_compradas.naves.add(nave_nueva);

        guardar_navesPersistentes();

        startActivity(activity);
        finish();
    }


    public void guardar_navesPersistentes(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = naves_compradas.convertirAJson();
        editor.putString("datos_naves", json);
        editor.commit();
    }
}