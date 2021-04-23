package com.example.aplicrestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Carrito extends MainMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, NuestraCarta.class);
        startActivity(intent);
    }
}