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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentRecherche extends Fragment implements CompoundButton.OnCheckedChangeListener{

    //Ambiance
    private ImageView igChoixAmbiance;
    private TextView tvAmbiances;

    //Enseigne
    private ImageView igChoixEnseigne;
    private TextView tvEnseignes;

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

    //Orientation
    private RadioButton rbGay;
    private RadioButton rbHetero;

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

        //Ambiances
        tvAmbiances = (TextView) root.findViewById(R.id.textView_ambiances);
        String t = SharedPreferencesHelper.getInstance(getContext()).RecupererAmbiancesSelectionnesString();
        tvAmbiances.setText(t);
        igChoixAmbiance = (ImageView) root.findViewById(R.id.img_PlusTheme);
        igChoixAmbiance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfficherDialogChoixAmbiance();
            }
        });

        //Enseignes
        tvEnseignes = (TextView) root.findViewById(R.id.textView_enseignes);
        String e = SharedPreferencesHelper.getInstance(getContext()).RecupererEnseignesSelectionnesString();
        tvEnseignes.setText(e);
        igChoixEnseigne = (ImageView) root.findViewById(R.id.img_PlusEnseigne);
        igChoixEnseigne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfficherDialogChoixEnseigne();
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

        //Orientation
        rbGay = (RadioButton) root.findViewById(R.id.rb_gay);
        rbHetero = (RadioButton) root.findViewById(R.id.rb_hetero);

        String o = SharedPreferencesHelper.getInstance(getContext()).RecupererOrientationSelectionnee();

        if(o.startsWith("Gay")){
            rbHetero.setChecked(true);
        }
        if(o.startsWith("Hetero")){
            rbHetero.setChecked(true);
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


                String tmpO = "";
                if(rbGay.isChecked()){
                    tmpO="Gay";
                }

                if(rbHetero.isChecked()){
                    tmpO="Hetero";
                }


                SharedPreferencesHelper.getInstance(getContext()).SauvegarderOrientationSelectionnee(tmpO);

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


    private void AfficherDialogChoixAmbiance() {

        final ArrayList<String> seletedAmbiances = new ArrayList<>();
        ArrayList<String> ListeAmbiances = new ArrayList<>();
        for(int i = 0; i < Ambiance.values().length; i++){
            ListeAmbiances.add(Ambiance.values()[i].name().replace("_"," "));
        }

        final String[] ambiances = ListeAmbiances.toArray(new String[ListeAmbiances.size()]);

        boolean[] checkeditems = new boolean[ambiances.length];
        Arrays.fill(checkeditems, Boolean.FALSE);
        String ListeAmbiancesSelectionnees = SharedPreferencesHelper.getInstance(getContext()).RecupererAmbiancesSelectionnesString();

         for(int i = 0; i < ambiances.length;i++){
            if(ListeAmbiancesSelectionnees.contains(ambiances[i])){
                checkeditems[i] = true;
            }
         }

        boolean[] test = checkeditems;


        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Choix des ambiances")
                .setMultiChoiceItems(ambiances, checkeditems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedAmbiances.add(ambiances[indexSelected]);
                        } else if (seletedAmbiances.contains(ambiances[indexSelected])) {
                            // Else, if the item is already in the array, remove it
                            seletedAmbiances.remove(ambiances[indexSelected]);
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here

                        String listeSelectedAmbiances = "";

                        for (Object s : seletedAmbiances)
                        {
                            listeSelectedAmbiances += s.toString() + "\n";
                        }

                        if(listeSelectedAmbiances != ""){
                            SharedPreferencesHelper.getInstance(getContext()).SauvegarderAmbiancesSelectionnes(listeSelectedAmbiances);
                        }

                        String tmp = "";
                        if(rbGPS.isChecked()){
                            tmp="GPS";
                        }

                        if(rbCP.isChecked()){
                            tmp="CP"+etVilleCP.getText().toString();
                        }


                        SharedPreferencesHelper.getInstance(getContext()).SauvegarderTypeLocalisationSelectionnee(tmp);

                        String tmpO = "";
                        if(rbGay.isChecked()){
                            tmpO="Gay";
                        }

                        if(rbHetero.isChecked()){
                            tmpO="Hetero";
                        }


                        SharedPreferencesHelper.getInstance(getContext()).SauvegarderOrientationSelectionnee(tmpO);


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

    private void AfficherDialogChoixEnseigne() {

        final ArrayList<String> seletedEnseignes = new ArrayList<>();
        ArrayList<String> ListeEnseignes = new ArrayList<>();
        for(int i = 0; i < Enseigne.values().length; i++){
            ListeEnseignes.add(Enseigne.values()[i].name().replace("_"," "));
        }

        final String[] enseignes = ListeEnseignes.toArray(new String[ListeEnseignes.size()]);

        boolean[] checkeditems = new boolean[enseignes.length];
        Arrays.fill(checkeditems, Boolean.FALSE);
        String ListeEnseignesSelectionnees = SharedPreferencesHelper.getInstance(getContext()).RecupererEnseignesSelectionnesString();

        for(int i = 0; i < enseignes.length;i++){
            if(ListeEnseignesSelectionnees.contains(enseignes[i])){
                checkeditems[i] = true;
            }
        }

        boolean[] test = checkeditems;


        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Choix des enseignes")
                .setMultiChoiceItems(enseignes, checkeditems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedEnseignes.add(enseignes[indexSelected]);
                        } else if (seletedEnseignes.contains(enseignes[indexSelected])) {
                            // Else, if the item is already in the array, remove it
                            seletedEnseignes.remove(enseignes[indexSelected]);
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here

                        String listeSelectedEnseignes = "";

                        for (Object s : seletedEnseignes)
                        {
                            listeSelectedEnseignes += s.toString() + "\n";
                        }

                        if(listeSelectedEnseignes != ""){
                            SharedPreferencesHelper.getInstance(getContext()).SauvegarderEnseignesSelectionnes(listeSelectedEnseignes);
                        }

                        String tmp = "";
                        if(rbGPS.isChecked()){
                            tmp="GPS";
                        }

                        if(rbCP.isChecked()){
                            tmp="CP"+etVilleCP.getText().toString();
                        }


                        SharedPreferencesHelper.getInstance(getContext()).SauvegarderTypeLocalisationSelectionnee(tmp);

                        String tmpO = "";
                        if(rbGay.isChecked()){
                            tmpO="Gay";
                        }

                        if(rbHetero.isChecked()){
                            tmpO="Hetero";
                        }


                        SharedPreferencesHelper.getInstance(getContext()).SauvegarderOrientationSelectionnee(tmpO);



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
