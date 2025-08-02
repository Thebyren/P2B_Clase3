package com.negocio.services;

import com.negocio.db.DatabaseManager;
import com.negocio.models.Cliente;
import com.negocio.models.Pedido;
import com.negocio.models.Producto;
import com.negocio.models.DescuentoAplicable;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
public class PedidoService implements DescuentoAplicable {
    private List<Pedido> pedidos;
    private InventarioService inventarioService;
    private int contadorPedidos;
    private DatabaseManager connection;
    public PedidoService(InventarioService inventarioService) {
        this.pedidos = new ArrayList<>();
        this.inventarioService = inventarioService;
        this.contadorPedidos = 1;
        this.connection = new DatabaseManager();
    }

    // ERROR 11: Inicialización incorrecta de variables
    public Pedido crearPedido(Cliente cliente) {

        Pedido pedido = new Pedido(contadorPedidos, cliente);
        contadorPedidos++; // Incrementa el contador para el siguiente pedido
        pedidos.add(pedido);
        String query = DatabaseManager.writeQuery(pedido);
        try{
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query);
        }
        catch(Exception e){
            throw new RuntimeException("Error al guardar pedido: " + e.getMessage());
        }
        return pedido;
    }

    // ERROR 12: Condición mal formulada en bucle
    public boolean agregarProductoAPedido(int pedidoId, int productoId, int cantidad) {
        Pedido pedido = buscarPedidoPorId(pedidoId);
        if (pedido == null) {
            System.out.println("Error: Pedido no encontrado.");
            return false;
        }

        Producto producto = inventarioService.buscarProductoPorId(productoId);
        if (producto == null) {
            System.out.println("Error: Producto no encontrado.");
            return false;
        }

        // Vender el producto y actualizar el inventario
        if (inventarioService.venderProducto(productoId, cantidad)) {
            // Agregar el producto al pedido
            pedido.agregarProducto(producto, cantidad);
            return true;
        } else {
            System.out.println("Error: No hay suficiente stock para el producto: " + producto.getNombre());
            return false;
        }
    }

    public boolean aplicarDescuento(int pedidoId, double porcentaje) {
        Pedido pedido = buscarPedidoPorId(pedidoId);
        if (pedido != null) {
            pedido.aplicarDescuento(porcentaje);
            return true;
        }
        return false;
    }

    private Pedido buscarPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    public double calcularIngresosTotales() {
        double ingresos = 0;
        for (Pedido pedido : pedidos) {
            ingresos += pedido.getTotal();
        }
        return ingresos;
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidos;
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }
}
