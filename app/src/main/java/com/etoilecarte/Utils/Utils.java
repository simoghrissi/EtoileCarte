package com.etoilecarte.Utils;

import com.etoilecarte.Beans.Session;
import com.etoilecarte.Beans.Table;

/**
 * Created by simo on 01/02/2017.
 */

public class Utils {

    public static Table searchTableById(String tableId){
        for(int i = 0; i< Session.arrayTable.size(); i++){
            if(Session.arrayTable.get(i).getIdTable().equalsIgnoreCase(tableId)){
                return Session.arrayTable.get(i);
            }
        }
        return null;
    }
}
