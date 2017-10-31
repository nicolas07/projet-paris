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
            holder.theme = (TextView) row.findViewById(R.id.tvThemeBar);

            row.setTag(holder);
        } else {
            holder = (BarViewHolder) row.getTag();
        }

        Bar bar = getItem(position);
        holder.theme.setText(bar.getTheme().toString());
        holder.nom.setText(bar.getNom());
        holder.image.setImageResource(R.mipmap.bar);
        return row;
    }

    private class BarViewHolder {
        public ImageView image;
        public TextView theme;
        public TextView nom;
    }


}
