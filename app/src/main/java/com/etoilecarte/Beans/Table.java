package com.etoilecarte.Beans;

import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by mghrissi on 31/01/2017.
 */

public class Table {

    String idTable ;
    ArrayList<Food> foods ;

    public void Table(){};
    public String  getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

}
