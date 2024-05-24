package com.inicio.compranaves;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class naves_compradas  {

    public static List<naves_obj> naves = new ArrayList<>();


    public static String convertirAJson() {
        Gson gson = new Gson();
        String json = gson.toJson(naves);
        return json;
    }

    public static void convertirAJava(String json) {
        Gson gson = new Gson();
        naves = gson.fromJson(json, new TypeToken<List<naves_obj>>(){}.getType());
    }

}
