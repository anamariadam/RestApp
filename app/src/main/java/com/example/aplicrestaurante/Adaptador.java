package com.example.aplicrestaurante;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;

public class Adaptador extends BaseExpandableListAdapter {

    private ArrayList<String> categorias;
    private Map<String, ArrayList<Producto>> subcategoria;
    private Context context;

    public Adaptador(ArrayList<String> categorias, Map<String, ArrayList<Producto>> subcategoria, Context context) {
        this.categorias = categorias;
        this.subcategoria = subcategoria;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return categorias.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return subcategoria.get(categorias.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return categorias.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return subcategoria.get(categorias.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String titulo = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.categoriasvista, null);
        TextView categoria = (TextView) convertView.findViewById(R.id.categoria);
        categoria.setText(titulo);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Producto item = (Producto) getChild(groupPosition,childPosition);

        convertView = LayoutInflater.from(context).inflate(R.layout.subcategoriavista, null);
        TextView subcategoria = (TextView) convertView.findViewById(R.id.nombres);
        subcategoria.setText(item.nombre);

        TextView preciio = (TextView) convertView.findViewById(R.id.precios);
        preciio.setText(String.valueOf(String.format("%.2f",item.precio)));
        ImageView img =(ImageView) convertView.findViewById(R.id.imagenes);
        Log.e("foto", "adaptador foto:  "+ item.getFoto());
        Glide.with(context).load(item.getFoto()).into(img);
        //img.setImageResource(item.foto);



        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }

}
