package com.PrimerParcial;
//Importamos todas las clases del paquete.

import com.ventas.*;
import com.comisiones.*;
import java.util.Scanner;

public class ClsPrincipal {

    static ClsComisiones tablaFactura;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("¿Cuantos productos vas a añadir?: ");
        int filas = teclado.nextInt();

        tablaFactura = new ClsComisiones(filas);

        for (int i = 0; i < filas; i++) {
            tablaFactura.agregarProductos(i);
        }

        tablaFactura.ingresoGastos();

        tablaFactura.prorratear();

        tablaFactura.imprimirDecorado();

    }

}
