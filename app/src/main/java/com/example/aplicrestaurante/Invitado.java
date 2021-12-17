package com.example.aplicrestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Invitado extends MainMenu {
Button bt;
String direccion,nombre;
EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitado);

        bt = findViewById(R.id.inv);
        et = findViewById(R.id.nombreinput);
        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        cargarDireccion();
    }

    public void onClickIrAlaCarta(View view) {
        if (et.getText().length() != 0) {
            Intent i = new Intent(this, CartaFirebase.class);
            i.putExtra("dir", direccion);
            nombre = et.getText().toString();
            i.putExtra("nombre", nombre);
            startActivity(i);
        }else{
            Toast.makeText(this,"Debes escribir un nombre",Toast.LENGTH_SHORT).show();

        }
    }
    public void cargarDireccion(){

        Bundle extras = getIntent().getExtras();
        direccion =extras.getString("dir");
    }
}