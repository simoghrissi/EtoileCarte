package com.etoilecarte.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.etoilecarte.Beans.Session;
import com.etoilecarte.Beans.Table;
import com.etoilecarte.R;

import java.util.ArrayList;

import static com.etoilecarte.Activities.MainActivity.contextOfApplication;

/**
 * Created by mghrissi on 30/01/2017.
 */

public class TableActivity extends AppCompatActivity{
    static TableActivity instance;

    ArrayList<Button> arrayButton ;

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
        String buttonId="";

        ViewGroup parentView = (ViewGroup) findViewById(R.id.tableActivity);
        for(int i=0; i < parentView.getChildCount(); i++) {
            //int id = getResources().getIdentifier("button_"+i, "id", getPackageName());
            View childView = parentView.getChildAt(i);
            int resID = childView.getId();
            String []splitid =childView.getResources().getResourceName(resID).toString().split("/");
                 buttonId=splitid[1];
            final String finalButtonId = buttonId;
           // Button b = (Button) findViewById(resID);
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Session.arrayTable.isEmpty()){
                        Session.newTable(finalButtonId);
                        Toast.makeText(TableActivity.this, finalButtonId, Toast.LENGTH_LONG).show();
                        Intent p = new Intent(TableActivity.this, MainActivity.class);
                        startActivity(p);
                        finish();

                    }else{
                        if(!tableDejaPrise(Session.arrayTable,finalButtonId)){
                            Session.newTable(finalButtonId);
                            Toast.makeText(TableActivity.this, finalButtonId, Toast.LENGTH_LONG).show();
                            Intent p = new Intent(TableActivity.this, MainActivity.class);
                            startActivity(p);
                            finish();

                        }else{
                            Toast.makeText(TableActivity.this, "Table deja utilisée", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });

        }

    }

    public boolean tableDejaPrise(ArrayList<Table> table,String idTable){

        for(int i =0; i<table.size();i++){
            if(table.get(i).getIdTable().equalsIgnoreCase(idTable)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_acceuil:
                Intent p = new Intent(TableActivity.this, MainActivity.class);
                startActivity(p);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
