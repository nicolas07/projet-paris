package com.onvaou;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicolas on 06/11/2017.
 */

public class BarHelper {

    private static BarHelper instance = null;

    protected BarHelper() {
    }

    public static BarHelper getInstance() {
        if(instance == null) {
            instance = new BarHelper();
        }
        return instance;
    }

//    public ArrayList<Bar> GenererListeBars(){
//        ArrayList<Bar> b = new ArrayList<Bar>();
//
//        Random r = new Random();
//        for(int i = 0; i < 20;i++)
//        {
//
//            float i1 = r.nextFloat()*(5 - 1) + 1;
//            b.add(new Bar("Bar" + i,Ambiance.Bar_à_Biere, Prix.Eleve,"2 Rue Victor Hugo","PARIS","75016",48.894825,2.382356,i1));
//
//        }
//
//        return b;
//    }

    public ArrayList<Bar> RecupererListeBarsFavoris(Context ctx){

        ArrayList<Bar> bars = SharedPreferencesHelper.getInstance(ctx).RecupererListeBars();
        ArrayList<Bar> barsFavoris = new ArrayList<>();

        for (Bar b : bars ) {
            if(b.isFavori()){
                barsFavoris.add(b);
            }

        }

        return barsFavoris;
    }

    public ArrayList<Bar> RecupererListeBarsFavoris(ArrayList<Bar> listeBars){

        ArrayList<Bar> barsFavoris = new ArrayList<>();

        for (Bar b : listeBars ) {

            if(b.isFavori()){
                barsFavoris.add(b);
            }

        }

        return barsFavoris;
    }

    public ArrayList<Bar> Rechercher(Context ctx){

        ArrayList<Bar> barsRecherche = new ArrayList<Bar>();
        ArrayList<Bar> bars = LireFichierBars(ctx);
        ArrayList<Ambiance> ambiances = SharedPreferencesHelper.getInstance(ctx).RecupererAmbiancesSelectionnes();
        ArrayList<Prix> prix = SharedPreferencesHelper.getInstance(ctx).RecupererPrixSelectionnes();
        String localisation = SharedPreferencesHelper.getInstance(ctx).RecupererTypeLocalisationSelectionnee();
        ArrayList<Enseigne> enseignes = SharedPreferencesHelper.getInstance(ctx).RecupererEnseignesSelectionnes();
        String orientation = SharedPreferencesHelper.getInstance(ctx).RecupererOrientationSelectionnee();

        for (Bar b : bars) {
            if(localisation.equals("GPS") && b.getCP().contains("75016")){
                if(ambiances.contains(b.getAmbiance()) && prix.contains(b.getPrix()) && enseignes.contains(b.getEnseigne()) && orientation.contains(b.getOrientation())){
                    barsRecherche.add(b);
                }
            } else if(localisation.substring(0,2).equals("CP")) {
                if(ambiances.contains(b.getAmbiance()) && prix.contains(b.getPrix()) && localisation.contains(b.getCP()) && enseignes.contains(b.getEnseigne()) && orientation.contains(b.getOrientation())){
                    barsRecherche.add(b);
                }
            }

        }

        return barsRecherche;
    }

//        //Pour chaque ambiance selectionne
//        for (Ambiance t : ambiances) {
//            Random r = new Random();
//            for(int i = 0; i < r.nextInt(10-1) + 1;i++)
//            {
//
//                float i1 = r.nextFloat()*(5 - 1) + 1;
//                Bar b = new Bar("Bar" + i,t, Prix.Faible,"2","Rue Victor Hugo","PARIS","75016",48.894825,2.382356,i1);
//
//
//                bars.add(b);
//            }
//
//        }


//    public ArrayList<Bar> GenererListeBarsRandom() {
//        ArrayList<Bar> bars = new ArrayList<Bar>();
//        Random r = new Random();
//        int nbBars = r.nextInt(15-5) + 5;
//
//        for (Ambiance t: Ambiance.values()) {
//
//            Random r2 = new Random();
//            int prix = r2.nextInt(3-1) + 1;
//            for(int i = 0; i < nbBars;i++){
//                Bar b = null;
//                switch (prix){
//                    case 1 :
//                        b = new Bar(t.name()+" "+ i,t, Prix.Faible,"2 Rue Victor Hugo","PARIS","75016",48.894825,2.382356,r2.nextFloat()*(5 - 1) + 1);
//                        break;
//                    case 2 :
//                        b = new Bar(t.name()+" "+ i,t, Prix.Modere,"2 Rue Victor Hugo","PARIS","75016",48.894825,2.382356,r2.nextFloat()*(5 - 1) + 1);
//                        break;
//                    case 3 :
//                        b = new Bar(t.name()+" "+ i,t, Prix.Eleve,"2 Rue Victor Hugo","PARIS","75016",48.894825,2.382356,r2.nextFloat()*(5 - 1) + 1);
//                        break;
//                }
//
//                bars.add(b);
//            }
//        }
//        return bars;
//    }

    public ArrayList<Bar> LireFichierBars(Context ctx){

        ArrayList<Bar> bars = new ArrayList<Bar>();

        InputStream inputStream = ctx.getResources().openRawResource(R.raw.bars);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            Random r = new Random();
            int icpt = 0;
            while (( line = buffreader.readLine()) != null) {
                String[] splitLine = line.split(",");

                float note = r.nextFloat()*(5 - 2) + 2;
                String orientation = "Hetero";

                if(icpt > 60){
                    orientation = "Gay";
                }

                bars.add(new Bar(splitLine[0],
                        new AmbianceHelper().ConvertirStringVersAmbiance(splitLine[4]),
                        new PrixHelper().ConvertirStringVersPrix(splitLine[5]),
                        splitLine[1],
                        splitLine[3],
                        splitLine[2],
                        48.894825,
                        2.382356,
                        note,
                        new EnseigneHelper().ConvertirStringVersEnseigne(splitLine[6]),orientation));
                icpt++;
            }
        } catch (Exception e) {
        }
        return bars;
    }
}
