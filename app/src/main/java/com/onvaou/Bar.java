package com.onvaou;

/**
 * Created by Nicolas on 29/10/2017.
 */
public class Bar {

    private String Nom;
    private Theme Theme;
    private String Adresse;
    private TypePrix Prix;

    public Bar(String nom, com.onvaou.Theme theme, String adresse, TypePrix prix) {
        Nom = nom;
        Theme = theme;
        Adresse = adresse;
        Prix = prix;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public com.onvaou.Theme getTheme() {
        return Theme;
    }

    public void setTheme(com.onvaou.Theme theme) {
        Theme = theme;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public TypePrix getPrix() {
        return Prix;
    }

    public void setPrix(TypePrix prix) {
        Prix = prix;
    }
}
