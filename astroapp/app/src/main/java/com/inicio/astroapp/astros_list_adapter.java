package com.inicio.astroapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class astros_list_adapter extends ArrayAdapter {

    int ItemLayout;
    Context context;
    List<astros_obj> datos_astros;



    public astros_list_adapter(@NonNull Context context, int resource, @NonNull List<astros_obj> datos) {
        super(context, resource, datos);
        this.context = context;
        ItemLayout = resource;
        datos_astros = datos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(ItemLayout, parent, false);
        }

        TextView texto_item = convertView.findViewById(R.id.texto_elemento);
        texto_item.setText(datos_astros.get(position).nombre_astro);

        ImageView icono_item = convertView.findViewById(R.id.icono_elemento);
        icono_item.setImageResource(datos_astros.get(position).imagen_astro);
        return convertView;
    }
}
