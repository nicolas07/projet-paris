package com.onvaou;

/**
 * Created by nkleb on 09/11/2017.
 */

public class ThemeHelper {

    public ThemeHelper() {
    }

    public Theme ConvertirStringVersTheme(String stheme){
        Theme theme = null;

        switch (stheme){
            case "Bar à Vins" :
                theme = Theme.Bar_à_Vins;
                break;
            case    "Bar à Biere" :
                theme = Theme.Bar_à_Biere;
                break;
            case    "Bar sans Alcool" :
                theme = Theme.Bar_sans_Alcool;
                break;
            case    "Bar à Cocktails" :
                theme = Theme.Bar_à_Cocktails;
                break;
            case    "Pub Irlandais" :
                theme = Theme.Pub_Irlandais;
                break;
            case    "Bar Sportif" :
                theme = Theme.Bar_Sportif;
                break;
            case    "Bar à Musique" :
                theme = Theme.Bar_à_Musique;
                break;
            case    "Bar Latinos" :
                theme = Theme.Bar_Latinos;
                break;
        }

        if(theme == null){
            String t = theme.toString();
        }

        return theme;
    }
}
