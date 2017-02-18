package com.etoilecarte.WebServices;

import android.app.ProgressDialog;
import android.os.AsyncTask;


import static com.etoilecarte.Activities.MainActivity.getContextOfApplication;
import static com.etoilecarte.Activities.PrincipalFragment.url;

/**
 * Created by simo on 18/02/2017.
 */


public class BackgroundConfirmation extends AsyncTask<Void, Integer, Boolean>{

    ProgressDialog progressDialog;
    AbstractConnexion abstractConnexion;

    public  BackgroundConfirmation(AbstractConnexion abstractConnexion){
        this.abstractConnexion= abstractConnexion;
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean testConnection=false;
        try {
            testConnection = ConnectionServer.getInstance().IsReachable(getContextOfApplication(), url);
            abstractConnexion.onSuccessed();

        } catch (Exception e) {
            e.printStackTrace();
            abstractConnexion.onFailed();
        }

        return testConnection;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // progressDialog = ProgressDialog.show(, "Please wait...", "Retrieving data ...", true);

    }


    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

    }

}