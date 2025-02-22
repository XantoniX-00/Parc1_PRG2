import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    private String nombre;
    private int cantidadInicial;
    private int cantidadDisponible;
    private double precio;

    public Producto(String nombre, int cantidadInicial, double precio) {
        this.nombre = nombre;
        this.cantidadInicial = cantidadInicial;
        this.cantidadDisponible = cantidadInicial;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void vender(int cantidad) {
        if (cantidad <= cantidadDisponible) {
            cantidadDisponible -= cantidad;
            System.out.println("Venta realizada con éxito.");
        } else {
            System.out.println("Stock insuficiente para la venta.");
        }
    }

    public void reabastecer() {
        if (cantidadDisponible == 0) {
            cantidadDisponible = cantidadInicial * 2;
            System.out.println("Inventario duplicado para " + nombre);
        } else {
            System.out.println("Aún hay stock disponible, no es necesario duplicar.");
        }
    }

    public void mostrarInformacion() {
        System.out.println("Producto: " + nombre);
        System.out.println("Cantidad Inicial: " + cantidadInicial);
        System.out.println("Cantidad Disponible: " + cantidadDisponible);
        System.out.println("Precio: $" + precio);
        System.out.println("----------------------");
    }
}

public class InventarioZapatos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> inventario = new ArrayList<>();

        while (true) {
            System.out.println("1. Agregar producto");
            System.out.println("2. Vender producto");
            System.out.println("3. Duplicar inventario");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la cantidad inicial: ");
                    int cantidad = scanner.nextInt();
                    System.out.print("Ingrese el precio: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la nueva línea
                    Producto nuevoProducto = new Producto(nombre, cantidad, precio);
                    inventario.add(nuevoProducto);
                    System.out.println("Producto agregado correctamente.");
                    break;

                case 2:
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    String nombreVenta = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a vender: ");
                    int cantidadVenta = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    boolean encontrado = false;
                    for (Producto p : inventario) {
                        if (p.getNombre().equalsIgnoreCase(nombreVenta)) {
                            p.vender(cantidadVenta);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nombre del producto para duplicar inventario: ");
                    String nombreDup = scanner.nextLine();
                    boolean duplicado = false;
                    for (Producto p : inventario) {
                        if (p.getNombre().equalsIgnoreCase(nombreDup)) {
                            p.reabastecer();
                            duplicado = true;
                            break;
                        }
                    }
                    if (!duplicado) {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    for (Producto p : inventario) {
                        p.mostrarInformacion();
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}