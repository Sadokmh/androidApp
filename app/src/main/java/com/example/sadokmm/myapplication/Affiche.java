package com.example.sadokmm.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Affiche extends DialogFragment implements View.OnClickListener {

    private Bitmap im;

    View view;




    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){


        view=inflater.inflate(R.layout.affiche,container,false);
        ImageButton buC=(ImageButton)view.findViewById(R.id.buC);
        ImageView im_of=(ImageView)view.findViewById(R.id.im_aff);
        im_of.setImageBitmap(im);
        buC.setOnClickListener(this);
        return view;


    }

    @Override
    public void onClick(View v){

        this.dismiss();

    }


    public void setIm(Bitmap ime){
        this.im=ime;
    }


    public Bitmap getIm() {
        return im;
    }


}


