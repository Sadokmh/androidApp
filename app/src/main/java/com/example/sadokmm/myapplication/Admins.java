package com.example.sadokmm.myapplication;

import android.graphics.Bitmap;

public class Admins {


    private int id;
    private String name;
    private String email;
    private String pass;
    private Bitmap av;

    public Admins(int id, String nom, String email, String pass, Bitmap av) {
        this.id = id;
        this.name = nom;
        this.email = email;
        this.pass = pass;
        this.av = av;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Bitmap getAv() {
        return av;
    }

    public void setAv(Bitmap av) {
        this.av = av;
    }

}
