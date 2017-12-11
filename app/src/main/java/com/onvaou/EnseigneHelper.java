package com.onvaou;

/**
 * Created by nkleb on 11/12/2017.
 */

public class EnseigneHelper {

    public EnseigneHelper() {
    }

    public Enseigne ConvertirStringVersEnseigne(String senseigne){
        Enseigne enseigne = null;

        switch (senseigne){
            case "Bar" :
                enseigne = Enseigne.Bar;
                break;
            case "Restaurant" :
                enseigne = Enseigne.Restaurant;
                break;
            case "Discothèque" :
                enseigne = Enseigne.Discothèque;
                break;
            case    "Salon de Thés" :
                enseigne = Enseigne.Salon_de_Thés;
                break;
            case    "Chicha" :
                enseigne = Enseigne.Chicha;
                break;
        }

        if(enseigne == null){
            String t = enseigne.toString();
        }

        return enseigne;
    }

}
