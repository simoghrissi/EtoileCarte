package com.etoilecarte.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.etoilecarte.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.etoilecarte.Activities.MainActivity.SHARED_PREFS_KEY_PANIER;


/**
 * Created by mghrissi on 25/11/2016.
 */

public class AjouterPanierActivity extends AppCompatActivity {

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pannier);

        mListView = (ListView) findViewById(R.id.listView_panier);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_KEY_PANIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = prefs.getStringSet(SHARED_PREFS_KEY_PANIER, null);
        List<String> listPanier=new ArrayList<String>(set);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listPanier));
    }
}
