package com.example.sadokmm.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Vector;

//import static com.example.sadokmm.myapplication.LoginActivity.myCon;

public class MainActivity extends AppCompatActivity {

    ActionBar toolbar;
    BottomNavigationView mBottomNavigationView;
    static Vector<Personne> list=new Vector<Personne>();
    private FragmentManager manager= getSupportFragmentManager();
    static String message;
    Bitmap im;

    Fragment f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=getSupportActionBar();
        toolbar.setTitle("Home");
        remplir();
        loadHomeFragment();



        // DBBBB








        setupBottomNav();
        //Floating Button

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                addPic();
            }
        });



        //
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        /*Intent intent = getIntent();
         message = intent.getStringExtra("name");
*/
    }




    //Ajout photo
    //





public void setupBottomNav() {
    mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);

    mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.home:
                    loadHomeFragment();
                    toolbar.setTitle("Home");


                    return true;
                case R.id.list:
                    loadGridFragment();
                    toolbar.setTitle("Home");
                    return true;
                case R.id.profile:
                    loadProfile();
                    //toolbar.setTitle(myCon.getName(message));
                    return true;
            }
            return false;
        }
    });
}

    //Remplir liste
   void remplirListe() {
       /* Bitmap im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.amine);
        list.add(new Personne(0,"Amine", "Ahmed Amine Rassas ISSAT", im));
        im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.boha);
        list.add(new Personne(1,"Boha", "Baha Eddine Bettaieb ISGs", im));
        im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.fedi);
        list.add(new Personne(2,"Fedi", "Fedi Abdelhamid Kantaoui FLSHs", im));
        im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.monta);
        list.add(new Personne(3,"Monta", "Montassar Yakoubi ISIMM", im));
        /*ListView lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(mya);*/


       /*Bitmap im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.sadok);
       myCon.insertPersonne(1000,"Sadok","Sadok Mourad Mhiri",im);
       im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.amine);
       myCon.insertPersonne(1001,"Amine","Ahmed Amine Rassas",im);
       im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.fedi);
       myCon.insertPersonne(1002,"Fedi","Fedi Abdelhamid Kantaoui",im);
       im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.boha);
       myCon.insertPersonne(1003,"Baha","Baha Eddine Bettaieb",im);
       im = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.monta);
       myCon.insertPersonne(1004,"Monta","Montassar Yaakoubi",im);

       list=myCon.charger();

        */


   }

   //NODE
   public  void remplir(){


       try{




           list=new Vector<Personne>();

           String myurl="http://192.168.3.137:8080/profile";

           final RequestQueue requestQueue= Volley.newRequestQueue(this);

           JsonArrayRequest objectRequest=new JsonArrayRequest(JsonArrayRequest.Method.GET,
                   myurl,
                   null,
                   new com.android.volley.Response.Listener<JSONArray>(){
                       @Override
                       public void onResponse(final JSONArray response) {
                           try {


                               int i = 0;
                               String nom, des, id;
                               String av;
                               Personne p;
                               ImageRequest imageRequest;


                               while (!(response.isNull(i))) {
                                   id = response.getJSONObject(0).get("_id").toString();
                                   nom = response.getJSONObject(0).get("nom").toString();
                                   des = response.getJSONObject(0).get("des").toString();
                                   av = response.getJSONObject(i).get("img").toString();

                                   byte[] decodeString = Base64.decode(av, Base64.DEFAULT);
                                   im = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                   try {
                                       p = new Personne(response.getJSONObject(i).get("_id").toString(), response.getJSONObject(i).get("nom").toString(), response.getJSONObject(i).get("des").toString(), im);
                                       list.add(p);
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }


                                   //get IMG


                                   imageRequest = new ImageRequest("http://192.168.3.137:8080" + av,
                                           new Response.Listener<Bitmap>() {
                                               @Override
                                               public void onResponse(Bitmap bitmap) {
                                                  /* Bitmap immm = bitmap;
                                                   try {
                                                       Personne p = new Personne(response.getJSONObject(finalI).get("_id").toString(),response.getJSONObject(finalI).get("nom").toString(),response.getJSONObject(finalI).get("des").toString(),immm);
                                                       list.add(p);
                                                   } catch (JSONException e) {
                                                       e.printStackTrace();
                                                   }*/
                                               }
                                           }, 0, 0, ImageView.ScaleType.FIT_CENTER, null, new Response.ErrorListener() {
                                       @Override
                                       public void onErrorResponse(VolleyError error) {
                                           im = BitmapFactory.decodeResource(getResources(), R.drawable.ic_error);
                                       }
                                   });
                                   requestQueue.add(imageRequest);
                                   // p = new Personne(id, nom, des,immm);
                                   // list.add(p);
                                i++;
                               }
                           }catch (JSONException e1) {
                               e1.printStackTrace();



                       } catch (Exception e) {
                               e.printStackTrace();
                               TextView t=(TextView)findViewById(R.id.textView3);
                               t.setText(e.getMessage());
                           }

                       }

                   },
                   new com.android.volley.Response.ErrorListener(){

                       @Override
                       public void onErrorResponse(VolleyError error) {
                           TextView t=(TextView)findViewById(R.id.textView3);
                           t.setText(error.getMessage());
                           //Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
                       }
                   }
           );

           requestQueue.add(objectRequest);

       }
       catch(Exception e){

           TextView t=(TextView)findViewById(R.id.textView3);
           t.setText(e.toString());

       }



   }









   //BA3D NODE

    public void loadHomeFragment() {

        homeFrag fragment = new homeFrag();
        //fragment.applyGrid();
        //fragment.aa();
        manager.beginTransaction()
                .replace(R.id.container,fragment)
                .commit();

    }

    private void loadGridFragment() {

        listFrag fragment = new listFrag();
        manager.beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }

    private void loadProfileFragment() {

        artisteFrag fragment = new artisteFrag();
        manager.beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }


    private void addPic(){
        Ajout fragment = new Ajout();
        manager.beginTransaction()
                .replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit();
    }

    private void loadProfile(){
        profile fragment = new profile();
        manager.beginTransaction()
                .replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit();
    }

    public static Vector<Personne> getList() {
        return list;
    }

    public static void setList(Vector<Personne> list) {
        MainActivity.list = list;
    }


    //back press


    @Override
    public boolean dispatchKeyEvent (KeyEvent event) {

        if (event.getAction()==KeyEvent.ACTION_DOWN && event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
           loadHomeFragment();
            return true;
        }
        return false;
    }
}
