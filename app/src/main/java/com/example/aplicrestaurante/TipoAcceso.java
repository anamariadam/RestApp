package com.example.aplicrestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class TipoAcceso extends MainMenu {
Button invitado , logear;
String direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_acceso);
        invitado = findViewById(R.id.invitado);
        logear = findViewById(R.id.registrado);

        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        cargarDireccion();
    }
    public void onClickSesion(View view) {
        Intent i = new Intent(this, Login.class);
        i.putExtra("dir", direccion);
        startActivity(i);
    }

    public void onClickInvitado(View view) {
        Intent i = new Intent(this, Invitado.class);
        i.putExtra("dir", direccion);
        startActivity(i);
    }
    public void cargarDireccion(){

        Bundle extras = getIntent().getExtras();
        direccion =extras.getString("dir");
    }
}