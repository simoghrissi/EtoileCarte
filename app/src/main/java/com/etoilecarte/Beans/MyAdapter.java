package com.etoilecarte.Beans;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etoilecarte.Activities.MainActivity;
import com.etoilecarte.Activities.PrincipalFragment;
import com.etoilecarte.R;
import com.etoilecarte.WebServices.WebService;
import com.tktm.lyvraison.beans.Category;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mghrissi on 12/02/2017.
 */

public class MyAdapter extends BaseAdapter {

    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public MyAdapter(Context context) {
        //String address = context.getResources().getString(R.string.adressConnect);
        WebService webService = new WebService(context);

        // Asynck Task
        ArrayList<Category> listCategorie = webService.listProduit(PrincipalFragment.url);
        inflater = LayoutInflater.from(context);


        for (Category cat : listCategorie) {

            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(PrincipalFragment.url+"/" + cat.getDescription().getImagePath()).getContent());
                items.add(new Item(cat.getName(), bitmap, cat.getId()));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
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

        if (v == null) {
            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = (Item) getItem(i);

        picture.setImageBitmap(item.url);

        name.setText(item.name);

        return v;
    }

    public class Item {
        final String name;
        final Bitmap url;
        public final int id;

        Item(String name, Bitmap url, int id) {
            this.name = name;
            this.id = id;
            this.url = url;
        }
    }
}