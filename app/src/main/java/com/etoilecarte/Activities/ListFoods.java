package com.etoilecarte.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.etoilecarte.Beans.CustomAdapterList;
import com.etoilecarte.Beans.Food;
import com.etoilecarte.R;
import com.etoilecarte.WebServices.WebService;
import com.tktm.lyvraison.beans.Article;
import com.tktm.lyvraison.beans.Category;

import java.util.ArrayList;

/**
 * Created by simo on 16/04/16.
 */
public class ListFoods  extends AppCompatActivity implements  AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<Article> articles = null;
    ArrayList<Food> foods=null;
    WebService webService = new WebService(this);

    protected void onCreate(Bundle savedInstanceState) {
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        try {
        setContentView(R.layout.list_view_cat);

        foods = new ArrayList<>();
        ArrayList<Category> categories=null;

            int idCat = intent.getIntExtra("idCat",-1);
                if (idCat == -1){
                    idCat =  MainActivity.idCatgerorie;
                }
           articles=webService.listArticle(idCat,MainActivity.url);

            for(Article article :articles ){
                Food food = new Food();
                food.setTitre(article.getName());
                food.setPrix(String.valueOf(article.getPrice())+" €");
                food.setImage(article.getDescription().getUrlSmallImage());
                food.setDescription(article.getDescription().getText());
                foods.add(food);
            }


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        ListAdapter listAdapter = new CustomAdapterList(this,foods);

         listView = (ListView) findViewById(R.id.listViewCat);
        listView.setAdapter(listAdapter);
      listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        listView.getItemAtPosition(i);
        Intent p=new Intent(ListFoods.this,DetailsActivity.class);
        p.putExtra("largeImage", articles.get(i).getDescription().getUrlLargeImage());
        p.putExtra("text",articles.get(i).getName());
      startActivity(p);
    }
}
