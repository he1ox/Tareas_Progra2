/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author georg
 */
//Importamos todas las clases dentro del paquete util
import java.util.*;

public class HolaMundo {

    public static void main(String args[]) {
        String palabra = Ascii("codificar","Jorge",4);
        System.out.println(palabra);
       
        //Jorge = Nsvki, Clave 4;
        
        String palabra2 = Ascii("decodificar","Nsvki",4);
        System.out.println(palabra2);
        
        
        System.out.println("--FUERZA BRUTA---");
        
        String palabras = fuerzaBruta("Nsvki",0,10);
        System.out.println(palabras);
    }


    /**
    * Este método es capaz de codificar/decodificar String con el cifrado cesar.
    * @param opcion "codificar", "decodificar".
    * @param palabra String a operar con el algoritmo.
    * @param clave valor número que alterá el orden lógico del String.
    * @return String con encriptación/desencriptación.
    */
    
    public static String Ascii(String opcion, String palabra, int clave){
        String palabraDecodificada = "";
        char letraDecodificada;
        opcion = opcion.toLowerCase();
        
        for(int i = 0 ; i<palabra.length() ; i++){
            if(palabra.charAt(i) != ' '){
                letraDecodificada = (char)palabra.charAt(i);
                
                switch(opcion){
                    case "codificar":
                        letraDecodificada += clave;
                        break;
                    case "decodificar":
                        letraDecodificada -= clave;
                        break;
                    default:
                        letraDecodificada += clave;
                        break;
                }
                
                palabraDecodificada += letraDecodificada;
            } else {
                palabraDecodificada += " ";
            }
        }
        return palabraDecodificada;
    }
    
    
     /**
    * Fuerza bruta aplicada al cifrado cesar.
    * @param inicio clave con que se comenzará a operar
    * @param palabra String a operar con el algoritmo.
    * @param fin clave final a operar
    * @return String con encriptación/desencriptación.
    */
    public static String fuerzaBruta(String palabra, int inicio, int fin){
        
        String palabraDecodificada = "";
        char letraDecodificada;
        
        for(int j = inicio ; j<fin ; j++){
            
            for(int i = 0 ; i<palabra.length() ; i++){
                
                if(palabra.charAt(i) != ' '){
                    letraDecodificada = (char)palabra.charAt(i);
                    letraDecodificada -= j;
                    palabraDecodificada += letraDecodificada;
                } 
                else 
                {
                    palabraDecodificada += " ";
                }
                
            }
            palabraDecodificada += ", CLAVE: "+j+"\n";
        }
        
        return palabraDecodificada;
    }

}