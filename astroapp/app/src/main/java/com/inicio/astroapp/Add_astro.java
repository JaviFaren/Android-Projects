
package com.inicio.astroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class Add_astro extends AppCompatActivity {

    GridView lista_botones;
    EditText nombre_astro;
    ImageButton boton_calendar, boton_hora;
    DatePicker calendario;
    TimePicker hora;

    TextView fecha_seleccionada, hora_seleccionada, categoria_shower;

    Button confirmar_boton;

    int item_pos;
    Integer selected_img;

    String nombre_final, fecha_final, categoria_actual;
    Integer categoria_final;
    private astro_category_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_astro);
        item_pos = -1;
        categoria_shower = findViewById(R.id.categoria_show);
        categoria_shower.setText("Selecciona categoria");
        nombre_astro = findViewById(R.id.input_astroName);
        lista_botones = findViewById(R.id.lista_botones);
        adapter = new astro_category_adapter(this, R.layout.astro_category,  astro_cat_data.imagenes_iconos());
        lista_botones.setAdapter(adapter);
        lista_botones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                item_pos = position;
                switch (position){
                    case 0:
                        categoria_shower.setText("Planeta");
                        break;
                    case 1:
                        categoria_shower.setText("Satelite");
                        break;
                    case 2:
                        categoria_shower.setText("Sol");
                        break;
                    case 3:
                        categoria_shower.setText("Galaxia");
                        break;
                    case 4:
                        categoria_shower.setText("Constelacion");
                        break;
                    case 5:
                        categoria_shower.setText("Nebulosa");
                        break;
                    case 6:
                        categoria_shower.setText("cometa");
                        break;
                    case 7:
                        categoria_shower.setText("Asteroide");
                        break;
                }
                for(int i=0; i<8; i++){
                    parent.getChildAt(i).findViewById(R.id.boton_categoria).setBackgroundColor(Color.rgb(255, 235, 112));
                }


                v.findViewById(R.id.boton_categoria).setBackgroundColor(Color.rgb(87,35,100));
                categoria();
            }
        });

        boton_calendar = findViewById(R.id.boton_calendario);
        calendario = findViewById(R.id.calendario);
        calendario.setVisibility(View.INVISIBLE);
        boton_calendar.setOnClickListener(view -> show_calendar());

        boton_hora = findViewById(R.id.hora_button);
        hora = findViewById(R.id.selector_hora);
        hora.setVisibility(View.INVISIBLE);
        boton_hora.setOnClickListener(view -> show_date());

        fecha_seleccionada = findViewById(R.id.fecha_elegida);
        hora_seleccionada = findViewById(R.id.hora_elegida);
        fecha_seleccionada.setText("Fecha: NAN/NAN/NAN");
        hora_seleccionada.setText("Hora: NAN:NAN");

        confirmar_boton = findViewById(R.id.confirm_button);
        confirmar_boton.setOnClickListener(view -> confirm_astro());
    }

    public void show_calendar(){
        if(calendario.getVisibility() == View.VISIBLE){
            fecha_seleccionada.setText("Fecha: " + calendario.getDayOfMonth() + " / " + calendario.getMonth() + " / " + calendario.getYear());
            calendario.setVisibility(View.INVISIBLE);
            boton_hora.setVisibility(View.VISIBLE);
            hora_seleccionada.setVisibility(View.VISIBLE);
            fecha_seleccionada.setVisibility(View.VISIBLE);
        }
        else{
            calendario.setVisibility(View.VISIBLE);
            boton_hora.setVisibility(View.INVISIBLE);
            hora.setVisibility(View.INVISIBLE);
            hora_seleccionada.setVisibility(View.INVISIBLE);
            fecha_seleccionada.setVisibility(View.INVISIBLE);
        }
    }

    public void show_date(){
        if(hora.getVisibility() == View.VISIBLE){

            hora_seleccionada.setText(hora_formatter());
            fecha_seleccionada.setVisibility(View.VISIBLE);
            hora_seleccionada.setVisibility(View.VISIBLE);
            hora.setVisibility(View.INVISIBLE);
            boton_calendar.setVisibility(View.VISIBLE);
        }
        else{
            hora.setVisibility(View.VISIBLE);
            fecha_seleccionada.setVisibility(View.INVISIBLE);
            hora_seleccionada.setVisibility(View.INVISIBLE);
            boton_calendar.setVisibility(View.INVISIBLE);
            calendario.setVisibility(View.INVISIBLE);
        }
    }

    public String hora_formatter(){
        String am_pm = "";
        String hora_real = "";
        String hora_final;
        if(hora.getHour() > 12){
            am_pm = "PM";
            hora_real = "" + (hora.getHour() - 12);
        }
        else{
            am_pm = "AM";
            hora_real = "" + hora.getHour();
        }
        if(hora.getMinute() == 0){
            hora_final = "Hora: " + hora_real + " : 00" + "  " + am_pm;
        }
        else{
            if(hora.getMinute()<10){
                hora_final = "Hora: " + hora_real + " : 0" + hora.getMinute() + "  " + am_pm;
            }
            else{
                hora_final = "Hora: " + hora_real + " : " + hora.getMinute() + "  " + am_pm;
            }

        }
        return hora_final;
    }

    public void categoria(){
        Integer[] array_imagenes = astro_cat_data.imagenes_iconos();
        selected_img = array_imagenes[item_pos];

    }


    public void confirm_astro(){
        if(fecha_seleccionada.getText() != "Fecha: NAN/NAN/NAN" && hora_seleccionada.getText() != "Hora: NAN:NAN" && item_pos != -1 && !nombre_astro.getText().toString().equals("")){
            nombre_final = "" + nombre_astro.getText();
            categoria_final = selected_img;
            fecha_final = calendario.getDayOfMonth() + "/" + calendario.getMonth() + "/" + calendario.getYear() + " | " + hora_formatter();

            astros_obj nuevo_astro = new astros_obj(nombre_final, categoria_final, fecha_final);

            astros_data.lista_astros.add(nuevo_astro);

            guardar_astrosPersistentes();


            finish();
        }
        else{
            if(fecha_seleccionada.getText() == "Fecha: NAN/NAN/NAN"){
                fecha_seleccionada.setTextColor(Color.rgb(123, 3, 35));
            }
            if(hora_seleccionada.getText() == "Hora: NAN:NAN"){
                hora_seleccionada.setTextColor(Color.rgb(123, 3, 35));
            }
            if(nombre_astro.getText().toString().equals("")){
                nombre_astro.setError("Introduce un nombre");
            }
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