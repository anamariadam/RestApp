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
        Holder holder = null;

            holder = new Holder();

            // Els botons i la quantitat
            holder.setBotonmas((Button) convertView.findViewById(R.id.sumar));
            holder.setBotonmenos((Button) convertView.findViewById(R.id.restar));
            holder.setCantidad( convertView.findViewById(R.id.contador));

            convertView.setTag(holder);

        TextView prod = (TextView) convertView.findViewById(R.id.productoPedido);
        prod.setText(item.nombre);
        TextView d = convertView.findViewById(R.id.descripcion);
        d.setText(item.descripcion);
        TextView prodprec = (TextView) convertView.findViewById(R.id.productoPedidoPrecio);
        prodprec.setText(String.valueOf(String.format("%.2f",item.precio)));

        TextView tvQuantitat = holder.getCantidad();
        tvQuantitat.setText(String.valueOf(item.unidades));



        holder.getBotonmas().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int quantitat = Carrito.prodComanda.get(position).getUnidades();
                    quantitat++;

                    Carrito.prodComanda.get(position).setUnidades(quantitat);
                    tvQuantitat.setText(String.valueOf(Carrito.prodComanda.get(position).getUnidades()));
                Log.e("unitat ", "esta en mes: "+Carrito.prodComanda.get(position).getUnidades() );
                Carrito.totalDinero();
            }
        });
        holder.getBotonmenos().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int quantitat = Carrito.prodComanda.get(position).getUnidades();
                    if (quantitat > 0) quantitat--;

                    Carrito.prodComanda.get(position).setUnidades(quantitat);
                tvQuantitat.setText(String.valueOf(Carrito.prodComanda.get(position).getUnidades()));
                Log.e("unitat", "esta en menos: "+Carrito.prodComanda.get(position).getUnidades() );
                Carrito.totalDinero();
            }
        });

        return convertView;
    }
    static class Holder    {
        public Button botonmas, botonmenos;
        TextView cantidad;


        public Button getBotonmas() {
            return botonmas;
        }
        public void setBotonmas(Button botonmas) {
            this.botonmas = botonmas;
        }
        public Button getBotonmenos() {
            return botonmenos;
        }
        public void setBotonmenos(Button botonmenos) {
            this.botonmenos = botonmenos;
        }
        public TextView getCantidad() {
            return cantidad;
        }
        public void setCantidad(TextView cantidad) {
            this.cantidad = cantidad;
        }

    }
}
