package com.example.sadokmm.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.content.Context.LOCATION_SERVICE;
//import static com.example.sadokmm.myapplication.LoginActivity.myCon;
import static com.example.sadokmm.myapplication.MainActivity.list;
import static com.example.sadokmm.myapplication.MainActivity.message;

public class profile extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mapView;
    RatingBar r1;
    float finalRating;
    int nbtotal=0;
    float rating=0;



    public profile() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mapView = (MapView) getView().findViewById(R.id.mymap);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }




        ImageView av=(ImageView)getView().findViewById(R.id.imageView2);
        //av.setImageBitmap(myCon.getAv(message));
        TextView nametext=(TextView)getView().findViewById(R.id.nametext);
       // nametext.setText(myCon.getName(message));
        ImageButton im3=(ImageButton)getView().findViewById(R.id.imageButton3);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        av.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction manager=getFragmentManager().beginTransaction();

                Affiche a=new Affiche();
               // a.setIm(myCon.getAv(message));
                a.show(manager,null);
            }
        });


         r1=(RatingBar)getView().findViewById(R.id.ratingBar);
        final TextView nbTot=(TextView)getView().findViewById(R.id.nbTotal);
        r1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
              //  Toast.makeText(getContext(),String.valueOf(v),Toast.LENGTH_LONG).show();
                rating+=v;
                nbTot.setText(Integer.toString(++nbtotal-1));

                finalRating=rating/nbtotal;
                r1.setRating(finalRating);

            }
        });



    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(35.813687, 10.626142)).title("ISG SOUSSE"));
        CameraPosition Liberty= CameraPosition.builder().target(new LatLng(35.813687, 10.626142)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

        ImageButton button = (ImageButton) getView().findViewById(R.id.myLoc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Rating bar

                Toast.makeText(getContext(),String.valueOf(finalRating),Toast.LENGTH_LONG).show();


                // Enabling MyLocation Layer of Google Map
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mGoogleMap.setMyLocationEnabled(true);

                // Getting LocationManager object from System Service LOCATION_SERVICE
                LocationManager locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

                // Creating a criteria object to retrieve provider
                Criteria criteria = new Criteria();

                // Getting the name of the best provider
                String provider = locationManager.getBestProvider(criteria, true);

                // Getting Current Location
                Location location = locationManager.getLastKnownLocation(provider);

                if (location != null) {
                    // Getting latitude of the current location
                    double latitude = location.getLatitude();

                    // Getting longitude of the current location
                    double longitude = location.getLongitude();

                    // Creating a LatLng object for the current location
                    LatLng latLng = new LatLng(latitude, longitude);

                    LatLng myPosition = new LatLng(latitude, longitude);

                    mGoogleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));
                }

            }
        });

    }
}
