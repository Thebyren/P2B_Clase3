package com.negocio.models;

// ERROR 1: Atributos públicos (Mala práctica de encapsulamiento)
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public void reducirStock(int cantidad) {
        if (cantidad <= 0) {
             throw new IllegalArgumentException("La cantidad a reducir debe ser positiva.");
        }
        if (hayStock(cantidad)) {
            this.stock -= cantidad;
        } else {
            throw new IllegalArgumentException("No hay suficiente stock para realizar la operación.");
        }
    }

    public boolean hayStock(int cantidad) {
        return this.stock >= cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
