package com.example.aplicrestaurante;

import android.content.Intent;
import android.os.Bundle;
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
    ArrayList<Producto> prodComanda = new ArrayList<>();
    private ListView myListView;
    AdaptadorCarrito adaptan;
    EditText conta;
    TextView total;
    DatabaseReference db;
    Button sum, res, botonSubirDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        myListView = findViewById(R.id.comanda);
        conta = findViewById(R.id.contador);
        sum = (Button)findViewById(R.id.sumar);
        res = (Button)findViewById(R.id.restar);
        total = findViewById(R.id.total);
        botonSubirDatos = findViewById(R.id.comandaUpdate);
        db = FirebaseDatabase.getInstance().getReference();

        cargarCarrito();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (sum.isPressed()){
                    int unit = prodComanda.get(position).getUnidades();
                    int tot = unit+1;
                    conta.setText(tot);
                    prodComanda.get(position).setUnidades(unit+1);
                }
                if (res.isActivated()){
                    int unit = prodComanda.get(position).getUnidades();
                    int tot = unit+1;
                    conta.setText(tot);
                    prodComanda.get(position).setUnidades(unit-1);
                }

            }

        });


        botonSubirDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prodComanda.size() != 0) {
                    Map<String, Object> comandaSubida = new HashMap<>();
                    int i;
                    for (i = 0; i < prodComanda.size(); i++) {
                        comandaSubida.put("producto " + i, prodComanda.get(i).nombre);
                        comandaSubida.put("precio producto"+i, prodComanda.get(i).precio);
                        comandaSubida.put("unidades producto " + i, prodComanda.get(i).unidades);

                    }
                    db.child("comandas").push().setValue(comandaSubida);
                    prodComanda.clear();
                    adaptan = new AdaptadorCarrito(Carrito.this, prodComanda);
                    myListView.setAdapter(adaptan);
                    Intent intent = new Intent(Carrito.this, Finalizado.class);
                    startActivity(intent);

                }
            }
        });

    }

    public void onClickSumar(View v){

    }

    public void onClickRestar(View v){

    }


    public void cargarCarrito(){
        Bundle extras = getIntent().getExtras();
        int num = extras.getInt("quant");
        ArrayList<String> prod = new ArrayList<>();
        ArrayList<Double> preu = new ArrayList<>();
        for (int i = 0; i < num;i++) {
            prod.add(extras.getString("prod"));
        }
        for (int i = 0; i < num;i++) {
            preu.add(extras.getDouble("preu"));
        }
        for (int i = 0; i < prod.size();i++) {
            Producto p = new Producto(prod.get(i), preu.get(i), 1,4);

            prodComanda.add(p);
        }

        adaptan = new AdaptadorCarrito(this, prodComanda);
        myListView.setAdapter(adaptan);
    }



}