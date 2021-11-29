package com.example.aplicrestaurante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ConfirmarDireccion extends MainMenu {
    private FusedLocationProviderClient fl;
    EditText pobInp, calleInp, numInp, codeInp, info, tlf;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_direccion);
        fl = LocationServices.getFusedLocationProviderClient(this);

        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        pobInp = (EditText) findViewById(R.id.poblacioninput);
        calleInp = (EditText) findViewById(R.id.calleinput);
        numInp = (EditText) findViewById(R.id.numeroinput);
        codeInp = (EditText) findViewById(R.id.codigopostalinput);
        info = (EditText) findViewById(R.id.infoadicionalinput);
        tlf = (EditText) findViewById(R.id.telefonoinput);

        cargarDatos();
        cargarname();


    }

    public void cargarDatos() {

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


                            pobInp.setText(localidad);
                            calleInp.setText(calle);
                            numInp.setText(numero);
                            codeInp.setText(codigoPostal);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void cargarname() {
        Bundle extras = getIntent().getExtras();
        name = extras.getString("nom");
    }

    public void onClick(View view) {
        if (name.equals("Finalizado")) {
            Intent i = new Intent(this, Finalizado.class);
            String direccion = pobInp.getText().toString()+" "+ codeInp.getText().toString() + "\n" + calleInp.getText().toString() + " " + numInp.getText().toString() + "\n" + info.getText().toString() + "\n" + tlf.getText().toString();
            i.putExtra("dir", direccion);
            startActivity(i);
        } else {
            Intent intent = new Intent(this, CartaFirebase.class);
            String direccion = pobInp.getText().toString()+" "+ codeInp.getText().toString() + "\n" + calleInp.getText().toString() + " " + numInp.getText().toString() + "\n" + info.getText().toString() + "\n" + tlf.getText().toString();
            intent.putExtra("dir", direccion);
            startActivity(intent);
        }
    }
}