package com.etoilecarte.Beans;


import android.content.Context;

import com.etoilecarte.Activities.PrincipalFragment;
import com.etoilecarte.WebServices.WebService;
import com.tktm.lyvraison.beans.Article;
import com.tktm.lyvraison.beans.Category;

import java.util.ArrayList;


/**
 * Created by mghrissi on 30/01/2017.
 */

public class Session {
    public static Session instance = null;
    public static String idTableIncach =null;
    public static Table table;
    public static ArrayList<Table>arrayTable = new ArrayList<>();
    public static ArrayList<Category> listCategorie =null;
    public static ArrayList<Article> listArticle =null;
    Context mcontext ;
    public static void newTable(final String idTable) {
         table = new Table();
        arrayTable.add(table);
        instance.idTableIncach = idTable;
        table.setIdTable(idTable);
    }

    public ArrayList<Category> getListCategorie(Context context){
        mcontext=context;
        WebService webService = new WebService(context);
        if(listCategorie==null){
            listCategorie= webService.listProduit(PrincipalFragment.url);
        }
        return listCategorie;
    }

    public ArrayList<Article> getListArticle(String id , String url){
        WebService webService = new WebService(mcontext);
        if(listArticle==null){
            listArticle=webService.listArticle(id,url);
        }
        return  listArticle;
    }
}
