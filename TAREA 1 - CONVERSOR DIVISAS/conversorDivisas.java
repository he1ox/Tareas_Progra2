/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progra;

import java.util.*;

/**
 *
 * @author georg
 */
public class Progra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Scanner & Variables
        Scanner entrada = new Scanner(System.in);
        String nombre;
        String[] divisas = new String[]{"dolares", "euros", "yuanes"};
        String divisaLocal;
        String divisaExtranjera;
        double cantidad;

        System.out.print("Ingresa tu nombre: ");
        nombre = entrada.nextLine();

        for (int i = 0; i < divisas.length; i++) {
            System.out.println(divisas[i]);
        }

        System.out.print(nombre + ", elige tu divisa local: ");
        divisaLocal = entrada.nextLine().toLowerCase();
        
        //Si la divisaLocal no existe en divisas[] arroja error.
        if(existeEnArreglo(divisas, divisaLocal)){
        } else {
            System.out.println("Divisa local no valida.");
            return;
        }
        
        //Despliegue de divisas en pantalla
        for (int i = 0; i < divisas.length; i++) {
            if (divisas[i].equals(divisaLocal)) {
                //No se muestra la divisa seleccionada
            } else {
                //Se imprimen en pantallas las demás divisas.
                System.out.println(divisas[i]);
            }
        }

        do{
            //Repetir mientras las divisas seleccionadas sean iguales.
            System.out.print("Convertir a: ");
            divisaExtranjera = entrada.nextLine().toLowerCase();
        } while (divisaExtranjera.equals(divisaLocal));
        
        
        if(existeEnArreglo(divisas, divisaExtranjera)){
        } else {
            System.out.println("Divisa extranjera no valida.");
            return;
        }

        System.out.print("Ingresa la cantidad: ");
        cantidad = Double.parseDouble(entrada.nextLine());

        System.out.println("Convirtiendo " + divisaLocal + " a " + divisaExtranjera);

        double resultado = realizarConversion(divisaLocal, divisaExtranjera, cantidad);
        
        String textoFormateado = String.format("%s %s equivale a %s %s",cantidad, divisaLocal, resultado, divisaExtranjera);
        System.out.println(textoFormateado);
    }
    
    public static double realizarConversion(String divisaLocal, String divisaExtranjera, double cantidad){
        double resultado = 0;
            
        switch (divisaLocal) {
            case "dolares":
                switch (divisaExtranjera) {
                    case "euros":
                        resultado = cantidad * 0.84;
                        break;
                    case "yuanes":
                        resultado = cantidad * 6.47;
                        break;
                }
                break;
            case "euros":
                switch (divisaExtranjera) {
                    case "dolares":
                        resultado = cantidad * 1.19;
                        break;
                    case "yuanes":
                        resultado = cantidad * 7.68;
                        break;
                }
                break;
            case "yuanes":
                switch (divisaExtranjera) {
                    case "dolares":
                        resultado = cantidad * 0.15;
                        break;
                    case "euros":
                        resultado = cantidad * 0.13;
                        break;
                }
                break;
        }
        return resultado;
    }
    
    public static boolean existeEnArreglo(String[] arreglo, String dato){
        boolean existe = false;
        for(int i = 0; i<arreglo.length ; i++){
            if(arreglo[i].equals(dato)){
                existe = true;
            }
        }
        return existe; 
    }

}
