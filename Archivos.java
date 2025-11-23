package Producto_Integrador;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Archivos {

    // Guarda el stock completo
    public static void guardarStock(ArrayList<Producto> productos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("stock.txt"))) {
            for (Producto p : productos) {
                pw.println(p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar stock: " + e.getMessage());
        }
    }

    // Cargar el stock al iniciar
    public static ArrayList<Producto> cargarStock() {
        ArrayList<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("stock.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                productos.add(new Producto(
                        Integer.parseInt(d[0]),
                        d[1],
                        Double.parseDouble(d[2]),
                        Integer.parseInt(d[3])
                ));
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar stock, iniciará vacío.");
        }
        return productos;
    }

    // Guardar ventas (solo historial general)
    public static void guardarVenta(String registro) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("ventas.txt", true))) {
            pw.println(registro);
        } catch (IOException e) {
            System.out.println("Error al guardar venta: " + e.getMessage());
        }
    }

    // Mostrar archivo texto
    public static void mostrarArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) System.out.println(linea);
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + archivo);
        }
    }

    // Generar ticket numerado
    public static void generarTicket(Producto p, int cantidad, double total) {
        try {
            int numero = obtenerNumeroTicket();
            String nombreTicket = "ticket_" + String.format("%03d", numero) + ".txt";

            PrintWriter pw = new PrintWriter(new FileWriter(nombreTicket));

            String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

            pw.println("======== FERRETERÍA LA ORIGINAL ========");
            pw.println("Fecha: " + fecha);
            pw.println("----------------------------------------");
            pw.println("Producto: " + p.getNombre());
            pw.println("ID: " + p.getId());
            pw.println("Cantidad: " + cantidad);
            pw.println("Precio unitario: $" + p.getPrecio());
            pw.println("----------------------------------------");
            pw.println("TOTAL: $" + total);
            pw.println("========================================");
            pw.println("Gracias por su compra :)");

            pw.close();

            actualizarContadorTicket(numero + 1);

            System.out.println("Ticket generado: " + nombreTicket);

        } catch (Exception e) {
            System.out.println("Error al generar ticket: " + e.getMessage());
        }
    }

    // Lee contador de tickets
    private static int obtenerNumeroTicket() {
        try (BufferedReader br = new BufferedReader(new FileReader("contador_ticket.txt"))) {
            return Integer.parseInt(br.readLine());
        } catch (Exception e) {
            return 1; // Si no existe empieza en 1
        }
    }

    // Actualiza el contador
    private static void actualizarContadorTicket(int n) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("contador_ticket.txt"))) {
            pw.println(n);
        } catch (Exception ignored) {}
    }
}
