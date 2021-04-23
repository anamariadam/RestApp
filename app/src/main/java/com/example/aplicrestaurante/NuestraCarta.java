package com.example.aplicrestaurante;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NuestraCarta extends MainMenu {
    private ExpandableListView myListView;
    private Adaptador adapta;
    private ArrayList<String> categor;
    private Map<String, ArrayList<Producto>> subcategor;

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
                return false;
            }
        });
    }

    public class Producto {
        String nombre;
        int imagen;

        public Producto(String nombre, int imagen) {
            this.nombre = nombre;
            this.imagen= imagen;
        }

        public int getImagen() {
            return imagen;
        }


    }

    public void onClick(View view) { // Your own broadcast
        Intent intent = new Intent(this, Carrito.class);
        startActivity(intent);
    }
    public void onClickMenu(View view) { // Your own broadcast
        Intent intent = new Intent(this, NuestroMenu.class);
        startActivity(intent);
    }
   private void cargarCarta() {
        ArrayList<Producto> listaEntrantes = new ArrayList<>();
       ArrayList<Producto> listaPaellas = new ArrayList<>();
       ArrayList<Producto> listaFideuas = new ArrayList<>();
       ArrayList<Producto> listaPostres = new ArrayList<>();
       ArrayList<Producto> listaVinos = new ArrayList<>();

       categor.add("ENTRANTES");
       Producto calamares = new Producto("Calamares a la romana \n 7'00€", R.drawable.calamares);
       Producto pulpo = new Producto("Pulpo a la gallega \n 12'00€", R.drawable.pulpo);
       Producto sardinas = new Producto("Sardinas \n 7'00€", R.drawable.sardinas);
       Producto gambas = new Producto("Gambas a la plancha \n 8'00€", R.drawable.gambas);

       listaEntrantes.add(calamares);
       listaEntrantes.add(pulpo);
       listaEntrantes.add(sardinas);
       listaEntrantes.add(gambas);



       categor.add("PAELLAS");
       Producto valenciana = new Producto("Valenciana \n 8'00€", R.drawable.valenciana);
       Producto mixta = new Producto("Mixta, pollo y pescado \n 8'00€", R.drawable.mixta);
       Producto marisco = new Producto("Marisco \n 10'00€", R.drawable.marisco);
       Producto bogavante = new Producto("Bogavante \n 10'00€", R.drawable.bogavante);
       Producto bacalao = new Producto("Bacalao y coliflor \n 10'00€", R.drawable.bacalao);

       listaPaellas.add(valenciana);
       listaPaellas.add(mixta);
       listaPaellas.add(marisco);
       listaPaellas.add(bogavante);
       listaPaellas.add(bacalao);

       categor.add("FIDEUAS");
       Producto gandia = new Producto("De gandia \n 8'00€", R.drawable.gandia);
       Producto casa = new Producto("De la casa \n 9'00€", R.drawable.casa);
       Producto pato = new Producto("Pato i foie \n 10'00€", R.drawable.pato);

       listaFideuas.add(gandia);
       listaFideuas.add(casa);
       listaFideuas.add(pato);

       categor.add("POSTRES");
       Producto flan = new Producto("Flan de la casa \n 4'00€", R.drawable.flan);
       Producto tiramisu = new Producto("Tiramisu \n 3'50€", R.drawable.tiramisu);
       Producto queso = new Producto("Tarta de queso \n 4'00€", R.drawable.queso);
       Producto chocolate = new Producto("Tarta de chocolate \n 4'00€", R.drawable.chocolate);
       Producto crepe = new Producto("Crepe de chocolate \n 4'50€", R.drawable.crepe);

       listaPostres.add(flan);
       listaPostres.add(tiramisu);
       listaPostres.add(queso);
       listaPostres.add(chocolate);
       listaPostres.add(crepe);

       categor.add("VINOS");
       Producto cantoreal = new Producto("Canto real 'Blanco Verdejo' \n 6'00€", R.drawable.cantoreal);
       Producto enhebro = new Producto("Enhebro 'Blanco Valencia' \n 7'00€", R.drawable.enhebro);
       Producto soldadito = new Producto("Soldadito Marinero 'Blanco Moscatel' \n 8'00€", R.drawable.soldadito);
       Producto tribuna = new Producto("La Tribuna 'Tinto Valencia' \n 7'00€", R.drawable.tribuna);

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