package com.example.aplicrestaurante;

public class Producto {
    String nombre;
    int foto;
    double precio;
    int unidades;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
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

    public Producto(String nombre, double precio, int unidades, int foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.foto = foto;
    }
    public Producto(String nombre , double precio, int foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.foto = foto;
    }
    public Producto(String nombre , double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

}

