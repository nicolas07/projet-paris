package com.onvaou;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentRecherche2 extends Fragment {


    public static Fragment newInstance(Context context) {
        FragmentRecherche2 f = new FragmentRecherche2();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = (View) inflater.inflate(R.layout.fragment_recherche2, null);

//        ArrayList<String> websitesArray = new  ArrayList<String>();
//
//        for(int i = 0; i < 10;i++){
//            websitesArray.add("Theme "+i);
//        }
//
//        final LinearLayout ll = (LinearLayout) root.findViewById(R.id.ll);
//        //create radio buttons
//        for (int i = 0; i < websitesArray.size(); i++) {
//            CheckBox radioButton = new CheckBox(getContext());
//            radioButton.setText(websitesArray.get(i));
//            radioButton.setId(i);
//            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    Toast.makeText(getContext(),compoundButton.getId() + "State" + b,Toast.LENGTH_SHORT).show();
//                }
//            });
//            ll.addView(radioButton);
//        }



        return root;
    }
   }
