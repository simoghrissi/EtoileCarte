package com.etoilecarte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by simo on 15/05/16.
 */
public class Setting extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting);

        EditText adressText = (EditText) findViewById(R.id.adress);


    }
}
