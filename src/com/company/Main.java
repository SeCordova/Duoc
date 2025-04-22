package com.company;
import java.util.Scanner;

public class Main {
    static String nombreTeatro = "Teatro Moro";
    static int capacidadSala = 100;
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variables locales
        String nombreCliente = "";
        int cantidadEntradas = 0;
        String ubicacion1 = "";
        String ubicacion2 = "";
        int precioEntrada = 10000;
        int totalPago = 0;
        boolean reservada = false;
        boolean comprada = false;
        boolean salir = false;

        do {
            System.out.println("\n=== Bienvenidos al " + nombreTeatro + "! ===");
            System.out.println("1. Reservar entradas");
            System.out.println("2. Comprar entradas");
            System.out.println("3. Modificar entrada");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    if (totalEntradasVendidas >= capacidadSala) {
                        System.out.println("No quedan entradas disponibles");
                        break;
                    }

                    System.out.print("Ingrese su nombre: ");
                    nombreCliente = scanner.nextLine();

                    do{
                        System.out.print("Cuantas entradas desea reservar? (Maximo 2): ");
                        cantidadEntradas = scanner.nextInt();
                        scanner.nextLine();

                        if (cantidadEntradas < 1 || cantidadEntradas > 2) {
                            System.out.println("Cantidad invalida.");
                        }
                    } while(cantidadEntradas < 1 || cantidadEntradas > 2);

                    do {
                        System.out.print("Ubicacion 1 (ej: A1): ");
                        ubicacion1 = scanner.nextLine();
                        if (ubicacion1.isEmpty()) {
                            System.out.println("Ubicacion no puede estar vacia.");
                        }
                        //DEBUG 1- VERIFICAR QUE LA UBICACION NO ESTE VACIA Y SE ESTE GUARDANDO CORRECTAMENTE
                    } while (ubicacion1.isEmpty());

                    if (cantidadEntradas == 2) {
                        do {
                            System.out.print("Ubicacion 2 (ej: A2): ");
                            ubicacion2 = scanner.nextLine();

                            if (ubicacion2.isEmpty()) {
                                System.out.println("Ubicacion no puede estar vacia.");
                            } else if (ubicacion2.equals(ubicacion1)) {
                                System.out.println("Las ubicaciones deben ser diferentes.");
                            }
                            // DEBUG 2 - VALIDAR QUE LA UBICACION2 NO SEA IGUAL QUE LA UBICACION1 Y QUE NO ESTE VACIA
                        } while (ubicacion2.isEmpty() || ubicacion2.equals(ubicacion1));
                    } else {
                        ubicacion2 = "";
                    }

                    reservada = true;
                    comprada = false;

                    System.out.println("Entradas reservadas correctamente!");

                    break;

                case 2:
                    if (!reservada) {
                        //DEBUG 3 CONFIRMAR LA RESERVA
                        System.out.println("Primero debes realizar una reserva.");
                        break;
                    }

                    if (comprada) {
                        System.out.println("La reserva ya fue comprada.");
                        break;
                    }

                    totalPago = cantidadEntradas * precioEntrada;
                    comprada = true;
                    totalEntradasVendidas += cantidadEntradas;
                    totalIngresos += totalPago;

                    System.out.println("Compra de asientos realizada con exito por $" + totalPago);

                    // DEBUG 4 - CONFIRMAR QUE SE REALIZO LA COMPRA Y SE ACTUALIZARON LAS VARIABLES totalEntradasVendidas Y totalIngresos

                    break;

                case 3:
                    if (!reservada) {
                        System.out.println("No hay una reserva para modificar.");
                        break;
                    }

                    if (comprada) {
                        System.out.println("No se puede modificar una compra ya realizada.");
                        break;
                    }

                    System.out.print("Ingrese el nuevo nombre del cliente: ");
                    nombreCliente = scanner.nextLine();

                    System.out.print("¿Desea modificar la cantidad de entradas? (s/n): ");
                    String respuesta = scanner.nextLine();

                    if (respuesta.equalsIgnoreCase("s")) {
                        System.out.print("Cuántas entradas desea? (Máximo 2): ");
                        cantidadEntradas = scanner.nextInt();
                        scanner.nextLine();

                        if (cantidadEntradas < 1 || cantidadEntradas > 2) {
                            System.out.println("Cantidad invalida.");
                            break;
                        }

                        System.out.print("Nueva ubicacion 1: ");
                        ubicacion1 = scanner.nextLine();

                        if (cantidadEntradas == 2) {
                            System.out.print("Nueva ubicacion 2: ");
                            ubicacion2 = scanner.nextLine();
                        } else {
                            ubicacion2 = "";
                        }

                        System.out.println("Reserva modificada correctamente.");
                    } else {
                        System.out.println("No se modificaron las entradas.");
                    }
                    break;

                case 4:
                    if (!comprada) {
                        System.out.println("Primero debe comprar la entrada.");
                        break;
                    }

                    System.out.println("\n======= BOLETA =======");
                    System.out.println("Cliente: " + nombreCliente); // DEBUG 5 - VERIFICAR QUE EL NOMBRE DEL CLIENTE ESTE BIEN CAPTURADO
                    System.out.println("Entradas: " + cantidadEntradas); // DEBUG 6 - Verifica QUE LA CANTIDAD DE ENTRADAS SEA LA CORRECTA
                    System.out.println("Ubicacion 1: " + ubicacion1);
                    if (cantidadEntradas == 2) {
                        System.out.println("Ubicacion 2: " + ubicacion2);
                    }
                    System.out.println("Total pagado: $" + totalPago); // DEBUG 7 - VALIDAR QUE EL MONTO TOTAL EN LA BOLETA COINCIDA CON EL CALCULO
                    System.out.println("======================");

                    break;

                case 5:
                    salir = true;
                    System.out.println("Gracias por usar el sistema del " + nombreTeatro + "!");
                    break;

                default:
                    System.out.println("Opción invalida. Intente nuevamente.");
            }

        } while (!salir);

        scanner.close();
    }
}
