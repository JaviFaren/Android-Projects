package com.inicio.astroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ListView lista_astros;

    public TextView texto_item;
    public ImageButton boton_desplegable;
    public Button add_astro_button;
    public Button erase_astro_button;
    private astros_list_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton_desplegable = findViewById(R.id.imageButton);
        add_astro_button = findViewById(R.id.add_astro);
        erase_astro_button = findViewById(R.id.erase_astro);

        add_astro_button.setVisibility(View.INVISIBLE);
        erase_astro_button.setVisibility(View.INVISIBLE);

        texto_item = findViewById(R.id.texto_elemento);


        lista_astros = findViewById(R.id.lista_astros);
        adapter = new astros_list_adapter(MainActivity.this, R.layout.item_mainlist,  astros_data.lista_prueba());
        lista_astros.setAdapter(adapter);

        boton_desplegable.setOnClickListener(view -> boton_despl_accion());
        add_astro_button.setOnClickListener(view -> siguiente_activity());
    }

    public void boton_despl_accion(){
        if(add_astro_button.getVisibility() == View.INVISIBLE && erase_astro_button.getVisibility() == View.INVISIBLE){
            add_astro_button.setVisibility(View.VISIBLE);
            erase_astro_button.setVisibility(View.VISIBLE);
        }
        else{
            add_astro_button.setVisibility(View.INVISIBLE);
            erase_astro_button.setVisibility(View.INVISIBLE);
        }
    }

    public void siguiente_activity(){
        Intent intent = new Intent(this, Add_astro.class);
        startActivity(intent);
    }

}