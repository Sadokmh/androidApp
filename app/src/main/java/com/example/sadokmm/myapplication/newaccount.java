package com.example.sadokmm.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

//import static com.example.sadokmm.myapplication.LoginActivity.myCon;

public class newaccount extends Fragment {


    private Bitmap myNewImage;
    private int idAdmins;
    public newaccount() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newaccount,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        try {
            c();
          //  idAdmins = myCon.lastItemId();

            ImageView imAdd = (ImageView) getView().findViewById(R.id.immAd);
            imAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openGalerie();
                }
            });
        }
        catch (Exception e){
            TextView t=(TextView)getView().findViewById(R.id.photoTextView);
            t.setText(e.toString());
        }
    }





    int id_intentImg=100;
    private void openGalerie(){
        Intent intentImg=new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg,id_intentImg);
    }





    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == id_intentImg) {
            try {
                Uri uri = data.getData();

                InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);

                myNewImage = decodeStream;
                ImageView b2 = (ImageView) getView().findViewById(R.id.immAd);
                b2.setImageURI(uri);


            } catch (Exception e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }


    }


    private void c(){
        final EditText name = (EditText)getView().findViewById(R.id.nameee);
        final EditText pass = (EditText)getView().findViewById(R.id.passwordddd);
        final EditText pass2 = (EditText)getView().findViewById(R.id.password2);
        final EditText email = (EditText)getView().findViewById(R.id.email);
        final TextView t = (TextView)getView().findViewById(R.id.c);
        ImageButton im=(ImageButton)getView().findViewById(R.id.imageButton2);



        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (name.getText().toString().equals("")) Toast.makeText(getContext(),"type username",Toast.LENGTH_LONG).show();
                    else if (!(pass.getText().toString().equals(pass2.getText().toString())) || pass.getText().toString().equals("")) Toast.makeText(getContext(),"Invalid passwords",Toast.LENGTH_LONG).show();
                    else  {

                        //myCon.insertAdmins(++idAdmins,name.getText().toString(), email.getText().toString(),pass.getText().toString() , myNewImage);

                        getActivity().getSupportFragmentManager().popBackStack();
                    }

                }
                catch (Exception e){
                    t.setText(e.toString());
                }
            }
        });

    }

}
