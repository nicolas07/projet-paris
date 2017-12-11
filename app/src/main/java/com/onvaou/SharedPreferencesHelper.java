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
    private static String Key_AmbiancesSelectionnes = "ListeAmbiancesSelectionnes";
    private static String Key_EnseignesSelectionnes = "ListeEnsiegnesSelectionnes";
    private static String Key_OrientationSelectionnee = "OrientationSelectionnee";
    private static String Key_ListeBars = "ListeBars";
    private static String Key_ListeBarsFavoris = "ListeBarsFavoris";
    private static String Key_Localisation = "TypeLocalisation";

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

    public void SauvegarderAmbiancesSelectionnes(String listeSelectedAmbiances){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Key_AmbiancesSelectionnes, listeSelectedAmbiances);
        editor.commit();
    }

    public ArrayList<Ambiance> RecupererAmbiancesSelectionnes(){
        ArrayList<Ambiance> ambiances = new ArrayList<Ambiance>();
        String liste = prefs.getString(Key_AmbiancesSelectionnes,"");
        String[] listeSplit = liste.split("\n");

        for (String s : listeSplit) {
            ambiances.add(new AmbianceHelper().ConvertirStringVersAmbiance(s));
        }
        return ambiances;
    }

    public String RecupererAmbiancesSelectionnesString(){
        ArrayList<Ambiance> ambiances = new ArrayList<Ambiance>();
        return prefs.getString(Key_AmbiancesSelectionnes,"");
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

    public void SauvegarderTypeLocalisationSelectionnee(String localisation){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Key_Localisation, localisation);
        editor.commit();
    }

    public String RecupererTypeLocalisationSelectionnee(){
        return prefs.getString(Key_Localisation,"");
    }

    public void Clear(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }


    public void SauvegarderEnseignesSelectionnes(String listeSelectedEnseignes){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Key_EnseignesSelectionnes, listeSelectedEnseignes);
        editor.commit();
    }

    public ArrayList<Enseigne> RecupererEnseignesSelectionnes(){
        ArrayList<Enseigne> enseignes = new ArrayList<Enseigne>();
        String liste = prefs.getString(Key_EnseignesSelectionnes,"");
        String[] listeSplit = liste.split("\n");

        for (String s : listeSplit) {
            enseignes.add(new EnseigneHelper().ConvertirStringVersEnseigne(s));
        }
        return enseignes;
    }

    public String RecupererEnseignesSelectionnesString(){
        ArrayList<Enseigne> enseignes = new ArrayList<Enseigne>();
        return prefs.getString(Key_EnseignesSelectionnes,"");
    }

    public void SauvegarderOrientationSelectionnee(String orientation){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Key_OrientationSelectionnee, orientation);
        editor.commit();
    }

    public String RecupererOrientationSelectionnee(){
        return prefs.getString(Key_OrientationSelectionnee,"");
    }
}
