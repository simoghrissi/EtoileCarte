package com.etoilecarte.Beans;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etoilecarte.Activities.MainActivity;
import com.etoilecarte.Beans.Food;
import com.etoilecarte.R;
import com.etoilecarte.Utils.AutoResizeTextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.etoilecarte.Activities.MainActivity.SHARED_PREFS_KEY_PANIER;

/**
 * Created by simo on 14/04/16.
 */
public class CustomAdapterList extends ArrayAdapter<Food> {
    ArrayList listPanier;
    Context applicationContext;
    SharedPreferences prefs;
    public CustomAdapterList(Context context, ArrayList<Food> foods){
        super(context, R.layout.custom_row ,foods);
         applicationContext = MainActivity.getContextOfApplication();
         prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Food food = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_cat,parent,false);
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row,parent ,false);


        AutoResizeTextView titre = (AutoResizeTextView)(customView.findViewById((R.id.titre)));
        final AutoResizeTextView description = (AutoResizeTextView)(customView.findViewById(R.id.description));
        ImageView smallImage = (ImageView)(customView.findViewById(R.id.imageFood));
        final AutoResizeTextView prix = (AutoResizeTextView)(customView.findViewById(R.id.prix));
        ImageView plusPannier= (ImageView)(customView.findViewById(R.id.icon_plus));
        ImageView moinPannier =(ImageView)(customView.findViewById(R.id.icon_moins));
        String idFood;
        final TextView textCount = (TextView)(customView.findViewById(R.id.count_plat));
            plusPannier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textCount.setText(Integer.toString(Integer.parseInt((textCount.getText().toString()))+1));
                    addToPanier(Integer.toString(food.getIdFood()));
                }
            });
        moinPannier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCount.setText(Integer.toString(Integer.parseInt(textCount.getText().toString())-1));
                deleteFromPanier(Integer.toString(food.getIdFood()));
            }
        });
        titre.setText(food.getTitre());
        description.setText(food.getDescription());
        prix.setText(food.getPrix());
        food.setIdFood(food.getIdFood());
        try {

            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(food.getImage()).getContent());
            smallImage.setImageBitmap(bitmap);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return customView;
    }

    public void addToPanier(String t) {
        if (null == listPanier) {
            listPanier = new ArrayList<String>();
        }
        listPanier.add(t);
        // call preferences from MainActivity;
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(listPanier);
        editor.putStringSet(SHARED_PREFS_KEY_PANIER,set);
        editor.commit();
    }

    public void deleteFromPanier(String id){

        Set<String> set = prefs.getStringSet(SHARED_PREFS_KEY_PANIER,null);
        listPanier=new ArrayList<String>(set);

      for(int i =0 ;i < listPanier.size();i++){
                if(listPanier.get(i).equals(id)){
                    listPanier.remove(listPanier.get(i));
                }
      }
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> newSet = new HashSet<String>();
        newSet.addAll(listPanier);
        editor.putStringSet(SHARED_PREFS_KEY_PANIER,newSet);
        editor.commit();
    }


}
