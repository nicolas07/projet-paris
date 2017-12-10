package com.onvaou;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentListeBarsFavoris extends Fragment{

    private ListView lvBars;
    private TextView tvErreur;
    private ArrayList<Bar> listeBars;

    public static Fragment newInstance(Context context) {
        FragmentListeBarsFavoris f = new FragmentListeBarsFavoris();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_listebarsfavoris, null);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Bars Favoris");

        lvBars = (ListView) root.findViewById(R.id.lvBars);
        listeBars = SharedPreferencesHelper.getInstance(getContext()).RecupererListeBarsFavoris();

        //listeBars = BarHelper.getInstance(getContext()).RecupererListeBarsFavoris(BarHelper.getInstance(getContext()).GenererListeBars());
        BarAdapter adapter = new BarAdapter(getContext(), R.layout.row_listebars,listeBars);
        lvBars.setAdapter(adapter);

        tvErreur = (TextView) root.findViewById(R.id.tvErreurFavori);
        tvErreur.setVisibility(View.INVISIBLE);
        if(listeBars == null || listeBars.size() == 0){
            tvErreur.setTextColor(Color.RED);
            tvErreur.setVisibility(View.VISIBLE);
        }

        return root;
    }




}
