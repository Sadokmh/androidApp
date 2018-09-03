package com.example.sadokmm.myapplication;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

//import static com.example.sadokmm.myapplication.LoginActivity.myCon;
import static com.example.sadokmm.myapplication.MainActivity.getList;
import static com.example.sadokmm.myapplication.MainActivity.list;

public class listFrag extends Fragment {

     MyCustomAdapter mya;
    private Bitmap image ;


    public listFrag() {



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listfrag,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mya=new MyCustomAdapter(getList());

        ListView lv=(ListView)getView().findViewById(R.id.lv);
        lv.setAdapter(mya);

      //  list=myCon.charger();

        //LV item Click photo ZOOm

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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



    //LIST VIEW CLASS

    class MyCustomAdapter extends BaseAdapter {

        Vector<Personne> listv= new Vector<Personne>();
        MyCustomAdapter(Vector<Personne> list){
            this.listv=list;
        }

        @Override
        public int getCount(){
            return listv.size();
        }

        @Override
        public Personne getItem(int position){
            return listv.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i , View view , ViewGroup viewGroup){
            LayoutInflater l=getLayoutInflater();
            View v= l.inflate(R.layout.list_it,null);

            TextView nom=(TextView) v.findViewById(R.id.nom);
            TextView des=(TextView) v.findViewById(R.id.des);
            ImageView av=(ImageView) v.findViewById(R.id.av);

            nom.setText(listv.get(i).getNom());
            des.setText(listv.get(i).getDes());
            av.setImageBitmap(listv.get(i).getAv());

            return v;
        }
    }



}
