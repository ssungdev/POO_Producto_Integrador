package Producto_Integrador;

import java.util.Scanner;

public class Ferreteria {
    public static void main(String[] args) {

        Inventario inv = new Inventario();
        inv.cargarArchivo(); // Cargar desde productos.txt

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Mostrar inventario");
            System.out.println("2. Buscar producto");
            System.out.println("3. Modificar precio");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Vender producto");
            System.out.println("6. Agregar producto");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    inv.mostrarInventario();
                    break;

                case 2:
                    System.out.print("Nombre del producto: ");
                    String nombreBusca = sc.nextLine();
                    Producto encontrado = inv.buscarPorNombre(nombreBusca);

                    if (encontrado != null)
                        System.out.println("Encontrado: " + encontrado);
                    else
                        System.out.println("No existe.");
                    break;

                case 3:
                    System.out.print("Nombre del producto: ");
                    String nombreModificar = sc.nextLine();

                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = sc.nextDouble();

                    if (inv.modificarPrecio(nombreModificar, nuevoPrecio))
                        System.out.println("Precio actualizado.");
                    else
                        System.out.println("Producto no encontrado.");
                    break;

                case 4:
                    System.out.print("Nombre del producto: ");
                    String nombreEliminar = sc.nextLine();

                    if (inv.eliminarProducto(nombreEliminar))
                        System.out.println("Producto eliminado.");
                    else
                        System.out.println("No existe.");
                    break;

                case 5:
                    System.out.println("Vender por:");
                    System.out.println("1. Nombre");
                    System.out.println("2. ID");
                    System.out.print("Opción: ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    if (tipo == 1) {
                        System.out.print("Producto a vender: ");
                        String nombreVenta = sc.nextLine();

                        System.out.print("Cantidad: ");
                        int cant = sc.nextInt();

                        System.out.print("Monto con el que paga: ");
                        double pago = sc.nextDouble();
                        sc.nextLine();

                        if (inv.venderProducto(nombreVenta, cant, pago))
                            System.out.println("Venta realizada, ticket generado.");
                        else
                            System.out.println("Error: no existe, stock insuficiente o pago insuficiente.");
                        break;
                    } else if (tipo == 2) {
                        System.out.print("ID del producto: ");
                        int idVenta = sc.nextInt();

                        System.out.print("Cantidad: ");
                        int cant = sc.nextInt();

                        System.out.print("Monto con el que paga: ");
                        double pago = sc.nextDouble();
                        sc.nextLine();

                        if (inv.venderProductoPorId(idVenta, cant, pago))
                            System.out.println("Venta realizada, ticket generado.");
                        else
                            System.out.println("Error: no existe, stock insuficiente o pago insuficiente.");
                        break;
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;

                case 6:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();

                    System.out.print("Precio: ");
                    double prec = sc.nextDouble();

                    System.out.print("Stock: ");
                    int sto = sc.nextInt();

                    inv.agregarProducto(new Producto(id, nom, prec, sto));
                    System.out.println("Producto agregado.");
                    break;

                case 7:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 7);

        sc.close();
    }
}
