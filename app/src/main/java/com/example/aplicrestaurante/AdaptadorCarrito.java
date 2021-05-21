package com.example.aplicrestaurante;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
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
            holder.setButtonMes ((Button) convertView.findViewById(R.id.sumar));
            holder.setButtonMenys ((Button) convertView.findViewById(R.id.restar));
            holder.setTextViewQuantitat( convertView.findViewById(R.id.contador));

            convertView.setTag(holder);

        TextView prod = (TextView) convertView.findViewById(R.id.productoPedido);
        prod.setText(item.nombre);
        TextView prodprec = (TextView) convertView.findViewById(R.id.productoPedidoPrecio);
        prodprec.setText(String.valueOf(item.precio));

        TextView tvQuantitat = holder.getTextViewQuantitat();
        tvQuantitat.setText(String.valueOf(item.unidades));


        holder.getButtonMes().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int quantitat = Carrito.prodComanda.get(position).getUnidades();
                    quantitat++;

                    Carrito.prodComanda.get(position).setUnidades(quantitat);
                    tvQuantitat.setText(String.valueOf(Carrito.prodComanda.get(position).getUnidades()));
                Log.e("unitat ", "esta en mes: "+Carrito.prodComanda.get(position).getUnidades() );

            }
        });

        holder.getButtonMenys().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int quantitat = Carrito.prodComanda.get(position).getUnidades();
                    if (quantitat > 0) quantitat--;

                    Carrito.prodComanda.get(position).setUnidades(quantitat);
                tvQuantitat.setText(String.valueOf(Carrito.prodComanda.get(position).getUnidades()));
                Log.e("unitat", "esta en menos: "+Carrito.prodComanda.get(position).getUnidades() );

            }
        });

        return convertView;
    }
    static class Holder    {
        Button buttonMes, buttonMenys;
        TextView textViewQuantitat;


        public Button getButtonMes() {
            return buttonMes;
        }
        public void setButtonMes(Button buttonMes) {
            this.buttonMes = buttonMes;
        }
        public Button getButtonMenys() {
            return buttonMenys;
        }
        public void setButtonMenys(Button buttonMenys) {
            this.buttonMenys = buttonMenys;
        }
        public TextView getTextViewQuantitat() {
            return textViewQuantitat;
        }
        public void setTextViewQuantitat(TextView textViewQuantitat) {
            this.textViewQuantitat = textViewQuantitat;
        }

    }
}
