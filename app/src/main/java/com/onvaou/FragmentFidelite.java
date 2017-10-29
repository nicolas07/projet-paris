package com.onvaou;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentFidelite extends Fragment {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private EditText etCode;
    private ImageView ivAjoutCode;
    private TextView tvCoupon;

    public static Fragment newInstance(Context context) {
        FragmentFidelite f = new FragmentFidelite();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_fidelite, null);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("E-Carte de fidélité");

        final ArrayList<Bitmap> list = getListeEtoile();
        gridView = (GridView) root.findViewById(R.id.gvEtoiles);
        gridAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout, list);
        gridView.setAdapter(gridAdapter);

        ivAjoutCode  = (ImageView) root.findViewById(R.id.ivAjoutCode);
        etCode = (EditText) root.findViewById(R.id.etCode);
        tvCoupon = (TextView) root.findViewById(R.id.tvCoupon);
        tvCoupon.setVisibility(View.INVISIBLE);

        ivAjoutCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size() < 8)
                {
                    if(etCode.getText().length() == 0)
                    {
                        Toast.makeText(getContext(), "Veuillez saisir un code", Toast.LENGTH_SHORT).show();
                    } else {
                        list.add(BitmapFactory.decodeResource(getResources(), R.mipmap.star));
                        gridAdapter.notifyDataSetChanged();
                        etCode.setText("");
                        setNombreEtoile(list.size());

                        if(list.size() == 8){
                            Toast.makeText(getContext(), "Coupon de réduction disponible", Toast.LENGTH_SHORT).show();
                            tvCoupon.setText("Coupon de réduction : "+getCouponReduction());
                            tvCoupon.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        tvCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNombreEtoile(0);
                tvCoupon.setText("");
                list.clear();
                int i = list.size();
                gridAdapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    private void setNombreEtoile(int nombre)
    {
        SharedPreferences sp = getActivity().getSharedPreferences("Preferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("NombreEtoile", nombre);
        editor.commit();
    }

    private int getNombreEtoile()
    {
        SharedPreferences sp = getActivity().getSharedPreferences("Preferences", Activity.MODE_PRIVATE);
        return sp.getInt("NombreEtoile", 0);
    }

    private ArrayList<Bitmap> getListeEtoile()
    {
        ArrayList<Bitmap> liste = new ArrayList<Bitmap>();
        int nb = getNombreEtoile();
        if(nb > 0)
        {
            for(int i = 0; i<nb;i++){
                liste.add(BitmapFactory.decodeResource(getResources(), R.mipmap.star));
            }
        }
        return liste;
    }

    private String getCouponReduction(){

        char[] chars1 = "ABCDEF012GHIJKL345MNOPQR678STUVWXYZ9".toCharArray();
        StringBuilder sb1 = new StringBuilder();
        Random random1 = new Random();
        for (int i = 0; i < 8; i++)
        {
            char c1 = chars1[random1.nextInt(chars1.length)];
            sb1.append(c1);
        }
        return sb1.toString();
    }

}
