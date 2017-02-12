package com.etoilecarte.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.etoilecarte.R;
import com.etoilecarte.Utils.FragmentManagerUtil;


/**
 * Created by simo on 15/05/16.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    Button enregistrerBouton = null;
    EditText adressText;
     String  textUrlBefore = null;
    String textUrlAfter =null;
    View rootView;


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.setting, container, false);
        return rootView;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adressText = (EditText) rootView.findViewById(R.id.adress);
        enregistrerBouton = (Button) rootView.findViewById(R.id.enregistrerBouton);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        enregistrerBouton.setOnClickListener(this);
        try{
            textUrlBefore =  preferences.getString("ServerName", "");
            adressText.setText(textUrlBefore);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();

        if (view == enregistrerBouton) {
            textUrlAfter = adressText.getText().toString();
            editor.putString("ServerName", textUrlAfter);
            editor.apply();
            adressText.setText(textUrlAfter);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            if (textUrlAfter.equals(textUrlBefore)) {
                System.out.println("Vous n'avez rien modifier");

                //  System.out.print(password.getText().toString());
                builder.setMessage("Vous n'avez rien modifier");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FragmentManagerUtil.replaceMainFragments(getActivity(),new PrincipalFragment());

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
                        FragmentManagerUtil.replaceMainFragments(getActivity(),new PrincipalFragment());

                    }
                }).setNegativeButton("cancel", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }
}
