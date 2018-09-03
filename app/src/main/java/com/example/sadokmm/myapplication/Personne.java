package com.example.sadokmm.myapplication;

import android.graphics.Bitmap;

public class Personne {

    private String id;
    private String nom;
    private String des;
    private Bitmap av;

    public Personne(String id ,String nom, String des , Bitmap av){
        this.id=id;
        this.nom=nom;
        this.des=des;
        this.av=av;
    }


    public String getNom(){
        return this.nom;
    }

    public String getDes(){
        return this.des;
    }


    public Bitmap getAv(){
        return this.av;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAv(Bitmap av) {
        this.av = av;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
