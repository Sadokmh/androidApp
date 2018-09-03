package com.example.sadokmm.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static android.support.v4.provider.FontsContractCompat.FontRequestCallback.RESULT_OK;
//import static com.example.sadokmm.myapplication.LoginActivity.myCon;
import static com.example.sadokmm.myapplication.MainActivity.list;

public class Ajout extends Fragment {


    private FragmentManager manager=getFragmentManager();
    private Bitmap myNewImage;
    private  ImageButton b2;
    private int idPersonne;
    String path;
    EditText name;
    EditText dess;
    Activity mActivity;


    int id2=1;


    public Ajout() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ajout,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myNewImage=BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_pic);
        //opencam();
        ImageButton b=(ImageButton) getView().findViewById(R.id.btnAdd);
        ImageView b2=(ImageView)getView().findViewById(R.id.addIm);

       // idPersonne=myCon.lastItemIdPersonne();

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseSrouceImage();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    name = (EditText) getView().findViewById(R.id.name);
                    dess = (EditText) getView().findViewById(R.id.dess);
                  //  myCon.insertPersonne(++idPersonne,name.getText().toString(),dess.getText().toString(),myNewImage);
                   uploadImage();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                catch (Exception e){
                    EditText name = (EditText) getView().findViewById(R.id.name);
                    name.setText(e.toString());
                    Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }



private void chooseSrouceImage(){
    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
    alert.setIcon(R.drawable.ci);
    alert.setTitle("wini tasswira ? :D ");
    alert.setPositiveButtonIcon(getContext().getDrawable(R.drawable.ic_camera));
    alert.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {


            opencam();
        }
    });
    alert.setNegativeButtonIcon(getContext().getDrawable(R.drawable.ic_photo_library));
    alert.setNegativeButton("Galerie", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            openGalerie();
        }
    });
    alert.show();
}


    void opencam() {

        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, id2);
            }

        }
        catch (Exception e){
            EditText name = (EditText) getView().findViewById(R.id.name);
            name.setText(e.toString());
        }
    }





int id_intentImg=100;
private void openGalerie(){
        Intent intentImg=new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg,id_intentImg);
}






public void post(){

    String myurl="http://192.168.3.137:8080/profile/p";

    try {

        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, myurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected java.util.Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("nom", name.getText().toString());
                params.put("des", dess.getText().toString());
               params.put("img", "hi");


                return params;
            }

        };
        requestQueue.add(postRequest);
    }
    catch (Exception e){
        Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
    }

}



//image convert





    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == Activity.RESULT_OK && requestCode == id_intentImg){
            try {
                 Uri uri = data.getData();
                 Uri filePath=data.getData();


                 /*InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                 Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                 myNewImage=decodeStream;*/

                myNewImage = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), filePath);


                ImageView b2=(ImageView) getView().findViewById(R.id.addIm);
                b2.setImageBitmap(myNewImage);


            }
            catch (Exception e){
                Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }


        else if (requestCode == id2 && resultCode == Activity.RESULT_OK) {


            Bundle extras = data.getExtras();
            myNewImage = (Bitmap) extras.get("data");
            ImageView imageView=(ImageView)getView().findViewById(R.id.addIm);
            imageView.setImageBitmap(myNewImage);


        }
        else {
            ImageView imageView=(ImageView)getView().findViewById(R.id.addIm);
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_pic));
        }

    }



    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);



        if (context instanceof Activity){
            mActivity=(Activity) context;
        }

        }



        //UPLOAD IMAGE




    private void uploadImage(){
        String myurl="http://192.168.3.137:8080/profile/p";

        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(mActivity,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(mActivity,s , Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(mActivity, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                //Getting Image Name
                String name1 = name.getText().toString().trim();
                String des = dess.getText().toString().trim();

                //Converting Bitmap to String
                String image =  getStringImage(myNewImage);

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("nom", name1);
                params.put("des", des);
                params.put("img", image);


                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }



}
