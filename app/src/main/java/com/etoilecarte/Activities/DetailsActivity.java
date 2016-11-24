package com.etoilecarte.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.etoilecarte.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by simo on 21/04/16.
 */
public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_article);
        Intent intent = getIntent();
        String largeImage = intent.getStringExtra("largeImage");
        String description = intent.getStringExtra("text");

        ImageView imagefood = (ImageView)findViewById(R.id.imageDetails);

        TextView textDetails = (TextView)findViewById(R.id.textDetails);
        TextView text = (TextView)findViewById(R.id.text);

        Bitmap bitmap=null;
        try {
         bitmap = BitmapFactory.decodeStream((InputStream)new URL(largeImage).getContent());
           // bitmap=  flip(bitmap);
            imagefood.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagefood.setImageBitmap(bitmap);
        text.setText(description);
    }



    Bitmap flip(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap dst = Bitmap.createBitmap(d, 0, 0, d.getWidth(), d.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }
}
