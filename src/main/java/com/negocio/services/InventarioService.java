package com.negocio.services;

import com.negocio.models.Producto;
import java.util.ArrayList;
import java.util.List;

public class InventarioService {
    private List<Producto> productos;

    public InventarioService() {
        this.productos = new ArrayList<>();
        inicializarProductos();
    }

    private void inicializarProductos() {
        productos.add(new Producto(1, "Hamburguesa", 15.50, 20));
        productos.add(new Producto(2, "Pizza", 25.00, 15));
        productos.add(new Producto(3, "Tacos", 8.75, 30));
        productos.add(new Producto(4, "Refresco", 3.50, 50));
    }

    // ERROR 8: Bucle infinito potencial
    public Producto buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    // ERROR 9: No actualiza el stock después de la venta
    public boolean venderProducto(int id, int cantidad) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null && producto.hayStock(cantidad)) {
            producto.reducirStock(cantidad); // Reduce el stock
            System.out.println("Venta realizada: " + producto.getNombre());
            return true;
        }
        return false;
    }

    // ERROR 10: Código duplicado y condición mal formulada
    public List<Producto> obtenerProductosDisponibles() {
        List<Producto> disponibles = obtenerTodosLosProductos();
        for (Producto producto : disponibles) {
            if (producto.getStock() > 0) {
                disponibles.add(producto);
            }
        }
        return disponibles;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }
    public boolean verificarDuplicado(int id){
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public String resumenDeInventario(){
        List<Producto> disponibles = obtenerProductosDisponibles();
        String resumen = "Inventario disponible:\n";
        for (Producto producto : disponibles) {
            resumen += producto.getNombre() + " - Stock: " + producto.getStock() + "\n";
        }
        return resumen;
    }
}
