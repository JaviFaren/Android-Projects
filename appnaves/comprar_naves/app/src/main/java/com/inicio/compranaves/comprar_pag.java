package com.inicio.compranaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class comprar_pag extends AppCompatActivity {

    public ListView lista_tienda;
    public ImageButton next_page, previous_page, return_button;
    public TextView pag_shower;
    private tienda_adapter adapter;
    public List<navesobj_tienda> naves_recibidas = new ArrayList<>();
    public int pagina_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_pag);
        next_page = findViewById(R.id.next_page);
        previous_page = findViewById(R.id.previous_page);
        return_button = findViewById(R.id.return_button);
        pag_shower = findViewById(R.id.page_number);

        pagina_actual = 1;
        pag_shower.setText(""+pagina_actual);
        lista_tienda = findViewById(R.id.lista_tienda);
        recibir_datos(String.valueOf(pagina_actual));
        adapter = new tienda_adapter(this, R.layout.nave_venta, naves_recibidas);
        lista_tienda.setAdapter(adapter);


        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        lista_tienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(naves_recibidas.get(i).precio != 0){
                    Intent activity = new Intent(comprar_pag.this, confirmar_compraPag.class);
                    editor.putString("nombre", naves_recibidas.get(i).nombre);
                    editor.putString("fabricante", naves_recibidas.get(i).fabricante);
                    editor.putString("personal", String.valueOf(naves_recibidas.get(i).personal));
                    editor.putString("pasajeros", String.valueOf(naves_recibidas.get(i).pasajeros));
                    editor.putString("velocidad", String.valueOf(naves_recibidas.get(i).velocidad));
                    editor.putString("precio", String.valueOf(naves_recibidas.get(i).precio));
                    editor.putString("carga", String.valueOf(naves_recibidas.get(i).carga));
                    editor.putString("longitud", String.valueOf(naves_recibidas.get(i).longitud));
                    editor.commit();

                    startActivity(activity);
                }
            }
        });


        next_page.setOnClickListener(view -> actualizar_pag(true));
        previous_page.setOnClickListener(view -> actualizar_pag(false));
        return_button.setOnClickListener(view -> finish());

    }

    public void recibir_datos(String pagina){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://swapi.dev/api/starships/?page=" + pagina;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String nombre, fabricante;
                            int personal, pasajeros, velocidad;
                            long precio, carga;
                            float longitud;
                            int num_naves = response.getJSONArray("results").length();
                            for(int i=0; i<num_naves; i++){
                                nombre = response.getJSONArray("results").getJSONObject(i).getString("name");
                                fabricante = response.getJSONArray("results").getJSONObject(i).getString("manufacturer");
                                if(response.getJSONArray("results").getJSONObject(i).getString("cost_in_credits").equals("unknown")){
                                    precio = 0;
                                }
                                else{
                                    precio = Long.parseLong(response.getJSONArray("results").getJSONObject(i).getString("cost_in_credits"));
                                }
                                String longitud_text = response.getJSONArray("results").getJSONObject(i).getString("length");
                                for(int u = 0; u<longitud_text.length(); u++){
                                    if(longitud_text.charAt(u) == ','){
                                        longitud_text = longitud_text.replace(",","");
                                    }

                                }
                                longitud_text = longitud_text.replace(",","");
                                longitud = Float.parseFloat(longitud_text);
                                String carga_text = response.getJSONArray("results").getJSONObject(i).getString("cargo_capacity");
                                if(carga_text.equals("unknown")){
                                    carga = 0;
                                }
                                else{
                                    carga = Long.parseLong(response.getJSONArray("results").getJSONObject(i).getString("cargo_capacity"));
                                }

                                String personal_text = response.getJSONArray("results").getJSONObject(i).getString("crew");
                                if(personal_text.equals("unknown")){
                                    personal = 0;
                                }
                                else{
                                    personal = 0;
                                    for(int l= 0; l<personal_text.length(); l++){
                                        if(personal_text.charAt(l) == '-'){
                                            personal = check_personal(personal_text, '-');
                                            break;
                                        }
                                        else if(personal_text.charAt(l) == ','){
                                            personal = check_personal(personal_text, ',');
                                            break;
                                        }
                                        else if(l == personal_text.length()-1){
                                            personal = Integer.parseInt(personal_text);
                                        }
                                    }
                                }

                                String pasajeros_text = response.getJSONArray("results").getJSONObject(i).getString("passengers");
                                if(pasajeros_text.equals("unknown")){
                                    pasajeros = 0;
                                }
                                else {
                                    pasajeros = 0;
                                    for(int p=0; p<pasajeros_text.length(); p++){
                                        if(pasajeros_text.charAt(p) == ','){
                                            pasajeros = check_personal(pasajeros_text, ',');
                                            break;
                                        }
                                        if(p == pasajeros_text.length()-1 && pasajeros_text.equals("n/a")){
                                            pasajeros = 0;
                                            break;
                                        }
                                        else if(p == pasajeros_text.length()-1 && !pasajeros_text.equals("n/a")){
                                            pasajeros = Integer.parseInt(pasajeros_text);
                                            break;
                                        }
                                    }
                                }

                                String velocidad_text = response.getJSONArray("results").getJSONObject(i).getString("MGLT");
                                if(velocidad_text.equals("unknown")){
                                    velocidad = 0;
                                }
                                else{
                                    velocidad = Integer.parseInt(response.getJSONArray("results").getJSONObject(i).getString("MGLT"));
                                }


                                navesobj_tienda nueva_nave = new navesobj_tienda(nombre, fabricante, precio, carga, personal, pasajeros, velocidad, longitud);
                                naves_recibidas.add(nueva_nave);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("response", "error en try");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("response", "error en request");
                    }
                });
        queue.add(jsonObjectRequest);

    }

    public int check_personal(String text, char error){
        String personal_text_final = "";
        int personal_final;
        boolean pasado_guion = false;

        if(error == '-'){
            for(int i=0; i<text.length(); i++){
                if(pasado_guion){
                    personal_text_final = personal_text_final + text.charAt(i);
                }
                if(text.charAt(i) == '-'){
                    pasado_guion = true;
                }
            }
        }
        if(error == ','){
            personal_text_final = text.replace(",","");
        }

        personal_final = Integer.parseInt(personal_text_final);
        return personal_final;
    }

    public void actualizar_pag(boolean es_next){
        if(es_next && pagina_actual<4){
            pagina_actual++;
            naves_recibidas.clear();
            recibir_datos(String.valueOf(pagina_actual));
            pag_shower.setText(""+pagina_actual);
        }
        else if(!es_next && pagina_actual>1){
            pagina_actual--;
            naves_recibidas.clear();
            recibir_datos(String.valueOf(pagina_actual));
            pag_shower.setText(""+pagina_actual);
        }
    }


}