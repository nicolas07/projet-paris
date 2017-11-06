package com.onvaou;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentListeBars extends Fragment{

    private ListView lvBars;

    private ArrayList<Bar> listeBars;

    public static Fragment newInstance(Context context) {
        FragmentListeBars f = new FragmentListeBars();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_listebars, null);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Liste des bars");

        lvBars = (ListView) root.findViewById(R.id.lvBars);
        listeBars = BarHelper.getInstance(getContext()).GenererListeBars();
        SharedPreferencesHelper.getInstance(getContext()).SauvegarderListeBars(listeBars);
        BarAdapter adapter = new BarAdapter(getContext(), R.layout.row_listebars,listeBars);
        lvBars.setAdapter(adapter);

        lvBars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                Fragment f = new FragmentDetail();
                Bundle bundle = new Bundle();
                bundle.putInt("BarSelectionne", position);
                f.setArguments(bundle);
                mFragmentTransaction.replace(R.id.content_frame,f ,"DETAIL").addToBackStack("Liste->Detail");
                mFragmentTransaction.commit();

            }
        });

        return root;
    }




}
