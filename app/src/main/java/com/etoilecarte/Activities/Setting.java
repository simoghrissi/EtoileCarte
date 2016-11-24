package com.etoilecarte.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.etoilecarte.R;

/**
 * Created by simo on 15/05/16.
 */
public class Setting extends AppCompatActivity implements View.OnClickListener {

    Button enregistrerBouton = null;
    EditText adressText;
     String  textUrlBefore = null;
    String textUrlAfter =null;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        adressText = (EditText) findViewById(R.id.adress);
        enregistrerBouton = (Button) findViewById(R.id.enregistrerBouton);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        enregistrerBouton.setOnClickListener(this);
        try{
            textUrlBefore =  preferences.getString("ServerName", "");
            adressText.setText(textUrlBefore);
        }catch (Exception e){

        }

    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        if (view == enregistrerBouton) {
            textUrlAfter = adressText.getText().toString();
            editor.putString("ServerName", textUrlAfter);
            editor.apply();
            adressText.setText(textUrlAfter);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            if (textUrlAfter.equals(textUrlBefore)) {
                System.out.println("Vous n'avez rien modifier");

                //  System.out.print(password.getText().toString());
                builder.setMessage("Vous n'avez rien modifier");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            } else {
                System.out.println("modification reussite");
                builder.setMessage("Modification reussite");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setNegativeButton("cancel", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }
}
