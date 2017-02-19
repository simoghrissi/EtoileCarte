package com.etoilecarte.Beans;

import android.graphics.Bitmap;

/**
 * Created by simo on 19/02/2017.
 */

public class CategorieMenuItem {

        final String name;
        final Bitmap url;
        public final int id;

    public CategorieMenuItem(String name, Bitmap url, int id) {
            this.name = name;
            this.id = id;
            this.url = url;
        }
}
