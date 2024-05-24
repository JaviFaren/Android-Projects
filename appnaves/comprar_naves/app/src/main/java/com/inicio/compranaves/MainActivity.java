package com.inicio.compranaves;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity{

    public ListView lista_navesCompradas;
    public Button boton_tienda, precio_sorter, carga_sorter, pasajeros_sorter;
    public ImageButton sort_boton, filter_boton;
    private boolean precio_Pressed, cargaPressed, personasPressed;
    public LinearLayout caja_botones;
    public TextView texto_dinero;
    private mis_navesAdapter adapter;
    ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargar_datos();

        lista_navesCompradas = findViewById(R.id.lista_naves);
        adapter = new mis_navesAdapter(MainActivity.this, R.layout.nave_comprada_item, naves_compradas.naves);
        lista_navesCompradas.setAdapter(adapter);

        precio_Pressed = false;
        cargaPressed = false;
        personasPressed = false;
        sort_boton = findViewById(R.id.sort_button);
        caja_botones = findViewById(R.id.layoutsorter);
        precio_sorter = findViewById(R.id.precio_sort);
        carga_sorter = findViewById(R.id.carga_sort);
        pasajeros_sorter = findViewById(R.id.pasajeros_sort);
        caja_botones.setVisibility(View.INVISIBLE);

        sort_boton.setOnClickListener(view -> mostrar_botones(true));
        precio_sorter.setOnClickListener(view -> organizar_lista(1));
        carga_sorter.setOnClickListener(view -> organizar_lista(2));
        pasajeros_sorter.setOnClickListener(view -> organizar_lista(3));



        texto_dinero = findViewById(R.id.total_gastado);
        texto_dinero.setText("Total gastado: "+total_dinero()+" \uD83E\uDE99");

        boton_tienda = findViewById(R.id.boton_tienda);
        boton_tienda.setOnClickListener(view -> acceder_Tienda());

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        adapter.notifyDataSetChanged();
                    }
                });
    }


    public void mostrar_botones(boolean esSorter){
        if(esSorter){
            if(caja_botones.getVisibility() == View.INVISIBLE){
                caja_botones.setVisibility(View.VISIBLE);
            }
            else{
                caja_botones.setVisibility(View.INVISIBLE);
            }
        }
        else{

        }
    }

    public void organizar_lista(int tipo){
        if(tipo == 1){
            if(!precio_Pressed){
                naves_compradas.naves.sort((nave1, nave2) -> (int) (nave1.precio-nave2.precio));
                adapter.notifyDataSetChanged();
                precio_Pressed = true;
            }
            else{
                naves_compradas.naves.sort((nave1, nave2) -> (int) (nave2.precio-nave1.precio));
                adapter.notifyDataSetChanged();
                precio_Pressed = false;
            }

        }
        else if (tipo == 2) {
            if(!cargaPressed){
                naves_compradas.naves.sort((nave1, nave2) -> (int) (nave1.carga-nave2.carga));
                adapter.notifyDataSetChanged();
                cargaPressed = true;
            }
            else{
                naves_compradas.naves.sort((nave1, nave2) -> (int) (nave2.carga-nave1.carga));
                adapter.notifyDataSetChanged();
                cargaPressed = false;
            }

        }
        else{
            if(!personasPressed){
                naves_compradas.naves.sort((nave1, nave2) -> (nave1.personas-nave2.personas));
                adapter.notifyDataSetChanged();
                personasPressed = true;
            }
            else{
                naves_compradas.naves.sort((nave1, nave2) -> (nave2.personas-nave1.personas));
                adapter.notifyDataSetChanged();
                personasPressed = false;
            }

        }
    }


    public String total_dinero(){
        long dinero = 0;
        for(int i=0; i<naves_compradas.naves.size(); i++){
            dinero = dinero + naves_compradas.naves.get(i).precio;
        }
        return String.valueOf(dinero);
    }

    public void acceder_Tienda(){
        Intent activity = new Intent(this, comprar_pag.class);
        launcher.launch(activity);
    }

    public void cargar_datos(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String json = preferences.getString("datos_naves", null);
        if (json != null) {
            naves_compradas.convertirAJava(json);
        }
    }
}