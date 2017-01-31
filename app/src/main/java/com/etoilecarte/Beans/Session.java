package com.etoilecarte.Beans;


import java.util.ArrayList;

/**
 * Created by mghrissi on 30/01/2017.
 */

public class Session {
    public static Session instance = null;
    public static String idTableIncach =null;
    public static Table table;

    public static ArrayList<Table>arrayTable = new ArrayList<>();

    public static void newTable(final String idTable) {
         table = new Table();
        arrayTable.add(table);
        instance = new Session();
        instance.idTableIncach = idTable;
        table.setIdTable(idTable);
    }
}
