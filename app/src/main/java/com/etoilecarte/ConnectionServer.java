package com.etoilecarte;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by simo on 28/08/2016.
 */
public class ConnectionServer  extends AppCompatActivity {

    private static ConnectionServer instance;
    String urlString=null ;

    private ConnectionServer() {
    }
        public boolean IsReachable(Context context) {
            View viewSetting = LayoutInflater.from(ConnectionServer.this).inflate(R.layout.setting, null);

            EditText adressText = (EditText)viewSetting.findViewById(R.id.adress);
            urlString = adressText.getText().toString();
            // First, check we have any sort of connectivity
            final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
            boolean isReachable = false;

            if (netInfo != null && netInfo.isConnected()) {
                // Some sort of connection is open, check if server is reachable
                try {
                    URL url = new URL(urlString);
                    //URL url = new URL("http://10.0.2.2");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setRequestProperty("User-Agent", "Android Application");
                    urlc.setRequestProperty("Connection", "close");
                    urlc.setConnectTimeout(10 * 1000);
                    urlc.connect();
                    isReachable = (urlc.getResponseCode() == 200);
                } catch (IOException e) {
                    //Log.e(TAG, e.getMessage());
                }
            }

            return isReachable;
        }

    public static ConnectionServer getInstance(){
        if (null == instance) { // Premier appel
            instance = new ConnectionServer();
        }
        return instance;
    }
    }


