package com.onvaou;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Nicolas on 04/11/2017.
 */

public class SharedPreferencesHelper {

    private static String SharedPreferencesName = "PREFERENCES";
    private static SharedPreferencesHelper instance = null;
    private static  SharedPreferences prefs = null;

    protected SharedPreferencesHelper(Context context) {
        // Exists only to defeat instantiation.
        prefs = context.getSharedPreferences(SharedPreferencesName, 0);
    }
    public static SharedPreferencesHelper getInstance(Context context) {
        if(instance == null) {
            instance = new SharedPreferencesHelper(context);
        }
        return instance;
    }

    public void SauvegarderEtatCheckBox(String nomCheckBox, boolean estCochee){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(nomCheckBox, estCochee);
        editor.commit();
    }

    public void SauvegarderListeBars(ArrayList<Bar> bars){

        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bars);
        editor.putString("ListeBars", json);
        editor.apply();
    }

    public ArrayList<Bar> RecupererListeBars(){

        Type type = new TypeToken<ArrayList<Bar>>() {}.getType();
        String gsonString = prefs.getString("ListeBars", "");
        Gson gson = new Gson();
        ArrayList<Bar> bars = gson.fromJson(gsonString, type);

        return bars;
    }

}
