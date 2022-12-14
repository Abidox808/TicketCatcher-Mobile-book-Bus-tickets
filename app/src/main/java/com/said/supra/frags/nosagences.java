package com.said.supra.frags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.said.supra.R;


public class nosagences extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nosagences, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //set position of marker
                LatLng Agadir = new LatLng(30.4163782,-9.5679348);
                LatLng Marrakech = new LatLng(31.630626,-8.0193754);
                LatLng Casablanca = new LatLng(33.57465,-7.59092);
                LatLng Rabat = new LatLng(33.9682227,-6.8842381);
                LatLng Tanger = new LatLng(35.7285848,-5.8638637);
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(Agadir).title("Gare Agadir"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(Agadir));
                googleMap.addMarker(new MarkerOptions().position(Marrakech).title("Gare Marrakech"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(Marrakech));
                googleMap.addMarker(new MarkerOptions().position(Casablanca).title("Gare Casablanca"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(Casablanca));
                googleMap.addMarker(new MarkerOptions().position(Rabat).title("Gare Rabat"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(Rabat));
                googleMap.addMarker(new MarkerOptions().position(Tanger).title("Gare Tanger"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(Tanger));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Agadir,5));
            }
        });

        return view;
    }
}