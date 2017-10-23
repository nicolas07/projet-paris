package com.onvaou;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentRecherche extends Fragment{

    private ImageView img_PlusTheme;
    private RadioGroup rg_Prix;
    private RadioButton rb_PrixFaible;
    private static ArrayList<String> mChoixBars;

    private TextView test;

    public static Fragment newInstance(Context context) {
        FragmentRecherche f = new FragmentRecherche();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = (View) inflater.inflate(R.layout.fragment_recherche, null);

        img_PlusTheme = (ImageView) root.findViewById(R.id.img_PlusTheme);
        img_PlusTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfficherPopUpChoixThemes(getContext());
            }
        });

        return root;
    }

    private static void AfficherPopUpChoixThemes(Context context){

        final Context ctx = context;

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.select_dialog_multichoice);
        arrayAdapter.add("Bar à cocktails");
        arrayAdapter.add("Bar à vins");
        arrayAdapter.add("Pubs Irlandais");
        arrayAdapter.add("Travernes");
        arrayAdapter.add("Bars Sportifs");
        arrayAdapter.add("Bars à Bières");
        arrayAdapter.add("Bars à Musique");
        arrayAdapter.add("Bars Sans Alcool");
        arrayAdapter.add("Bars Latinos");
        arrayAdapter.add("Bars Corses");

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ctx);
        builderSingle.setIcon(R.mipmap.ic_cocktail);
        builderSingle.setTitle("Thèmes : ");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
            }
        });
        builderSingle.show();

    }
}
