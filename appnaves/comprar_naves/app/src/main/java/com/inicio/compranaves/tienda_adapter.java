package com.inicio.compranaves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class tienda_adapter extends ArrayAdapter {

    List<navesobj_tienda> navesTienda_local;
    int item_layout;
    Context context;


    public tienda_adapter(@NonNull Context context, int resource, @NonNull List<navesobj_tienda> data_naves) {
        super(context, resource, data_naves);

        this.context = context;
        item_layout = resource;
        navesTienda_local = data_naves;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(item_layout, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.nombre_nave_tienda);
        nombre.setText(navesTienda_local.get(position).nombre);

        TextView precio = convertView.findViewById(R.id.precio_nave_tienda);
        if(navesTienda_local.get(position).precio != 0){
            precio.setText(navesTienda_local.get(position).precio+"\uD83E\uDE99");
        }
        else{
            precio.setText("NO DISPONIBLE");
        }


        return convertView;
    }
}
