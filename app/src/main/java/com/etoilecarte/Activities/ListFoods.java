package com.etoilecarte.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
                food.setIdFood(article.getId());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
