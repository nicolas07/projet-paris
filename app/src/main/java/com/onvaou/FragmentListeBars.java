package com.onvaou;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentListeBars extends Fragment{

    private ListView lvBars;

    private ArrayList<Bar> listeBars;

    private OnFragmentInteractionListener mListener;

    public static Fragment newInstance(Context context) {
        FragmentListeBars f = new FragmentListeBars();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_listebars, null);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Liste des bars");

        lvBars = (ListView) root.findViewById(R.id.lvBars);
        listeBars = getData();
        BarAdapter adapter = new BarAdapter(getContext(), R.layout.row_listebars,listeBars);
        lvBars.setAdapter(adapter);

        lvBars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                String selected = ((TextView) view.findViewById(R.id.tvNomBar)).getText().toString();
                Toast toast = Toast.makeText(getActivity(), selected, Toast.LENGTH_SHORT);
                toast.show();

                Bar c = listeBars.get(position);

                if (mListener != null) {
                    mListener.onFragmentInteraction(c);
                }
            }
        });

        return root;
    }

    private ArrayList<Bar> getData(){
        ArrayList<Bar> liste = new ArrayList<Bar>();

        for(int i =0; i < 10; i++)
        {
            liste.add(new Bar("Bar"+i,Theme.BarABiere,"14 rue Sarrette - 75014 PARIS",TypePrix.Faible));
        }

        return liste;
    }
}
