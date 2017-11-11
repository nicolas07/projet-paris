package com.onvaou;

/**
 * Created by Nicolas on 29/10/2017.
 */
public class Bar {

    public String Nom;
    public Theme Theme;
    public Prix Prix;
    public String Adresse;
    public String Ville;
    public String CP;
    public double Latitude;
    public double Longitude;
    public float Note;
    public boolean Favori;

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

    public com.onvaou.Prix getPrix() {
        return Prix;
    }

    public void setPrix(com.onvaou.Prix prix) {
        Prix = prix;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public float getNote() {
        return Note;
    }

    public void setNote(float note) {
        Note = note;
    }

    public boolean isFavori() {
        return Favori;
    }

    public void setFavori(boolean favori) {
        Favori = favori;
    }

    public Bar(String nom, com.onvaou.Theme theme, com.onvaou.Prix prix, String adresse, String ville, String CP, double latitude, double longitude, float note) {
        Nom = nom;
        Theme = theme;
        Prix = prix;
        Adresse = adresse;
        Ville = ville;
        this.CP = CP;
        Latitude = latitude;
        Longitude = longitude;
        Note = note;
        Favori = false;
    }


}
