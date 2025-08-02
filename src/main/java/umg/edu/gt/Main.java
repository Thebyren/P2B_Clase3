package umg.edu.gt;

import com.negocio.models.Cliente;
import com.negocio.models.Pedido;
import com.negocio.models.Producto;
import com.negocio.services.InventarioService;
import com.negocio.services.PedidoService;
import com.negocio.db.DatabaseManager;

import java.util.Scanner;

public class Main {
    private final InventarioService inventarioService;
    private final PedidoService pedidoService;
    private final Scanner scanner;
    private int clienteIdCounter = 1;

    public Main() {
        this.inventarioService = new InventarioService();
        this.pedidoService = new PedidoService(inventarioService);
        this.scanner = new Scanner(System.in);
        // The client ID counter is initialized to 1.
    }

    public static void main(String[] args) {
        System.out.println("=== FOODNET - Simulador de Negocio de Comida Rápida ===");
        Main app = new Main();
        try {
            app.run();
        } finally {
            DatabaseManager.cerrarConexion();

            System.out.println("¡Gracias por usar FoodNet!");
        }
    }

    private void run() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    crearNuevoPedido();
                    break;
                case 3:
                    agregarProductoAPedido();
                    break;
                case 4:
                    mostrarPedidos();
                    break;
                case 5:
                    mostrarIngresos();
                    break;
                case 6:
                    aplicarDescuentoAPedido();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Ver inventario");
        System.out.println("2. Crear nuevo pedido");
        System.out.println("3. Agregar producto a pedido");
        System.out.println("4. Ver pedidos");
        System.out.println("5. Ver ingresos totales");
        System.out.println("6. Aplicar descuento a pedido");
        System.out.println("7. Salir");
    }

    private void mostrarInventario() {
        System.out.println("\n--- INVENTARIO ---");
        for (Producto producto : inventarioService.obtenerProductosDisponibles()) {
            System.out.println(producto);
        }
    }

    private void crearNuevoPedido() {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono del cliente: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(clienteIdCounter++, nombre, telefono);
        Pedido pedido = pedidoService.crearPedido(cliente);

        System.out.println("Pedido creado con ID: " + pedido.getId());
    }

    private void agregarProductoAPedido() {
        int pedidoId = leerEntero("ID del pedido: ");
        int productoId = leerEntero("ID del producto: ");
        int cantidad = leerEntero("Cantidad: ");

        if (pedidoService.agregarProductoAPedido(pedidoId, productoId, cantidad)) {
            System.out.println("Producto agregado exitosamente");
        } else {
            System.out.println("Error al agregar producto");
        }
    }

    private void mostrarPedidos() {
        System.out.println("\n--- PEDIDOS ---");
        pedidoService.mostrarPedidos();
    }

    private void mostrarIngresos() {
        double ingresos = pedidoService.calcularIngresosTotales();
        System.out.printf("Ingresos totales: Q%.2f%n", ingresos);
    }

    private void aplicarDescuentoAPedido() {
        int pedidoId = leerEntero("ID del pedido: ");
        double descuento = leerDouble("Porcentaje de descuento (ej. 10 para 10%): ");

        if (pedidoService.aplicarDescuento(pedidoId, descuento)) {
            System.out.println("Descuento aplicado exitosamente.");
        } else {
            System.out.println("Error: No se pudo aplicar el descuento. Verifique el ID del pedido.");
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            }
        }
    }
}
