package com.etoilecarte.Beans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.etoilecarte.R;

/**
 * Created by simo on 12/05/16.
 */
public class PopUpAlert extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_alert);
    }
}
