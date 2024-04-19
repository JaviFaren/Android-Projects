
package com.inicio.astroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class Add_astro extends AppCompatActivity {

    GridView lista_botones;

    ImageButton boton_calendar, boton_hora;
    DatePicker calendario;
    TimePicker hora;

    TextView fecha_seleccionada, hora_seleccionada;

    Button confirmar_boton;

    private astro_category_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_astro);


        lista_botones = findViewById(R.id.lista_botones);
        adapter = new astro_category_adapter(this, R.layout.astro_category,  astro_cat_data.imagenes_iconos());
        lista_botones.setAdapter(adapter);

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

    public void confirm_astro(){

    }
}