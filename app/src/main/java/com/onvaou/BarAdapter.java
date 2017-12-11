package com.onvaou;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nicolas on 29/10/2017.
 */
public class BarAdapter extends ArrayAdapter<Bar> {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public BarAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BarViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BarViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.ivImageBar);
            holder.nom = (TextView) row.findViewById(R.id.tvNomBar);
            holder.ville = (TextView) row.findViewById(R.id.tvVilleBar);

            row.setTag(holder);
        } else {
            holder = (BarViewHolder) row.getTag();
        }

        Bar bar = getItem(position);
        holder.ville.setText(bar.getCP() + "-" + bar.getVille());
        holder.nom.setText(bar.getNom());

        int idImage = 0;
        switch (bar.getAmbiance()){
            case Bar_à_Vins :
                idImage = R.mipmap.baravins;
                break;
            case Bar_à_Biere :
                idImage = R.mipmap.barabiere;
                break;
            case Bar_sans_Alcool :
                idImage = R.mipmap.barsansalcool;
                break;
            case    Bar_à_Cocktails :
                idImage = R.mipmap.baracocktails;
                break;
            case    Pub_Irlandais :
                idImage = R.mipmap.pubirlandais;
                break;
            case    Bar_Sportif:
                idImage = R.mipmap.barsportif;
                break;
            case    Bar_à_Musique :
                idImage = R.mipmap.baramusique;
                break;
            case    Bar_Latinos :
                idImage = R.mipmap.barlatino;
                break;
            default :
                idImage = R.mipmap.bar;
        }
        holder.image.setImageResource(idImage);
        return row;
    }

    private class BarViewHolder {
        public ImageView image;
        public TextView ville;
        public TextView nom;
    }


}
