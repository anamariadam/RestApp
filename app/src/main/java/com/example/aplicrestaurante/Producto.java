package com.example.aplicrestaurante;

public class Producto {
    String nombre;
    int imagen;
    double precio;
    int unidades;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
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

    public Producto(String nombre, double precio, int unidades, int imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.imagen = imagen;
    }
    public Producto(String nombre , double precio, int imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen= imagen;
    }
    public Producto(String nombre , double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

}

