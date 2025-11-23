package Producto_Integrador;

import java.io.*;
import java.util.ArrayList;

public class Inventario {

    ArrayList<Producto> productos = new ArrayList<>();

    // ============================
    // Cargar archivo al iniciar
    // ============================
    public void cargarArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");

                int id = Integer.parseInt(datos[0].trim());
                String nombre = datos[1].trim();
                double precio = Double.parseDouble(datos[2].trim());
                int stock = Integer.parseInt(datos[3].trim());

                productos.add(new Producto(id, nombre, precio, stock));
            }

            System.out.println("Productos cargados desde archivo.");

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no existe, se creará al guardar.");
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }

    // ============================
    // Guardar archivo
    // ============================
    public void guardarArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("productos.txt"))) {

            for (Producto p : productos) {
                pw.println(p.getId() + "|" + p.getNombre() + "|" + p.getPrecio() + "|" + p.getStock());
            }

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // ============================
    // Operaciones del inventario
    // ============================

    public void agregarProducto(Producto p) {
        productos.add(p);
        guardarArchivo();
    }

    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("Inventario vacío.");
            return;
        }

        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public boolean modificarPrecio(String nombre, double nuevoPrecio) {
        Producto p = buscarPorNombre(nombre);
        if (p != null) {
            p.setPrecio(nuevoPrecio);
            guardarArchivo();
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(String nombre) {
        Producto p = buscarPorNombre(nombre);
        if (p != null) {
            productos.remove(p);
            guardarArchivo();
            return true;
        }
        return false;
    }

    public boolean venderProducto(String nombre, int cantidad) {
        Producto p = buscarPorNombre(nombre);
        if (p != null && p.getStock() >= cantidad) {
            p.setStock(p.getStock() - cantidad);
            guardarArchivo();
            return true;
        }
        return false;
    }
}
