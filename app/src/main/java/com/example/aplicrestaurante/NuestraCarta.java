package com.example.aplicrestaurante;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NuestraCarta extends MainMenu {
    private ExpandableListView myListView;
    private Adaptador adapta;
    private ArrayList<String> categor;
    private Map<String, ArrayList<Producto>> subcategor;
    ArrayList<Producto>comanda = new ArrayList<>();
    ArrayList<Producto> listaEntrantes, listaPaellas, listaFideuas, listaPostres, listaVinos;

    Producto p;
    private int gp, cp;

    public NuestraCarta() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestra_carta);

        myListView = (ExpandableListView) findViewById(R.id.carta);
        categor = new ArrayList<>();
        subcategor = new HashMap<>();


        cargarCarta();


        myListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                gp = groupPosition;
                cp = childPosition;
                return true;
            }
            public void onClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id){

            }

        });

    }

    public void onClick(View view) {
        String cat = categor.get(gp);
        if(cat == "ENTRANTES"){
            p = new Producto(listaEntrantes.get(cp).nombre,listaEntrantes.get(cp).precio);
            Log.e("caaat dinsss ", ""+cp );
            comanda.add(p);
        }else if (cat == "PAELLAS"){
            p = new Producto(listaPaellas.get(cp).nombre,listaPaellas.get(cp).precio);
            comanda.add(p);
        }else if (cat == "FIDEUAS"){
            p = new Producto(listaFideuas.get(cp).nombre,listaFideuas.get(cp).precio);
            comanda.add(p);
        }else if (cat == "POSTRES"){
            p = new Producto(listaPostres.get(cp).nombre,listaPostres.get(cp).precio);
            comanda.add(p);
        }else{
            p = new Producto(listaVinos.get(cp).nombre,listaVinos.get(cp).precio);
            comanda.add(p);
        }
        Context context = getApplicationContext();
        CharSequence text = "Producto añadido";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void onClickCarrito(View view) {
        Intent intent = new Intent(this, Carrito.class);
        int q = comanda.size();

        intent.putExtra("quant", q);
       for (int i = 0; i < comanda.size();i++) {
           intent.putExtra("prod", comanda.get(i).getNombre());
       }
        for (int i = 0; i < comanda.size();i++) {
            intent.putExtra("preu", comanda.get(i).getPrecio());
        }

        startActivity(intent);
    }
    public void onClickMenu(View view) {
        Intent intent = new Intent(this, NuestroMenu.class);
        startActivity(intent);
    }

   private void cargarCarta() {
       listaEntrantes = new ArrayList<>();
       listaPaellas = new ArrayList<>();
       listaFideuas = new ArrayList<>();
       listaPostres = new ArrayList<>();
       listaVinos = new ArrayList<>();

       categor.add("ENTRANTES");
       Producto calamares = new Producto("Calamares a la romana" , 7.00, R.drawable.calamares);
       Producto pulpo = new Producto("Pulpo a la gallega " , 12.00, R.drawable.pulpo);
       Producto sardinas = new Producto("Sardinas " , 7.00 , R.drawable.sardinas);
       Producto gambas = new Producto("Gambas a la plancha" , 8.00, R.drawable.gambas);

       listaEntrantes.add(calamares);
       listaEntrantes.add(pulpo);
       listaEntrantes.add(sardinas);
       listaEntrantes.add(gambas);



       categor.add("PAELLAS");
       Producto valenciana = new Producto("Valenciana" , 8.00, R.drawable.valenciana);
       Producto mixta = new Producto("Mixta, pollo y pescado ", 8.00, R.drawable.mixta);
       Producto marisco = new Producto("Marisco", 10.00, R.drawable.marisco);
       Producto bogavante = new Producto("Bogavante ", 10.00, R.drawable.bogavante);
       Producto bacalao = new Producto("Bacalao y coliflor ", 10.00, R.drawable.bacalao);

       listaPaellas.add(valenciana);
       listaPaellas.add(mixta);
       listaPaellas.add(marisco);
       listaPaellas.add(bogavante);
       listaPaellas.add(bacalao);

       categor.add("FIDEUAS");
       Producto gandia = new Producto("De gandia ",8.00, R.drawable.gandia);
       Producto casa = new Producto("De la casa",9.00, R.drawable.casa);
       Producto pato = new Producto("Pato i foie ",10.00, R.drawable.pato);

       listaFideuas.add(gandia);
       listaFideuas.add(casa);
       listaFideuas.add(pato);

       categor.add("POSTRES");
       Producto flan = new Producto("Flan de la casa",4.00, R.drawable.flan);
       Producto tiramisu = new Producto("Tiramisu ",3.50, R.drawable.tiramisu);
       Producto queso = new Producto("Tarta de queso ",4.00, R.drawable.queso);
       Producto chocolate = new Producto("Tarta de chocolate",4.00, R.drawable.chocolate);
       Producto crepe = new Producto("Crepe de chocolate " ,4.50, R.drawable.crepe);

       listaPostres.add(flan);
       listaPostres.add(tiramisu);
       listaPostres.add(queso);
       listaPostres.add(chocolate);
       listaPostres.add(crepe);

       categor.add("VINOS");
       Producto cantoreal = new Producto("Canto real 'Blanco Verdejo'",6.00, R.drawable.cantoreal);
       Producto enhebro = new Producto("Enhebro 'Blanco Valencia' ",7.00, R.drawable.enhebro);
       Producto soldadito = new Producto("Soldadito Marinero 'Blanco Moscatel'",8.00, R.drawable.soldadito);
       Producto tribuna = new Producto("La Tribuna 'Tinto Valencia' ",7.00, R.drawable.tribuna);

       listaVinos.add(cantoreal);
       listaVinos.add(enhebro);
       listaVinos.add(soldadito);
       listaVinos.add(tribuna);

       subcategor.put(categor.get(0), listaEntrantes);
       subcategor.put(categor.get(1), listaPaellas);
       subcategor.put(categor.get(2), listaFideuas);
       subcategor.put(categor.get(3), listaPostres);
       subcategor.put(categor.get(4), listaVinos);


       adapta = new Adaptador(categor,subcategor,this);
       myListView.setAdapter(adapta);

    }

}