package com.inicio.astroapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class astro_category_adapter extends ArrayAdapter {

    int ItemLayout;
    Context context;
    public Integer imagenes_categorias[];

    //Constructor de los botones de las categorias seleccionables para el astro nuevo
    public astro_category_adapter(@NonNull Context context, int resource, @NonNull Integer[] datos) {
        super(context, resource, datos);
        this.context = context;
        ItemLayout = resource;
        imagenes_categorias = datos;
    }

    //Asignador de iconos correspondientes a los botones de la gridView segun el xml vinculado
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(ItemLayout, parent, false);
        }

        ImageView boton_item = convertView.findViewById(R.id.boton_categoria);


        boton_item.setImageDrawable(context.getDrawable(imagenes_categorias[position]));
        return convertView;
    }


}
