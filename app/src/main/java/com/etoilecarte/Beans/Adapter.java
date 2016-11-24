package com.etoilecarte.Beans;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by simo on 16/04/16.
 */
public class Adapter extends BaseAdapter {


    Context c ;
    ArrayList<String> listImage = new ArrayList<>();
    public Adapter(Context c , ArrayList<String> listImage){

        this.c = c ;
        this.listImage= listImage;
    }
    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public Object getItem(int i) {
        return listImage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        ImageView img = new ImageView(c);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(listImage.get(pos)).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        img.setImageBitmap(bitmap);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setLayoutParams(new GridView.LayoutParams(300,300));

        return img;
    }
}
