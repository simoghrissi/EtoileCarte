package com.etoilecarte.Beans;

/**
 * Created by simo on 16/04/16.
 */
public class Categorie {

    String nom;
    String image ;
    String texte ;

    public Categorie(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
