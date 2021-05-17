package com.example.aplicrestaurante;

import java.util.Objects;

public class Producto {
    String nombre;
    String foto;
    double precio;
    int unidades;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return nombre.equals(producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Producto(String nombre, double precio, int unidades, String foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.foto = foto;
    }
    public Producto(String nombre , double precio, String foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.foto = foto;
    }
    public Producto(String nombre , double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

}

