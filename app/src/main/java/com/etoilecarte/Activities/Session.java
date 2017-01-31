package com.etoilecarte.Activities;

import android.content.Context;

/**
 * Created by mghrissi on 30/01/2017.
 */

public class Session {
    public static Session instance = null;
    public static String idTableIncach =null;


    public static void newTable(final String idTable) {

        if (instance != null) {
            return;
        }
        instance = new Session();
        instance.idTableIncach = idTable;
    }
}
