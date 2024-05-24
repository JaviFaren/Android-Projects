package com.inicio.compranaves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class mis_navesAdapter extends ArrayAdapter {

    List<naves_obj> naves_local;
    int item_layout;
    Context context;


    public mis_navesAdapter(@NonNull Context context,int resource, @NonNull List<naves_obj> data_naves) {
        super(context, resource, data_naves);

        this.context = context;
        item_layout = resource;
        naves_local = data_naves;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(item_layout, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.nombre_nave);
        nombre.setText(naves_local.get(position).nombre);

        TextView otros_datos = convertView.findViewById(R.id.precio_carga_personas_nave);
        otros_datos.setText(naves_local.get(position).precio+"\uD83E\uDE99"+"\n"+naves_local.get(position).carga+"KG \uD83D\uDCE6"+"\n"+naves_local.get(position).personas+"\uD83E\uDDCD");

        return convertView;
    }
}
