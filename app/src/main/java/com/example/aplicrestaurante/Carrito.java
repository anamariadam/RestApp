package com.example.aplicrestaurante;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Carrito extends MainMenu {
    static ArrayList<Producto> prodComanda = new ArrayList<>();
    private ListView myListView;
    AdaptadorCarrito adaptan;
    EditText conta;
    public static TextView total;
    DatabaseReference db;
    Button boton;
    String direccion,nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        myListView = findViewById(R.id.comanda);
        conta = findViewById(R.id.contador);

        total = findViewById(R.id.total);
        boton = findViewById(R.id.comandaUpdate);
        db = FirebaseDatabase.getInstance().getReference();

        cargarCarrito();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this, Finalizado.class);
                int q = prodComanda.size();
                intent.putExtra("nombre", nombre);
                intent.putExtra("dir",direccion);
                intent.putExtra("quant", q);
                for (int i = 0; i < prodComanda.size();i++) {
                    intent.putExtra("prod"+i, prodComanda.get(i).getNombre());
                    intent.putExtra("preu"+i, prodComanda.get(i).getPrecio());
                    intent.putExtra("unit"+i, prodComanda.get(i).getUnidades());
                    intent.putExtra("descripcion"+i,prodComanda.get(i).getDescripcion());
                }
                /*for (int j = 0; j < prodComanda.size();j++){
                    prodComanda.remove(j);
                }*/
                startActivity(intent);
                }

        });

        totalDinero();

    }

    public static void totalDinero(){
        double tt = 0;
        for (int i = 0;i < prodComanda.size();i++){
        tt += prodComanda.get(i).getPrecio()*prodComanda.get(i).getUnidades();
        }
        String t =String.valueOf(tt);
        total.setText(String.valueOf(String.format("%.2f",tt)));

    }

    public void cargarCarrito(){
        Bundle extras = getIntent().getExtras();
        int num = extras.getInt("quant");
        ArrayList<String> prod = new ArrayList<>();
        ArrayList<String> descr = new ArrayList<>();
        ArrayList<Double> preu = new ArrayList<>();
        ArrayList<Integer> uni = new ArrayList<>();
        for (int i = 0; i < num;i++) {

            prod.add(extras.getString("prod"+i));
            preu.add(extras.getDouble("preu"+i));
            uni.add(extras.getInt("unit"+i));
            descr.add(extras.getString("descripcion"+i));
            Log.e("obj", "cargar carrito "+prod.get(i));
        }
        direccion =extras.getString("dir");
        nombre =extras.getString("nombre");
        for (int i = 0; i < prod.size();i++) {
            Producto p = new Producto(prod.get(i), preu.get(i),descr.get(i), uni.get(i));
            prodComanda.add(p);

        }

        adaptan = new AdaptadorCarrito(this, prodComanda);
        myListView.setAdapter(adaptan);
    }




}