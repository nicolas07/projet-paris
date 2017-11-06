package com.onvaou;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicolas on 06/11/2017.
 */

public class BarHelper {

    private static BarHelper instance = null;
    private static Context context = null;

    protected BarHelper() {
    }

    public static BarHelper getInstance(Context ctx) {
        if(instance == null) {
            instance = new BarHelper();
            context = ctx;
        }
        return instance;
    }

    public ArrayList<Bar> GenererListeBars(){
        ArrayList<Bar> b = new ArrayList<Bar>();

        Random r = new Random();
        for(int i = 0; i < 20;i++)
        {

            float i1 = r.nextFloat()*(5 - 1) + 1;
            b.add(new Bar("Bar" + i,Theme.BarABiere, Prix.Eleve,"2","Rue Victor Hugo","PARIS","75016",48.894825,2.382356,i1));

        }

        return b;
    }

    public ArrayList<Bar> RecupererListeBarsFavoris(){

        ArrayList<Bar> bars = SharedPreferencesHelper.getInstance(context).RecupererListeBars();
        ArrayList<Bar> barsFavoris = new ArrayList<>();

        for (Bar b : bars ) {

            if(b.isFavori()){
                barsFavoris.add(b);
            }

        }

        return barsFavoris;
    }
}
