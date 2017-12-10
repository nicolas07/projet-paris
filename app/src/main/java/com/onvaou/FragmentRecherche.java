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

    private ImageView igChoixTheme;
    private TextView tvThemes;

    //Prix
    private CheckBox cbPrixFaible;
    private CheckBox cbPrixModere;
    private CheckBox cbPrixEleve;

    //Localisation
    private RadioGroup rgLocalisation;
    private RadioButton rbGPS;
    private RadioButton rbCP;
    private EditText etVilleCP;
    String sVilleCP = "";

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
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Recherche");

        final View root = (View) inflater.inflate(R.layout.fragment_recherche, null);

        //Themes
        tvThemes = (TextView) root.findViewById(R.id.textView_themes);
        String t = SharedPreferencesHelper.getInstance(getContext()).RecupererThemesSelectionnesString();
        tvThemes.setText(t);
        igChoixTheme = (ImageView) root.findViewById(R.id.img_PlusTheme);
        igChoixTheme.setOnClickListener(new View.OnClickListener() {
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

        ArrayList<Prix> p = SharedPreferencesHelper.getInstance(getContext()).RecupererPrixSelectionnes();

        if(p.contains(Prix.Faible)){
            cbPrixFaible.setChecked(true);
        }
        if(p.contains(Prix.Modere)){
            cbPrixModere.setChecked(true);
        }
        if(p.contains(Prix.Eleve)){
            cbPrixEleve.setChecked(true);
        }

        //Localisation
        String localisation = "";
        rgLocalisation = (RadioGroup)root.findViewById(R.id.rg_localisation);
        etVilleCP = (EditText) root.findViewById(R.id.et_VilleCP);
        rbGPS = (RadioButton) root.findViewById(R.id.rb_LocalisationGPS);
        rbCP = (RadioButton) root.findViewById(R.id.rb_LocalisationCP);


        String l = SharedPreferencesHelper.getInstance(getContext()).RecupererTypeLocalisationSelectionnee();

        if(l.startsWith("GPS")){
            rbGPS.setChecked(true);
        }
        if(l.startsWith("CP")){
            rbCP.setChecked(true);
            etVilleCP.setText(l.substring(2));
        }

        Button btRecherche = (Button) root.findViewById(R.id.btn_Recherche);
        btRecherche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String tmp = "";
                if(rbGPS.isChecked()){
                    tmp="GPS";
                }

                if(rbCP.isChecked()){
                    tmp="CP"+etVilleCP.getText().toString();
                }


                SharedPreferencesHelper.getInstance(getContext()).SauvegarderTypeLocalisationSelectionnee(tmp);

                String prixSelectionnes = "";
                if(cbPrixFaible.isChecked()){
                    prixSelectionnes = "PrixFaible,";
                }

                if(cbPrixModere.isChecked()){
                    prixSelectionnes += "PrixModere,";
                }

                if(cbPrixEleve.isChecked()){
                    prixSelectionnes += "PrixEleve";
                }

                if(prixSelectionnes.endsWith(","))
                {
                    prixSelectionnes.substring(0,prixSelectionnes.length()-1);
                }

                SharedPreferencesHelper.getInstance(getContext()).SauvegarderPrixSelectionnes(prixSelectionnes);

                //Remise à zéro des champs de recherche
//                tvThemes.setText("");
//                rgLocalisation.clearCheck();
//                etVilleCP.setText("");
//                cbPrixFaible.setChecked(false);
//                cbPrixModere.setChecked(false);
//                cbPrixEleve.setChecked(false);

                ArrayList<Bar> bars = BarHelper.getInstance().Rechercher(getContext());
                SharedPreferencesHelper.getInstance(getContext()).SauvegarderListeBars(bars);

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
            ListeThemes.add(Theme.values()[i].name().replace("_"," "));
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
                            listeSelectedThemes += s.toString() + "\n";
                        }

                        SharedPreferencesHelper.getInstance(getContext()).SauvegarderThemesSelectionnes(listeSelectedThemes);

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
