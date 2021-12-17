package com.example.aplicrestaurante;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends MainMenu implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient flpc;
    FirebaseAuth a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RestApp");
        a = FirebaseAuth.getInstance();
        a.getCurrentUser();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        flpc = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            super.onRestoreInstanceState(savedInstanceState);
        }




    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Cerrar Aplicación")
                .setMessage("Seguro que quieres cerrar la aplicación?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                            DialogInterface dialog, int which) {
                        a.signOut();
                        Intent intent = new Intent(Intent.ACTION_MAIN); intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("NO", null).show(); }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        flpc.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Add a marker in Sydney and move the camera
                            LatLng ubica = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(ubica).title("Mi ubicación"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(ubica));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubica, 18));
                        }
                    }
                });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ConfirmarDireccion.class);
        String cl="Main";
        String n=" ";
        intent.putExtra("nom", cl);
        intent.putExtra("nombre", n);
        startActivity(intent);
    }
}