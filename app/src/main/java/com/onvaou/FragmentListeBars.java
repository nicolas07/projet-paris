package com.onvaou;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class FragmentListeBars extends Fragment{

    public static Fragment newInstance(Context context) {
        FragmentListeBars f = new FragmentListeBars();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_listebars, null);
        return root;
    }
}
