package com.onvaou;

/**
 * Created by nkleb on 09/11/2017.
 */

public class AmbianceHelper {

    public AmbianceHelper() {
    }

    public Ambiance ConvertirStringVersAmbiance(String sambiance){
        Ambiance ambiance = null;

        switch (sambiance){
            case "Bar à Vins" :
                ambiance = Ambiance.Bar_à_Vins;
                break;
            case    "Bar à Biere" :
                ambiance = Ambiance.Bar_à_Biere;
                break;
            case    "Bar sans Alcool" :
                ambiance = Ambiance.Bar_sans_Alcool;
                break;
            case    "Bar à Cocktails" :
                ambiance = Ambiance.Bar_à_Cocktails;
                break;
            case    "Pub Irlandais" :
                ambiance = Ambiance.Pub_Irlandais;
                break;
            case    "Bar Sportif" :
                ambiance = Ambiance.Bar_Sportif;
                break;
            case    "Bar à Musique" :
                ambiance = Ambiance.Bar_à_Musique;
                break;
            case    "Bar Latinos" :
                ambiance = Ambiance.Bar_Latinos;
                break;
        }

        if(ambiance == null){
            String t = ambiance.toString();
        }

        return ambiance;
    }
}
