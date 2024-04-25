package com.inicio.astroapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ListView lista_astros;
    public ConstraintLayout vista_main;

    ActivityResultLauncher<Intent> launcher;
    public TextView texto_item, num_astros;
    public ImageButton boton_desplegable;
    public Button add_astro_button;
    public Button erase_astro_button;
    private astros_list_adapter adapter;

    public boolean puede_borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargar_datos();
        vista_main = findViewById(R.id.main_layout);
        puede_borrar = false;
        num_astros = findViewById(R.id.titulo);
        num_astros();
        boton_desplegable = findViewById(R.id.more_options_button);
        add_astro_button = findViewById(R.id.add_astro);
        erase_astro_button = findViewById(R.id.erase_astro);

        add_astro_button.setVisibility(View.INVISIBLE);
        erase_astro_button.setVisibility(View.INVISIBLE);

        texto_item = findViewById(R.id.texto_elemento);


        lista_astros = findViewById(R.id.lista_astros);
        adapter = new astros_list_adapter(MainActivity.this, R.layout.item_mainlist,  astros_data.lista_astros);
        lista_astros.setAdapter(adapter);

        boton_desplegable.setOnClickListener(view -> boton_despl_accion());
        add_astro_button.setOnClickListener(view -> siguiente_activity());
        erase_astro_button.setOnClickListener(view -> erase_activator());
        lista_astros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(puede_borrar){
                    astros_data.lista_astros.remove(position);
                    adapter.notifyDataSetChanged();
                    num_astros();
                    guardar_astrosPersistentes();
                }
                if(astros_data.lista_astros.size()== 0){
                    erase_activator();
                }
            }
        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void num_astros(){
        num_astros.setText("Has Visto " + astros_data.lista_astros.size() + " Astros");
    }

    public void erase_activator(){

        if(!puede_borrar){
            puede_borrar = true;
            vista_main.setBackgroundColor(Color.rgb(248, 99, 99));
        }
        else{
            puede_borrar = false;
            vista_main.setBackgroundColor(Color.rgb(137, 151, 241));
        }
        add_astro_button.setVisibility(View.INVISIBLE);
        erase_astro_button.setVisibility(View.INVISIBLE);
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
        add_astro_button.setVisibility(View.INVISIBLE);
        erase_astro_button.setVisibility(View.INVISIBLE);
        if(puede_borrar){
            erase_activator();
        }
        Intent intent = new Intent(this, Add_astro.class);
        launcher.launch(intent);
    }

    public void cargar_datos(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String json = preferences.getString("datos_astros", null);
        if (json != null) {
            astros_data.convertirAJava(json);
        }
    }

    public void guardar_astrosPersistentes(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = astros_data.convertirAJson();
        editor.putString("datos_astros", json);
        editor.commit();
    }

}