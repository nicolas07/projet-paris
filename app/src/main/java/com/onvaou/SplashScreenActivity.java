package com.onvaou;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_DUREE_OUT = 3000; //en secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        ArrayList<Bar> bars = BarHelper.getInstance().LireFichierBars(getApplicationContext());
        SharedPreferencesHelper.getInstance(getApplicationContext()).SauvegarderListeBars(bars);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_DUREE_OUT);


    }
}
