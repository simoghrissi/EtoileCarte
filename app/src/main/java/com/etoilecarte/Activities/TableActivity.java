package com.etoilecarte.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.etoilecarte.R;

import static com.etoilecarte.Activities.MainActivity.contextOfApplication;

/**
 * Created by mghrissi on 30/01/2017.
 */

public class TableActivity extends AppCompatActivity{
    static TableActivity instance;


    public static TableActivity getInstance(){
        if(instance==null){
            return new TableActivity();
        }else{
            return instance;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.table_layout);
        contextOfApplication = getApplicationContext();


        final Button table1 = (Button)findViewById(R.id.table1);
        table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.newTable(table1.getText().toString());
                Intent p = new Intent(TableActivity.this, MainActivity.class);
                startActivity(p);
                finish();
            }
        });
    }


}
