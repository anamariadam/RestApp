package com.example.aplicrestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NuestroMenu extends MainMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestro_menu);

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