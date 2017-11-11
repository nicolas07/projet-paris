package com.onvaou;

/**
 * Created by nkleb on 09/11/2017.
 */

public class PrixHelper {

    public PrixHelper() {
    }

    public Prix ConvertirStringVersPrix(String sprix){
        Prix prix = null;
        switch (sprix.toUpperCase()){
            case "PRIXFAIBLE" :
                prix = Prix.Faible;
                break;
            case "PRIXMODERE" :
                prix = Prix.Modere;
                break;
            case "PRIXELEVE" :
                prix = Prix.Eleve;
                break;
        }

        return prix;
    }
}
