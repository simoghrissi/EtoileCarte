package com.etoilecarte.Beans;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.etoilecarte.Beans.Food;
import com.etoilecarte.R;
import com.etoilecarte.Utils.AutoResizeTextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by simo on 14/04/16.
 */
public class CustomAdapterList extends ArrayAdapter<Food> {

    public CustomAdapterList(Context context, ArrayList<Food> foods){
        super(context, R.layout.custom_row ,foods);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Food food = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_cat,parent,false);
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row,parent ,false);


        AutoResizeTextView titre = (AutoResizeTextView)(customView.findViewById((R.id.titre)));
        AutoResizeTextView description = (AutoResizeTextView)(customView.findViewById(R.id.description));
        ImageView smallImage = (ImageView)(customView.findViewById(R.id.imageFood));
        AutoResizeTextView prix = (AutoResizeTextView)(customView.findViewById(R.id.prix));
        ImageView plusPannier= (ImageView)(customView.findViewById(R.id.icon_plus));
        ImageView moinPannier =(ImageView)(customView.findViewById(R.id.icon_moins));

        titre.setText(food.getTitre());
        description.setText(food.getDescription());
        prix.setText(food.getPrix());

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




}
