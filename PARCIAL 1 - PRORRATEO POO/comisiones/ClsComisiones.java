package com.comisiones;

import com.ventas.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ClsComisiones {
    
    DecimalFormat formatearDecimal = new DecimalFormat("#.0");
    
    private String[][] comisiones;
    private Double[] pesoProductos;

    //Columnas determinadas
    private final int cantidad = 0;
    private final int descripcionProd = 1;
    private final int valorSegunFact = 2;
    private final int gastoValor = 3;
    private final int gastoPeso = 4;
    private final int costoUnitario = 5;
    private final int costoTotal = 6;

    //Gastos al valor
    private double seguro;
    private double impuesto;
    private double comisionBancaria;
    //Gastos al peso
    private double flete;
    private double acarreo;

    //Iterador
    private int filaActual = 0;

    //Límites de matriz
    private int MAX_FILAS;
    private final int MAX_COLUMNAS = 7;
    private int totalFilas = 0;

    //Constructor
    public ClsComisiones(int filas) {
        MAX_FILAS = filas;
        comisiones = new String[MAX_FILAS][MAX_COLUMNAS];
        pesoProductos = new Double[MAX_FILAS];
    }
    
    public void agregarProductos(int fila) {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Producto [" + (fila + 1) + "]");
        
        System.out.print("Cantidad de unidades: ");
        comisiones[fila][cantidad] = String.valueOf(teclado.nextInt());
        
        teclado.nextLine();
        
        System.out.print("Descripcion: ");
        comisiones[fila][descripcionProd] = teclado.nextLine();
        
        System.out.print("Valor c/u: ");
        comisiones[fila][valorSegunFact] = String.valueOf(teclado.nextDouble());
        
        System.out.print("Peso en Kg: ");
        pesoProductos[fila] = teclado.nextDouble();
        
    }

    //Métodos
    public boolean agregarProducto(ClsProducto oProducto) {
        
        if (filaActual >= MAX_FILAS) {
            return false;
        } else {
            comisiones[filaActual][cantidad] = String.valueOf(oProducto.getCantidad());
            comisiones[filaActual][descripcionProd] = oProducto.getDescripcion();
            comisiones[filaActual][valorSegunFact] = String.valueOf(oProducto.getPrecio());
            
            pesoProductos[filaActual] = oProducto.getPeso();
            
            filaActual++;
        }
        return true;
    }
    
    public void ingresoGastos() {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Ingrese los siguientes montos.");
        
        System.out.print("Seguro: ");
        seguro = teclado.nextDouble();
        
        System.out.print("Impuestos: ");
        impuesto = teclado.nextDouble();
        
        System.out.print("Comision Bancaria: ");
        comisionBancaria = teclado.nextDouble();
        
        System.out.print("Flete: ");
        flete = teclado.nextDouble();
        
        System.out.print("Acarreo: ");
        acarreo = teclado.nextDouble();        
    }
    
    private double primerPaso() {
        double valorTotal = 0.0;
        double valorPorCantidad = 0.0;
        int valor = 0;
        double cantidadProd = 0;

        /* 
        Se va a multiplicar el valor de cada unidad por el número de unidades.
         */
        for (int fila = 0; fila < MAX_FILAS; fila++) {
            valor = Integer.valueOf(comisiones[fila][cantidad]);
            cantidadProd = Double.valueOf(comisiones[fila][valorSegunFact]);
            
            valorPorCantidad = valor * cantidadProd;
            
            valorTotal += valorPorCantidad;
        }
        
        return valorTotal;
    }
    
    private double segundoPaso() {
        /*
        Se suman los gastos que generan en valor, para obtener
        el total del gasto al valor.
         */
        
        double gastos = (seguro + impuesto + comisionBancaria);
        
        return gastos;
    }
    
    private double tercerPaso() {
        /* 
        Establecemos el coeficiente del gasto en el cual se obtiene 
        dividiendo el total de gasto al valor, entre el valor total
        de los materiales, segun su precio en factura.
         */
        double totalGastosEnValor = segundoPaso();
        double totalGastosMateriales = primerPaso();
        
        double coeficiente = totalGastosEnValor / totalGastosMateriales;
        
        return coeficiente;
    }
    
    private void cuartoPaso() {
        /*
        En este paso se va a obtener los gastos al valor que genera cada producto.
        Se obtiene multiplicando el valor de cada cantidad por el coeficiente de 
        gastos al valor 
         */
        double coeficiente = tercerPaso();
        double valorProd = 0.0;
        double resultado = 0.0;
        
        for (int fila = 0; fila < MAX_FILAS; fila++) {
            valorProd = Double.valueOf(comisiones[fila][valorSegunFact]);
            
            resultado = valorProd * coeficiente;
            
            comisiones[fila][gastoValor] = formatearDecimal.format(resultado);;
        }
        
    }
    
    private double quintoPaso() {
        /* 
        Se va a establecer el peso total de la mercancia.
        Se obtiene multiplicando el peso de cada unidad 
         */
        
        double pesoTotal = 0.0;
        double peso;
        int cantidadProd;
        
        for (int fila = 0; fila < MAX_FILAS; fila++) {
            peso = pesoProductos[fila];
            cantidadProd = Integer.valueOf(comisiones[fila][cantidad]);
            
            pesoTotal += (peso * cantidadProd);
        }
        
        return pesoTotal;
    }
    
    private double sextoPaso() {
        /*
        Se suman los gastos relacionados al peso, como fletes y acarreo.
         */
        double totalGastosPeso = (flete + acarreo);
        
        return totalGastosPeso;
    }
    
    private double septimoPaso() {
        /*
        Establecemos el coeficiente del gasto al peso.
        Se obtiene diviendo el total de gastos, entre el peso total.
         */
        double pesoTotal = quintoPaso();
        double totalGastosPeso = sextoPaso();
        
        double coeficiente = (totalGastosPeso / pesoTotal);
        
        return coeficiente;
    }
    
    private void octavoPaso() {
        /*
        Se van a determinar los gastos al peso que generaron cada producto.
        Se obtiene multiplicando el peso de cada unidad por el coeficiente
        del gasto al valor.
         */
        double coeficiente = septimoPaso();
        double peso = 0.0;
        double resultado = 0.0;
        
        for (int fila = 0; fila < MAX_FILAS; fila++) {
            peso = pesoProductos[fila];
            
            resultado = peso * coeficiente;
            
            comisiones[fila][gastoPeso] = formatearDecimal.format(resultado);;
        }
        
    }
    
    private void calcCostos() {
        //Suma horizontal para el calculo de los costos unitarios
        double acumulador;
        
        for (int fila = 0; fila < MAX_FILAS; fila++) {
            acumulador = 0;
            for (int col = valorSegunFact; col <= gastoPeso; col++) {
                acumulador += Double.valueOf(comisiones[fila][col]);
            }
            
            comisiones[fila][costoUnitario] = String.valueOf(formatearDecimal.format(acumulador));
        }

        /*Se multiplica el costo unitario  por numero de unidades
        para obtener el costo total
         */
        double valorCosto = 0.0;
        double cantidadProd = 0.0;
        double resultado = 0.0;
        
        for (int fila = 0; fila < MAX_FILAS; fila++) {
            valorCosto = Double.valueOf(comisiones[fila][costoUnitario]);
            cantidadProd = Double.valueOf(comisiones[fila][cantidad]);
            
            resultado = valorCosto * cantidadProd;
            
            comisiones[fila][costoTotal] = String.valueOf(resultado);
            
        }
        
    }
    
    public double sumarFila(int columna) {
        double acumulador = 0.0;
        for (int fila = 0; fila < MAX_FILAS; fila++) {
            acumulador += Double.valueOf(comisiones[fila][columna]);
        }
        return acumulador;
    }
    
    public void prorratear() {
        cuartoPaso();
        octavoPaso();
        calcCostos();
    }
    
    public void imprimirDecorado() {
        
        System.out.println("\nUnidades\tDescripcion\tValor Fact.\tGasto Valor\tGasto Peso"
                + "\tCosto Unidad\tCosto Total\n");
        
        for (int x = 0; x < this.comisiones.length; x++) {
            System.out.print("|");
            for (int y = 0; y < this.comisiones[x].length; y++) {
                System.out.print(comisiones[x][y]);
                if (y != comisiones[x].length - 1) {
                    System.out.print("\t\t");
                }
            }
            System.out.println("");
        }
        
        try {
            double granTotal = sumarFila(costoTotal);
            System.out.println("\nGran Total : Q" + formatearDecimal.format(granTotal));
        } catch (Exception e) {
            System.out.println("Debes prorratear la factura primero.");
        }
        
    }
    
}
