package com.etoilecarte.Activities;/** * Created by simo on 25/04/16. */import android.content.Context;import android.content.DialogInterface;import android.content.Intent;import android.content.SharedPreferences;import android.os.Bundle;import android.support.v4.app.Fragment;import android.support.v4.app.FragmentManager;import android.support.v7.app.AlertDialog;import android.support.v7.app.AppCompatActivity;import android.view.LayoutInflater;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.EditText;import android.widget.ImageView;import com.etoilecarte.R;import com.etoilecarte.Utils.FragmentManagerUtil;public class MainActivity extends AppCompatActivity {    public static Context contextOfApplication;    ImageView imageLogo = null;    EditText password = null;    View view = null;    public static final String SHARED_PREFS_KEY_PANIER="PanierListKEY";    FragmentManager fragmentManager;   static MainActivity instance;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);      /*  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();        StrictMode.setThreadPolicy(policy);        */        setContentView(R.layout.main);        contextOfApplication=getApplicationContext();         fragmentManager = getSupportFragmentManager();        TableFragment tableFragment = new TableFragment().newInstance("");        fragmentManager.beginTransaction().replace(R.id.content_frame,tableFragment).commit();    }    public static Context getContextOfApplication(){        return contextOfApplication;    }    @Override    protected void onResume() {        super.onResume();    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        getMenuInflater().inflate(R.menu.setting, menu);        return true;    }    @Override    public boolean onOptionsItemSelected(MenuItem item) {        switch (item.getItemId()) {            case R.id.action_setting:                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_alert, null);                password = (EditText) view.findViewById(R.id.password);                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);                //  System.out.print(password.getText().toString());                builder.setMessage("SettingFragment").setView(view).setPositiveButton("Login", new DialogInterface.OnClickListener() {                    @Override                    public void onClick(DialogInterface dialogInterface, int i) {                        String a = password.getText().toString();                        if (a.equals("passwordx")) {                            FragmentManagerUtil.replaceMainFragments(MainActivity.this,new SettingFragment().newInstance());                           /* SettingFragment settingFragment = new SettingFragment().newInstance();                             fragmentManager = getSupportFragmentManager();                            fragmentManager.beginTransaction().replace(R.id.content_frame, settingFragment).commit();                        */                        }                    }                }).setNegativeButton("cancel", null);                AlertDialog alert = builder.create();                alert.show();                break;            case R.id.action_panier:                PanierFragment panierFragment = new PanierFragment();                fragmentManager.beginTransaction().replace(R.id.content_frame, panierFragment).commit();                break;            case R.id.action_table:                TableFragment tableFragment = new TableFragment();                fragmentManager.beginTransaction().replace(R.id.content_frame, tableFragment).commit();                break;        }        return super.onOptionsItemSelected(item);    }}