package com.onvaou;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentListeBarsFavoris extends Fragment{

    private ListView lvBars;

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
        listeBars = BarHelper.getInstance(getContext()).RecupererListeBarsFavoris();
        BarAdapter adapter = new BarAdapter(getContext(), R.layout.row_listebars,listeBars);
        lvBars.setAdapter(adapter);

        lvBars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
//                Fragment f = new FragmentDetail();
//                Bundle bundle = new Bundle();
//                bundle.putInt("BarSelectionne", position);
//                f.setArguments(bundle);
//                mFragmentTransaction.replace(R.id.content_frame,f ,"DETAIL").addToBackStack("Liste->Detail");
//                mFragmentTransaction.commit();

            }
        });

        return root;
    }




}
