package com.etoilecarte.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.etoilecarte.Activities.ListFoods;
import com.etoilecarte.Beans.Adapter;
import com.etoilecarte.R;
import com.etoilecarte.WebServices.WebService;
import com.tktm.lyvraison.beans.Category;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class noMain extends Activity implements View.OnClickListener{


    GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.layout_categorie);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        WebService webService = new WebService(this);

        ArrayList<Category> listCategorie = webService.listProduit("");


        ImageView mj = (ImageView) findViewById(R.id.mj);
       String imageS = "http://schoolminibusleasing.uk.com/wp-content/uploads/2014/08/New-Header-Image.png";
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageS).getContent());



            mj.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
       //ImageView mj = (ImageView) findViewById(R.id.mj);
        ImageView nom = (ImageView) findViewById(R.id.nom);
         // nom.setImageResource(R.drawable.dessert);
        mj.setImageResource(R.drawable.dessert);
        nom.setOnClickListener(this);
        //nom.setImageBitmap(bitmap);
        ArrayList<String> imageList = new ArrayList<>();

        for(Category cat : listCategorie){

            imageList.add(cat.getDescription().getUrlLargeImage());

        }

        gv = (GridView)findViewById(R.id.gridView);

        Adapter adapter = new Adapter(this,imageList);
        gv.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        Intent i=new Intent(noMain.this,ListFoods.class);
        //i.putExtra("idpizza", pizza);
        startActivity(i);
    }
}
