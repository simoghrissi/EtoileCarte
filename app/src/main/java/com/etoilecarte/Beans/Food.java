package com.etoilecarte.Beans;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by simo on 16/04/16.
 */
public class Food {



    int idFood;
    String titre    ;
    String smallImage;
    String description ;
    String prix ;
    String bigImage ;

    public Food(){

    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return smallImage;
    }

    public void setImage(String image) {
        this.smallImage = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Food(String titre , String image, String description){

        this.smallImage=image ;
        this.titre= titre ;
        this.description= description;
    }
    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }
}
