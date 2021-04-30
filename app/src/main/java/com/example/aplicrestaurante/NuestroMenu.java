package com.example.aplicrestaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NuestroMenu extends MainMenu {
    FirebaseDatabase database;
    DatabaseReference myRef ;

    private ArrayList<String> llistaProductes;
    private  String  producte ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestro_menu);
        RadioButton entrante1 = findViewById(R.id.entrante1);
        RadioButton entrante2 = findViewById(R.id.entrante2);
        RadioButton entrante3 = findViewById(R.id.entrante3);
        RadioButton entrante4 = findViewById(R.id.entrante4);


        RadioButton principal1 = findViewById(R.id.principal1);
        RadioButton principal2 = findViewById(R.id.principal2);
        RadioButton principal3 = findViewById(R.id.principal3);
        RadioButton principal4 = findViewById(R.id.principal4);

        RadioButton postre1 = findViewById(R.id.postre1);
        RadioButton postre2 = findViewById(R.id.postre2);
        RadioButton postre3 = findViewById(R.id.postre3);
        RadioButton postre4 = findViewById(R.id.postre4);


        database =  FirebaseDatabase.getInstance();
        myRef = database.getReference("menu");
        llistaProductes=new ArrayList<>();





        getLlistaProductes(new MyCallback() {
            @Override
            public void onCallback(ArrayList products) {
                String e1 =  llistaProductes.get(0);
                entrante1.setText(e1);
                String e2 = (String)  llistaProductes.get(1);
                entrante2.setText(e2);
                String e3 = (String)  llistaProductes.get(2);
                entrante3.setText(e3);
                String e4 = (String)  llistaProductes.get(3);
                entrante4.setText(e4);

                String p1 =  llistaProductes.get(4);
                principal1.setText(p1);
                String p2 = (String)  llistaProductes.get(5);
                principal2.setText(p2);
                String p3 = (String)  llistaProductes.get(6);
                principal3.setText(p3);
                String p4 = (String)  llistaProductes.get(7);
                principal4.setText(p4);

                String po1 =  llistaProductes.get(8);
                postre1.setText(po1);
                String po2 = (String)  llistaProductes.get(9);
                postre2.setText(po2);
                String po3 = (String)  llistaProductes.get(10);
                postre3.setText(po3);
                String po4 = (String)  llistaProductes.get(11);
                postre4.setText(po4);

            }
        });

    }

    public void getLlistaProductes(final MyCallback myCallback){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                llistaProductes.clear();
                for (DataSnapshot producteSnapshot: dataSnapshot.getChildren()) {
                    producte = (String) producteSnapshot.getKey();
                    llistaProductes.add(producte);
                }
                // Into onDataChange method!!
                myCallback.onCallback(llistaProductes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Carrito.class);
        startActivity(intent);
    }
    public void onClickCarta(View view) {
        Intent intent = new Intent(this, NuestraCarta.class);
        startActivity(intent);
    }





}