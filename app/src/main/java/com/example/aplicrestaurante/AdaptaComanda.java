package com.example.aplicrestaurante;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptaComanda extends BaseAdapter {
    Context context;
    ArrayList<Producto> comanda = new ArrayList<>();


    public AdaptaComanda(Context context, ArrayList<Producto> comanda) {
        this.context = context;
        this.comanda = comanda;
    }

    @Override
    public int getCount() {
        return comanda.size();
    }

    @Override
    public Object getItem(int position) {

        return comanda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_comandafinal, null);
        Producto item = (Producto) getItem(position);


        TextView prod = (TextView) convertView.findViewById(R.id.prodfin);
        prod.setText(item.nombre);
        TextView prodprec = (TextView) convertView.findViewById(R.id.preciouni);
        prodprec.setText(String.valueOf(String.format("%.2f",item.precio)));
        Double tt = item.getPrecio() * item.getUnidades();
        TextView quant = (TextView) convertView.findViewById(R.id.totaluni);
        prodprec.setText(String.valueOf(String.format("%.2f",tt)));
        quant.setText(String.valueOf(item.unidades));

        return convertView;
    }

}
