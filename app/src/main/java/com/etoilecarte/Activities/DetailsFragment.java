package com.etoilecarte.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.etoilecarte.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.etoilecarte.Activities.ListFoodsFragment.FOOD_IMAGE_KEY;
import static com.etoilecarte.Activities.ListFoodsFragment.FOOD_TEXT_KEY;

/**
 * Created by simo on 21/04/16.
 */
public class DetailsFragment extends Fragment {

    public String getlargeImageId() {
        return getArguments().getString(FOOD_IMAGE_KEY);
    }
    public String getTextId() {
        return getArguments().getString(FOOD_TEXT_KEY);
    }
    View rootView;
    public static DetailsFragment newInstance(String largeImageId,String textId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(FOOD_IMAGE_KEY, largeImageId);
        args.putString(FOOD_TEXT_KEY, textId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.details_article, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView imagefood = (ImageView)rootView.findViewById(R.id.imageDetails);

        TextView textDetails = (TextView)rootView.findViewById(R.id.textDetails);
        TextView text = (TextView)rootView.findViewById(R.id.text);

        Bitmap bitmap=null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(getlargeImageId()).getContent());
            // bitmap=  flip(bitmap);
            imagefood.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagefood.setImageBitmap(bitmap);
        text.setText(getTextId());

    }
}
