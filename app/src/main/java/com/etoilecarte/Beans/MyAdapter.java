package com.etoilecarte.Beans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etoilecarte.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mghrissi on 12/02/2017.
 */

public class MyAdapter extends BaseAdapter {

    private List<CategorieMenuItem> menuItems = new ArrayList<CategorieMenuItem>();
    private LayoutInflater inflater;
    Context context;

    public MyAdapter(Context context,ArrayList<CategorieMenuItem> menuItems) {
        //String address = context.getResources().getString(R.string.adressConnect);

        this.context = context ;
        this.menuItems= menuItems;

    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return menuItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;
        inflater = LayoutInflater.from(context);
        if (v == null) {
            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        CategorieMenuItem item = (CategorieMenuItem) getItem(i);

        picture.setImageBitmap(item.url);

        name.setText(item.name);

        return v;
    }


    }
