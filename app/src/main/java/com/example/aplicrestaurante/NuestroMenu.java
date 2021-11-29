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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NuestroMenu extends MainMenu {
    FirebaseDatabase database;
    DatabaseReference myRef ;
    RadioButton entrante1, entrante2, entrante3,entrante4, principal1,principal2,principal3,principal4, postre1, postre2, postre3, postre4;
    private ArrayList<String> llistaProductes;
    private  String  producte ;
    public ArrayList<Producto> llistaMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestro_menu);

        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        entrante1 = findViewById(R.id.entrante1);
        entrante2 = findViewById(R.id.entrante2);
        entrante3 = findViewById(R.id.entrante3);
        entrante4 = findViewById(R.id.entrante4);

        principal1 = findViewById(R.id.principal1);
        principal2 = findViewById(R.id.principal2);
        principal3 = findViewById(R.id.principal3);
        principal4 = findViewById(R.id.principal4);

        postre1 = findViewById(R.id.postre1);
        postre2 = findViewById(R.id.postre2);
        postre3 = findViewById(R.id.postre3);
        postre4 = findViewById(R.id.postre4);

        database =  FirebaseDatabase.getInstance();
        myRef = database.getReference("menu");
        llistaProductes=new ArrayList<>();
        llistaMenus=new ArrayList<>();


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

    public void OnClickAnadir(View view){
        if (entrante1.isChecked() || entrante2.isChecked() || entrante3.isChecked() || entrante4.isChecked()){
            if(principal1.isChecked() || principal2.isChecked() || principal3.isChecked() || principal4.isChecked()){
                if(postre1.isChecked() || postre2.isChecked() || postre3.isChecked() || postre4.isChecked()){
                    Producto p = new Producto("Menu Diario", 12,1,"");
                    llistaMenus.add(p);
                    Intent intent = new Intent(this, Carrito.class);
                    int q = llistaMenus.size();

                    intent.putExtra("quant", q);
                    for (int i = 0; i < llistaMenus.size();i++) {
                        intent.putExtra("prod"+i, llistaMenus.get(i).getNombre());
                    }
                    for (int i = 0; i < llistaMenus.size();i++) {
                        intent.putExtra("preu"+i, llistaMenus.get(i).getPrecio());
                    }
                    for (int j = 0; j < llistaMenus.size();j++){
                        llistaMenus.remove(j);
                    }
                    startActivity(intent);
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Debes seleccionar 3 platos";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }else{
                Context context = getApplicationContext();
                CharSequence text = "Debes seleccionar 3 platos";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Debes seleccionar 3 platos";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
    public void onClickCarta(View view) {
        Intent intent = new Intent(this, CartaFirebase.class);
        startActivity(intent);
    }
}