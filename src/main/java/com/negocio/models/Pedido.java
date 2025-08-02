package com.negocio.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Map<Producto, Integer> productos;
    private LocalDateTime fecha;
    private double total;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.productos = new HashMap<>();
        this.fecha = LocalDateTime.now();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva.");
        }
        this.productos.put(producto, this.productos.getOrDefault(producto, 0) + cantidad);
        calcularTotal();
    }

    // ERROR 5: Cálculo incorrecto del total (suma precios sin considerar cantidades)
    private void calcularTotal() {
        total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
    }

    // ERROR 6: Método que puede causar IndexOutOfBoundsException
    public Producto obtenerPrimerProducto() {
        if (productos.isEmpty()) {
            return null; // Retorna null si la lista está vacía
        }
        return productos.keySet().iterator().next();
    }

    // ERROR 7: Descuento mal aplicado
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        double descuento = this.total * (porcentaje / 100);
        this.total -= descuento;
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Map<Producto, Integer> getProductos() { return productos; }
    public LocalDateTime getFecha() { return fecha; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                ", productos=" + productos.size() +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
