package com.example.aplicrestaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartaFirebase extends MainMenu {
    private ExpandableListView myListView;
    private Adaptador adapta;
    private ArrayList<String> categor;
    ArrayList<String> listaId = new ArrayList<>();
    private Map<String, ArrayList<Producto>> subcategor;
    ArrayList<Producto> comanda = new ArrayList<>();
    ArrayList<Producto> listaEntrantes, listaPaellas, listaFideuas, listaPostres, listaVinos;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Producto p;
    private int gp, cp;
    String direccion, nombre;
    public CartaFirebase() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_firebase);

        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        myListView = (ExpandableListView) findViewById(R.id.carta);
        categor = new ArrayList<>();
        subcategor = new HashMap<>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        categor.add("ENTRANTES");
        categor.add("PAELLAS");
        categor.add("FIDEUAS");
        categor.add("POSTRES");
        categor.add("VINOS");

        listaEntrantes = new ArrayList<>();
        listaPaellas = new ArrayList<>();
        listaFideuas = new ArrayList<>();
        listaPostres = new ArrayList<>();
        listaVinos = new ArrayList<>();
        cargar();
        adaptar();
        cargarDireccion();


        myListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Producto p = null;
                if (groupPosition == 0) {
                    p = new Producto(listaEntrantes.get(childPosition).nombre, listaEntrantes.get(childPosition).precio, 1,"");
                    comanda.add(p);
                } else if (groupPosition == 1) {
                    p = new Producto(listaPaellas.get(childPosition).nombre, listaPaellas.get(childPosition).precio, 1,"");
                    comanda.add(p);
                } else if (groupPosition == 2) {
                    p = new Producto(listaFideuas.get(childPosition).nombre, listaFideuas.get(childPosition).precio, 1,"");
                    comanda.add(p);
                } else if (groupPosition == 3) {
                    p = new Producto(listaPostres.get(childPosition).nombre, listaPostres.get(childPosition).precio, 1,"");
                    comanda.add(p);
                } else if (groupPosition == 4) {
                    p = new Producto(listaVinos.get(childPosition).nombre, listaVinos.get(childPosition).precio, 1,"");
                    comanda.add(p);
                }
                Context context = getApplicationContext();
                CharSequence text = "Producto añadido";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return false;
            }

        });

    }

    public void cargar(){

        myRef.child("carta/entrantes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Log.e("firebase", "dins del if ");
                    for (DataSnapshot producteSnapshot : snapshot.getChildren()) {

                        String n = producteSnapshot.child("nombre").getValue().toString();
                        String pr = producteSnapshot.child("precio").getValue().toString();
                        String url = producteSnapshot.child("foto").getValue().toString();
                        double preu = Double.parseDouble(pr);
                        p = new Producto(n, preu,1, url);

                        listaEntrantes.add(p);
                    }
                    subcategor.put(categor.get(0), listaEntrantes);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("carta/paellas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot producteSnapshot : snapshot.getChildren()) {
                        String n = producteSnapshot.child("nombre").getValue().toString();
                        String pr = producteSnapshot.child("precio").getValue().toString();
                        String url = producteSnapshot.child("foto").getValue().toString();
                        double preu = Double.parseDouble(pr);
                        p = new Producto(n, preu,1, url);
                        listaPaellas.add(p);
                    }
                    subcategor.put(categor.get(1), listaPaellas);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("carta/fideuas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot producteSnapshot : snapshot.getChildren()) {
                        String n = producteSnapshot.child("nombre").getValue().toString();
                        String pr = producteSnapshot.child("precio").getValue().toString();
                        String url = producteSnapshot.child("foto").getValue().toString();
                        double preu = Double.parseDouble(pr);
                        p = new Producto(n, preu, 1,url);
                        listaFideuas.add(p);
                    }
                    subcategor.put(categor.get(2), listaFideuas);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("carta/postres").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot producteSnapshot : snapshot.getChildren()) {
                        String n = producteSnapshot.child("nombre").getValue().toString();
                        String pr = producteSnapshot.child("precio").getValue().toString();
                        String url = producteSnapshot.child("foto").getValue().toString();
                        double preu = Double.parseDouble(pr);
                        p = new Producto(n, preu,1, url);
                        listaPostres.add(p);
                    }
                    subcategor.put(categor.get(3), listaPostres);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("carta/vinos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot producteSnapshot : snapshot.getChildren()) {
                        String n = producteSnapshot.child("nombre").getValue().toString();
                        String pr = producteSnapshot.child("precio").getValue().toString();
                        String url = producteSnapshot.child("foto").getValue().toString();
                        double preu = Double.parseDouble(pr);
                        p = new Producto(n, preu,1, url);
                        listaVinos.add(p);
                    }
                    subcategor.put(categor.get(4), listaVinos);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void adaptar() {

        adapta = new Adaptador(categor, subcategor, this);
        myListView.setAdapter(adapta);
    }
    public void cargarDireccion(){

        Bundle extras = getIntent().getExtras();
        direccion =extras.getString("dir");
        nombre = extras.getString("nombre");

        int num = extras.getInt("quant");
        ArrayList<String> prod = new ArrayList<>();
        ArrayList<String> descr = new ArrayList<>();
        ArrayList<Double> preu = new ArrayList<>();
        ArrayList<Integer> uni = new ArrayList<>();
        direccion =extras.getString("dir");
        nombre =extras.getString("nombre");
        for (int i = 0; i < num;i++) {
            prod.add(extras.getString("prod"+i));
            preu.add(extras.getDouble("preu"+i));
            uni.add(extras.getInt("unit"+i));
            descr.add(extras.getString("descripcion"+i));
            Log.e("obj", "cargar finalizado "+prod.get(i));
        }
        for (int i = 0; i < prod.size();i++) {
            Producto p = new Producto(prod.get(i), preu.get(i), descr.get(i),uni.get(i));

            comanda.add(p);
        }
    }



    public void onClickCarrito(View view) {
        Intent intent = new Intent(this, Carrito.class);
        int q = comanda.size();
        intent.putExtra("dir",direccion);
        intent.putExtra("nombre", nombre);
        intent.putExtra("quant", q);

        for (int i = 0; i < comanda.size();i++) {
            intent.putExtra("prod"+i, comanda.get(i).getNombre());
            intent.putExtra("preu"+i, comanda.get(i).getPrecio());
            intent.putExtra("descripcion"+i,comanda.get(i).getDescripcion());
            intent.putExtra("unit"+i, comanda.get(i).getUnidades());
        }
        for (int j = 0; j < comanda.size();j++){
            comanda.remove(j);
        }
        startActivity(intent);
    }
    public void onClickMenu(View view) {
        Intent intent = new Intent(this, NuestroMenu.class);
        intent.putExtra("dir",direccion);
        intent.putExtra("nombre", nombre);
        startActivity(intent);
    }

}