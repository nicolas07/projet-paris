package com.onvaou;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nicolas on 23/09/2017.
 */
public class LoginActivity extends AppCompatActivity {

    private Button bouton_login;
    private EditText editText_email;
    private EditText editText_motdepasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        // TODO : Mettre en place login avec identifiant et mot de passe

        bouton_login = (Button) findViewById(R.id.btn_login);
        editText_email = (EditText) findViewById(R.id.input_email);
        editText_motdepasse = (EditText) findViewById(R.id.input_password);

        bouton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_email.getText().toString();
                String motdepasse = editText_motdepasse.getText().toString();
                if(email != null && !email.isEmpty() && motdepasse != null && !motdepasse.isEmpty())
                {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir un email et/ou un mot de passe",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
