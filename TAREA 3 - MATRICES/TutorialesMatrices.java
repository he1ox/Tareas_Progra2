/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorialesmatrices;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author georg
 */
public class TutorialesMatrices {

    public static String[][] comisiones = new String[3][6];
    public static Scanner sc = new Scanner(System.in);

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void llenarComisiones() {
        comisiones[0][0] = "Monica Hernandez";
        comisiones[0][1] = "20";
        comisiones[0][2] = "15";
        comisiones[0][3] = "35";

        comisiones[1][0] = "Silvia Jimenez";
        comisiones[1][1] = "1";
        comisiones[1][2] = "6";
        comisiones[1][3] = "4";

        comisiones[2][0] = "Maria Serrano";
        comisiones[2][1] = "10";
        comisiones[2][2] = "10";
        comisiones[2][3] = "10";

        System.out.println("llenarComisiones() ejecutado.");
    }

    public static void imprimirDecorado(String[][] array) {
        calcTotalMeses();
        System.out.println(Arrays.deepToString(array).replace("], ", "]\n"));
    }

    public static void calcTotalMes() {
        calcTotalMeses();
        int acumuladorMes;
        int acumuladorFila;
        int[] totalMes = new int[comisiones.length];

        for (int columna = 1; columna < comisiones[0].length - 1; columna++) {
            /*
            Se empieza desde la columna 1 para evitar el nombre.
            Se resta 1 a comisiones.length para evitar total.
             */
            acumuladorMes = 0;
            acumuladorFila = 0;

            for (int fila = 0; fila < comisiones.length; fila++) {

                acumuladorMes += Integer.parseInt(comisiones[fila][columna]);
            }

            System.out.println("Totales de la columna " + columna + " son " + acumuladorMes);
        }
    }

    public static void calcTotalMeses() {

        String nombrePersona = "";
        int acumuladorMes;
        int cantMeses;
        double promedio;

        for (int fila = 0; fila < comisiones.length; fila++) {
            cantMeses = 0;
            acumuladorMes = 0;
            promedio = 0;

            for (int columna = 1; columna < comisiones[fila].length - 2; columna++) {
                /*
                Se empieza desde la columna 1 para evitar el nombre.
                Se resta 2 a comisiones.length para evitar total y promedio.
                 */

                acumuladorMes += Integer.parseInt(comisiones[fila][columna]);
                cantMeses++;

                promedio = acumuladorMes / cantMeses;

            }

            comisiones[fila][comisiones[fila].length - 2] = String.valueOf(acumuladorMes);
            comisiones[fila][comisiones[fila].length - 1] = String.valueOf(promedio);

            //nombrePersona = comisiones[fila][0];
            //System.out.println("Total de " + nombrePersona + " es de : " + acumuladorMes);
        }

    }

    public static void maxVentasPorMes(String mes) {
        int valorFila;
        int colMes;

        //Cualquier valor en x fila reemplazará inmediatamente a maxVentas por valorFila.
        int maxVentas = 0;

        /* 
        9999 es un numero ideal para minVentas, ya que cualquier valor menor a este
        sera reemplazado inmediatamente por cualquier valor menor a el que haya en valorFila.
         */
        int minVentas = 9999;

        //Nombres de los vendedores con maximas y minimas ventas.
        String nombreMaxVentas = "";
        String nombreMinVentas = "";

        switch (mes.toLowerCase()) {
            case "enero":
                colMes = 1;
                break;

            case "febrero":
                colMes = 2;
                break;

            case "marzo":
                colMes = 3;
                break;

            default:
                System.out.println("Mes no valido");
                //Corta la ejecución del programa
                return;
        }

        for (int fila = 0; fila < comisiones.length; fila++) {
            valorFila = Integer.parseInt(comisiones[fila][colMes]);

            if (valorFila > maxVentas) {
                maxVentas = valorFila;
                nombreMaxVentas = comisiones[fila][0];
            } else if (valorFila < minVentas) {
                minVentas = valorFila;
                nombreMinVentas = comisiones[fila][0];
            }

        }

        System.out.println(nombreMinVentas + " tuvo las ventas minimas con " + minVentas + " venta(s) en " + mes);
        System.out.println(nombreMaxVentas + " tuvo las ventas maximas con " + maxVentas + " venta(s) en " + mes);

    }

    public static void maxVentasTotales() {
        int valorFila;
        int minVentasTotales = 9999;
        int maxVentasTotales = 0;
        String nombreMaxVentas = "";
        String nombreMinVentas = "";

        for (int fila = 0; fila < comisiones.length; fila++) {
            valorFila = Integer.parseInt(comisiones[fila][4]);

            if (valorFila > maxVentasTotales) {
                maxVentasTotales = valorFila;
                nombreMaxVentas = comisiones[fila][0];
            } else if (valorFila < minVentasTotales) {
                minVentasTotales = valorFila;
                nombreMinVentas = comisiones[fila][0];
            }
        }

        System.out.println("VENTAS MAXIMAS - " + nombreMaxVentas + " tuvo un total de Q." + maxVentasTotales + " ventas.");
        System.out.println("VENTAS MINIMAS - " + nombreMinVentas + " tuvo un total de Q." + minVentasTotales + " ventas.");
    }

    public static void modificarMatriz(int fila, int columna) {
        Scanner teclado = new Scanner(System.in);

        try {
            var valorEnMatriz = comisiones[fila][columna];

            System.out.println("Modificando... -> " + valorEnMatriz);
            System.out.print("Reemplazar por: ");

            var reemplazo = teclado.nextLine();
            comisiones[fila][columna] = reemplazo;

            System.out.println(valorEnMatriz + " -> " + reemplazo);

        } catch (Exception e) {
            System.out.println("Dato no valido o fuera de rango.");
        }

    }

    public static void llenarMatrizManual() {
        Scanner teclado = new Scanner(System.in);

        for (int fila = 0; fila < comisiones.length; fila++) {
            System.out.println("[Cliente " + (fila + 1) + "]");
            for (int columna = 0; columna <= 3; columna++) {

                switch (columna) {
                    case 0:
                        System.out.print("Ingresa el nombre: ");
                        comisiones[fila][columna] = teclado.nextLine();
                        break;

                    case 1:
                        System.out.print("Ingresa ventas enero: ");
                        comisiones[fila][columna] = teclado.nextLine();
                        break;

                    case 2:
                        System.out.print("Ingresa ventas febrero: ");
                        comisiones[fila][columna] = teclado.nextLine();
                        break;

                    case 3:
                        System.out.print("Ingresa ventas marzo: ");
                        comisiones[fila][columna] = teclado.nextLine();
                        break;
                }
            }
        }
        calcTotalMeses();
    }

    public static void busquedaMonto(String monto) {
        String nombre;
        int acum = 0;

        for (int fila = 0; fila < comisiones.length; fila++) {
            for (int columna = 1; columna <= 3; columna++) {
                if (comisiones[fila][columna].equals(monto)) {
                    nombre = comisiones[fila][0];
                    System.out.println("Coincidencia encontrada. " + nombre + " en el mes: " + columna);
                    acum++;
                }
            }
        }

        if (acum == 0) {
            System.out.println("No se ha encontrado.");
        }

    }

    public static void menu() {
        System.out.print("------- [MENU] - ELIGE UNA DE LAS OPCIONES -------\n"
                + "1. [Consulta] Personas que mas y menores ventas tuvieron por mes.\n"
                + "2. [Consulta] Persona que vendió más en los 3 meses\n"
                + "3. [Edición] Cambiar algún dato en la tabla.\n"
                + "4. [Consulta] Buscar venta por monto determinado\n"
                + "5. Mostrar matriz en pantalla.\n"
                + "6. Totales por mes.\n"
                + "------------------------------------------------\n"
                + "Ingresa una opcion según su correlativo: ");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        llenarMatrizManual();

        int opcion = 0;

        menu();
        opcion = sc.nextInt();

        do {
            switch (opcion) {
                case 1:

                    System.out.print("Ingresa el mes: ");
                    sc.nextLine();

                    var mes = sc.nextLine();

                    maxVentasPorMes(mes);

                    break;
                case 2:
                    maxVentasTotales();
                    break;
                case 3:
                    imprimirDecorado(comisiones);

                    System.out.print("Ingresa la fila: ");
                    int fila = sc.nextInt();
                    System.out.print("Ingresa la columna: ");
                    int columna = sc.nextInt();

                    modificarMatriz(fila, columna);

                    System.out.print("---- MATRIZ MODIFICADA ----\n");
                    imprimirDecorado(comisiones);

                    break;
                case 4:
                    System.out.print("Ingresa un monto a buscar: ");
                    sc.nextLine();

                    String monto = sc.nextLine();

                    busquedaMonto(monto);
                    break;
                case 5:
                    imprimirDecorado(comisiones);
                    break;
                case 6:
                    calcTotalMes();
                    break;
            }
            System.out.print("\nSelecciona una opcion: ");
            opcion = sc.nextInt();
        } while (opcion > 0 && opcion <= 6);

    }

}
