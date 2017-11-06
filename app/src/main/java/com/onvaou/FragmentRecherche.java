package com.onvaou;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentRecherche extends Fragment implements CompoundButton.OnCheckedChangeListener{

    private ImageView img_PlusTheme;
    private RadioGroup rg_Prix;
    private RadioButton rb_PrixFaible;
    private static ArrayList<String> mChoixBars;
    private TextView textView_themes;

    //Prix
    private CheckBox cbPrixFaible;
    private CheckBox cbPrixModere;
    private CheckBox cbPrixEleve;

    //Localisation
    private RadioButton rButton;
    private RadioGroup rgLocalisation;
    private EditText etVilleCP;
    String sVilleCP = "";

    private TextView test;

    public static Fragment newInstance(Context context) {
        FragmentRecherche f = new FragmentRecherche();
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = (View) inflater.inflate(R.layout.fragment_recherche, null);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String set = pref.getString("key", null);

        textView_themes = (TextView) root.findViewById(R.id.textView_themes);
        textView_themes.setText(set);
        img_PlusTheme = (ImageView) root.findViewById(R.id.img_PlusTheme);
        img_PlusTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfficherDialogChoixTheme();
            }
        });

        //Prix
        cbPrixFaible = (CheckBox) root.findViewById(R.id.cb_PrixFaible);
        cbPrixModere = (CheckBox) root.findViewById(R.id.cb_PrixModere);
        cbPrixEleve = (CheckBox) root.findViewById(R.id.cb_PrixEleve);

        cbPrixFaible.setOnCheckedChangeListener(this);
        cbPrixModere.setOnCheckedChangeListener(this);
        cbPrixEleve.setOnCheckedChangeListener(this);


        //Localisation
        rgLocalisation = (RadioGroup)root.findViewById(R.id.rg_localisation);
        etVilleCP = (EditText) root.findViewById(R.id.et_VilleCP);
        rgLocalisation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("chk", "id" + i);

                if (i == R.id.rb_LocalisationGPS) {
                    //some code
                    sVilleCP = "GPS";
                } else if (i == R.id.rb_LocalisationCP) {
                    //some code
                    sVilleCP = etVilleCP.getText().toString();
                }
                Toast.makeText(getContext(), sVilleCP, Toast.LENGTH_SHORT).show();

            }
        });

        Button btRecherche = (Button) root.findViewById(R.id.btn_Recherche);
        btRecherche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                Fragment f = new FragmentListeBars();
                mFragmentTransaction.replace(R.id.content_frame,f ,"LISTE").addToBackStack("RECHERCHE->LISTE");
                mFragmentTransaction.commit();
            }
        });

        return root;
    }


    private void AfficherDialogChoixTheme() {

        final ArrayList<String> seletedThemes = new ArrayList<>();
        ArrayList<String> ListeThemes = new ArrayList<>();
        for(int i = 0; i < Theme.values().length; i++){
            ListeThemes.add(Theme.values()[i].name());
        }

        final String[] themes = ListeThemes.toArray(new String[ListeThemes.size()]);

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Choix des thèmes")
                .setMultiChoiceItems(themes, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedThemes.add(themes[indexSelected]);
                        } else if (seletedThemes.contains(themes[indexSelected])) {
                            // Else, if the item is already in the array, remove it
                            seletedThemes.remove(themes[indexSelected]);
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here

                        String listeSelectedThemes = "";

                        for (Object s : seletedThemes)
                        {
                            listeSelectedThemes += "," + s.toString();
                        }
                        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("key", listeSelectedThemes);
                        editor.commit();

                        Fragment fragment = FragmentRecherche.newInstance(getContext());
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, fragment);
                        fragmentTransaction.commit();

                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();

    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferencesHelper.getInstance(getContext()).SauvegarderEtatCheckBox(String.valueOf(compoundButton.getText()),b);
    }
}
