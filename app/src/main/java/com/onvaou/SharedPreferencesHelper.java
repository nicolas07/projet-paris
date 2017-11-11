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
    private static String Key_PrixSelectionnes = "ListePrixSelectionnes";
    private static String Key_ThemesSelectionnes = "ListeThemesSelectionnes";
    private static String Key_ListeBars = "ListeBars";
    private static String Key_ListeBarsFavoris = "ListeBarsFavoris";

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
        editor.putString(Key_ListeBars, json);
        editor.apply();
    }

    public ArrayList<Bar> RecupererListeBars(){

        Type type = new TypeToken<ArrayList<Bar>>() {}.getType();
        String gsonString = prefs.getString(Key_ListeBars, "");
        Gson gson = new Gson();
        ArrayList<Bar> bars = gson.fromJson(gsonString, type);

        return bars;
    }

    public void SauvegarderListeBarsFavoris(ArrayList<Bar> bars){

        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bars);
        editor.putString(Key_ListeBarsFavoris, json);
        editor.apply();
    }

    public ArrayList<Bar> RecupererListeBarsFavoris(){

        Type type = new TypeToken<ArrayList<Bar>>() {}.getType();
        String gsonString = prefs.getString(Key_ListeBarsFavoris, "");
        Gson gson = new Gson();
        ArrayList<Bar> bars = gson.fromJson(gsonString, type);

        return bars;
    }

    public void SauvegarderThemesSelectionnes(String listeSelectedThemes){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Key_ThemesSelectionnes, listeSelectedThemes);
        editor.commit();
    }

    public ArrayList<Theme> RecupererThemesSelectionnes(){
        ArrayList<Theme> themes = new ArrayList<Theme>();
        String liste = prefs.getString(Key_ThemesSelectionnes,"");
        String[] listeSplit = liste.split("\n");

        for (String s : listeSplit) {
            themes.add(new ThemeHelper().ConvertirStringVersTheme(s));
        }
        return themes;
    }

    public String RecupererThemesSelectionnesString(){
        ArrayList<Theme> themes = new ArrayList<Theme>();
        return prefs.getString(Key_ThemesSelectionnes,"");
    }

    public void SauvegarderPrixSelectionnes(String listeSelectedPrix){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Key_PrixSelectionnes, listeSelectedPrix);
        editor.commit();
    }

    public ArrayList<Prix> RecupererPrixSelectionnes(){
        ArrayList<Prix> Listeprix = new ArrayList<Prix>();
        String liste = prefs.getString(Key_PrixSelectionnes,"");
        String[] listeSplit = liste.split(",");

        for (String s : listeSplit) {
            Listeprix.add(new PrixHelper().ConvertirStringVersPrix(s));
        }
        return Listeprix;
    }

    public void Clear(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

}
