package com.example.sadokmm.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

//import static com.example.sadokmm.myapplication.LoginActivity.myCon;
import static com.example.sadokmm.myapplication.MainActivity.getList;
import static com.example.sadokmm.myapplication.MainActivity.list;

public class homeFrag extends Fragment {

    private Vector<Personne> l=getList();
    private Bitmap image;


    public homeFrag() {


      /*  try {

          */



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homefrag, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        GridView gv=(GridView) getView().findViewById(R.id.gvv);
        gv.setAdapter(new ListRessources(this.getContext(),l));


        //=myCon.charger();

        //Itemclick photo ZOOM


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                FragmentTransaction manager=getFragmentManager().beginTransaction();

                image=list.get(i).getAv();
                Affiche a=new Affiche();
                a.setIm(image);
                a.show(manager,null);

            }
        });





    }


//GET IMAGE METHOD

    public Bitmap getImage() {
        return image;
    }



    // GET LIST METHOD

    public Vector<Personne> getLiost(){
        return list;
    }


    //GridView CLASS
    class ListRessources extends BaseAdapter {

        Vector<Personne> listv=new Vector<>();
        Context context;
        public ListRessources(Context context,Vector<Personne> list){
            this.context=context;
            this.listv=list;

        }

        @Override
        public int getCount(){
            return listv.size();
        }

        @Override
        public Object getItem(int position){
            return listv.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position , View convertView , ViewGroup parent){
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.list_it_grid,null);
            TextView name=(TextView)row.findViewById(R.id.nom);
            TextView des=(TextView)row.findViewById(R.id.des);
            ImageView av=(ImageView)row.findViewById(R.id.av);
            final Personne p=listv.get(position);

            name.setText(p.getNom());
            des.setText(p.getDes());
            av.setImageBitmap(p.getAv());

            return row;
        }

    }

}
