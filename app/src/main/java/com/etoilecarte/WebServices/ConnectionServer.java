package com.etoilecarte.WebServices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by simo on 28/08/2016.
 */
public class ConnectionServer {

    private static ConnectionServer instance;
    String urlString=null ;
    EditText adressText;


    private ConnectionServer() {
    }
        public boolean IsReachable(Context context,String urlString) {



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
                    isReachable = (urlc.getResponseCode() == 404|| urlc.getResponseCode() == 200);
                } catch (IOException e) {
                    Log.e("", e.getMessage());
                }
            }

            return isReachable;
        }

    public static ConnectionServer getInstance(){
        if (null == instance) { // Premie   r appel
            instance = new ConnectionServer();
        }
        return instance;
    }
    }


