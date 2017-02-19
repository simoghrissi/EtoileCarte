package com.etoilecarte.WebServices;

import android.app.ProgressDialog;
import android.os.AsyncTask;


import java.io.IOException;

import static com.etoilecarte.Activities.MainActivity.getContextOfApplication;
import static com.etoilecarte.Activities.PrincipalFragment.url;

/**
 * Created by simo on 18/02/2017.
 */


public class BackgroundConfirmation extends AsyncTask<Void, Integer, Void>{

    ProgressDialog progressDialog;
    AbstractConnexion abstractConnexion;

    public  BackgroundConfirmation(AbstractConnexion abstractConnexion){
        this.abstractConnexion= abstractConnexion;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        Boolean testConnection=false;
        testConnection = ConnectionServer.getInstance().IsReachable(getContextOfApplication(), url);
        if(testConnection)
            abstractConnexion.onSuccessed();
        else
            abstractConnexion.onFailed();

        return  null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // progressDialog = ProgressDialog.show(, "Please wait...", "Retrieving data ...", true);

    }


    @Override
    protected void onPostExecute(Void aBoolean) {
        super.onPostExecute(aBoolean);

    }

}