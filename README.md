# Android App
## Astro Log Application

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

Una vez introducidos los datos, al volver a la pantalla principal se verá el astro agregado a la lista de avistamientos.


## Codigos varios

#### Recogida de datos del nuevo astro
```sh

```

#### Constructor del ListView de astros

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


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
