package com.example.aplicrestaurante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCarrito extends BaseAdapter {
    Context context;
    ArrayList<Producto> comanda = new ArrayList<>();

    public AdaptadorCarrito(Context context, ArrayList<Producto> comanda) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.comanda, null);
        Producto item = (Producto) getItem(position);

        TextView prod = (TextView) convertView.findViewById(R.id.productoPedido);
        prod.setText(item.nombre);
        TextView prodprec = (TextView) convertView.findViewById(R.id.productoPedidoPrecio);
        prodprec.setText(String.valueOf(item.precio));

        return convertView;
    }
}
