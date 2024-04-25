# Android App (Astro Log)

![N|Solid](https://cdn-icons-png.flaticon.com/512/1283/1283488.png)

#### Astro Log es una aplicación para registrar eventos astronómicos en tu dispositivo móvil para poder llevar un registro de sus avistamientos.

En la pantalla inicial se verá una lista de los astros ya observados con anterioridad y se verá la opción de agregar o eliminar astros de la lista.

## Puedes añadir astros tales como:

- Soles
- Satélites naturales
- Cometas
- Planetas
- Nebulosas
- Constelaciones
- Asteroides
- Galaxias

## Para agregar los astros deberás introducir:

- Nombre del avistamiento
- Categoría a la que pertenece(planeta, cometa, sol, etc...)
- Fecha de avistamiento (dd/mm/aaaa)
- Hora de avistamiento (00:00 - am/pm)
- Confirmar los cambios y agregarlo


Una vez introducidos los datos correctamente, se agregará el astro a la lista de registros y se cerrará la actividad, volviendo a la pantalla previa con los datos actualizados.



#### Codigo de envio de datos

```sh
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
```

Durante el proceso de guardado o eliminado de los nuevos astros, estos se almacenan en formato JSON para constar con persistencia de datos.

#### JSON Converter
```sh
    package com.inicio.astroapp;
    
    import java.util.ArrayList;
    import java.util.List;
    import com.google.gson.Gson;
    import com.google.gson.reflect.TypeToken;
    
    public class astros_data {
    
        public static List <astros_obj> lista_astros = new ArrayList<>();
    
    
        public static String convertirAJson() {
            Gson gson = new Gson();
            String json = gson.toJson(lista_astros);
            return json;
        }
    
        public static void convertirAJava(String json) {
            Gson gson = new Gson();
            lista_astros = gson.fromJson(json, new TypeToken<List<astros_obj>>(){}.getType());
        }
    }
```
#### Método para guardar los datos mediante el código anterior:
```sh
    public void guardar_astrosPersistentes(){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = astros_data.convertirAJson();
        editor.putString("datos_astros", json);
        editor.commit();
    }
```

#### Constructor del ListView de astros
El adapter relacionado con este código se actualiza al realizar cambios en la List de astros registrados.
```sh
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
```
