package com.example.aplicrestaurante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ConfirmarDireccion extends MainMenu {
    private FusedLocationProviderClient fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_direccion);
        fl = LocationServices.getFusedLocationProviderClient(this);
        cargarDatos();

    }
    public void cargarDatos () {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fl.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {

                    Geocoder geocoder = new Geocoder(ConfirmarDireccion.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (addresses != null) {

                            String localidad = addresses.get(0).getLocality();
                            String calle = addresses.get(0).getThoroughfare();
                            String numero = addresses.get(0).getFeatureName();
                            String codigoPostal = addresses.get(0).getPostalCode();

                            EditText pobInp = (EditText) findViewById(R.id.poblacioninput);
                            pobInp.setText(localidad);

                            EditText calleInp = (EditText) findViewById(R.id.calleinput);
                            calleInp.setText(calle);

                            EditText numInp = (EditText) findViewById(R.id.numeroinput);
                            numInp.setText(numero);

                            EditText codeInp = (EditText) findViewById(R.id.codigopostalinput);
                            codeInp.setText(codigoPostal);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, NuestraCarta.class);
        startActivity(intent);
    }
}