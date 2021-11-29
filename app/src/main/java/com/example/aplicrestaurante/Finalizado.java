package com.example.aplicrestaurante;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Finalizado extends MainMenu {
    Button inicia, finaliza;
    private ListView lw;
    RadioButton rb;
    static TextView contenidoDireccion, total;
    AdaptaComanda adapta;
    static ArrayList<Producto> prodComanda = new ArrayList<>();
    static ArrayList<Producto> prodComanda2 = new ArrayList<>();
    DatabaseReference db;
    String direccion;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizado);

        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        inicia = findViewById(R.id.inicio);
        finaliza = findViewById(R.id.confirma);
        lw = findViewById(R.id.listComand);
        rb = findViewById(R.id.efectivo);
        contenidoDireccion = findViewById(R.id.contenidoDireccion);
        total = findViewById(R.id.total);

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        cargarCarrito();
        cargarDireccion();
        totalDinero();

        FirebaseUser user = mAuth.getCurrentUser();



        if (user ==  null) {
            inicia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Finalizado.this, Login.class);
                    startActivity(intent);
                }
            });

        }else{
            inicia.setText("Sesión iniciada");
        }


    }

    public void cargarCarrito(){
        Bundle extras = getIntent().getExtras();
        int num = extras.getInt("quant");
        ArrayList<String> prod = new ArrayList<>();
        ArrayList<Double> preu = new ArrayList<>();
        ArrayList<Integer> uni = new ArrayList<>();
        direccion =extras.getString("dir");
        for (int i = 0; i < num;i++) {
            prod.add(extras.getString("prod"+i));
            preu.add(extras.getDouble("preu"+i));
            uni.add(extras.getInt("unit"+i));
            Log.e("obj", "cargar finalizado "+prod.get(i));
        }
        for (int i = 0; i < prod.size();i++) {
            Producto p = new Producto(prod.get(i), preu.get(i), uni.get(i),"");

            prodComanda.add(p);
        }


        adapta = new AdaptaComanda(Finalizado.this, prodComanda);
        lw.setAdapter(adapta);


    }
    public void onClickfin(View v) {
        if (rb.isChecked() && contenidoDireccion.getText().length() != 15) {
            if (prodComanda.size() != 0) {
                Map<String, Object> comandaSubida = new HashMap<>();
                int i;
                for (i = 0; i < prodComanda.size(); i++) {
                    Producto pp = new Producto(prodComanda.get(i).nombre, prodComanda.get(i).precio, prodComanda.get(i).unidades, prodComanda.get(i).foto);
                    comandaSubida.put("Producto " + i, pp);
                }
                Boolean st = true;
                comandaSubida.put("Estado ", st);
                comandaSubida.put("Direccion ", direccion);
                db.child("comandas").push().setValue(comandaSubida);
                prodComanda.clear();
                adapta = new AdaptaComanda(Finalizado.this, prodComanda);
                lw.setAdapter(adapta);
                Intent intent = new Intent(Finalizado.this, ConfirmacionPedido.class);
                startActivity(intent);

            }
        }
    }
    @Override
    public void onBackPressed() {
        for (int j = 0; j < prodComanda.size();j++){
            prodComanda.remove(j);

        }
        finish();
    }

    public void cargarDireccion(){
        contenidoDireccion.setText(direccion);
    }

    public static void totalDinero(){
        double tt = 0;
        for (int i = 0;i < prodComanda.size();i++){
            tt += prodComanda.get(i).getPrecio()*prodComanda.get(i).getUnidades();
        }
        String t =String.valueOf(tt);
        total.setText(String.valueOf(String.format("%.2f",tt)));

    }


    public void onClickEditaDireccion(View view) {
        Intent intent = new Intent(Finalizado.this, ConfirmarDireccion.class);
        String cl="Finalizado";
        intent.putExtra("nom", cl);
        startActivity(intent);

    }
}
