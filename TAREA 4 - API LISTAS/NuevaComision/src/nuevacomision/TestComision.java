
package nuevacomision;

import clases.comisiones.Empleado;
import clases.comisiones.TablaComision;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TestComision {
    
    static List<Empleado> empleados;
    static TablaComision tabla;

    public static void main(String[] args) throws IOException {
        empleados = new ArrayList<Empleado>();
        tabla = new TablaComision(empleados);
        
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do{
            System.out.println("\n");
            mostrarOpciones();
            opcion = sc.nextInt();
            menu(opcion);
        }while(opcion != 7);
        
    }
    
    public static void prueba(){
        TablaComision tabla;
        Empleado empleado1 = new Empleado("Camila",5,6,3);
    
        empleados.add(empleado1);
        empleados.add(new Empleado("Jorge",10,5,3));
        
        tabla = new TablaComision(empleados);
        tabla.ventasEnero();
        tabla.ventasFebrero();
        tabla.ventasMarzo();
       
        Empleado Jorge = new Empleado();
        Jorge = tabla.obtenerIndice(1);
        
        System.out.println(Jorge.toString());
    }
    
    public static void mostrarOpciones(){
        System.out.println("1. Ingresar empleados.");
        System.out.println("2. Obtener mayores ventas en Enero");
        System.out.println("3. Obtener mayores ventas en Febrero");
        System.out.println("4. Obtener mayores ventas en Marzo");
        System.out.println("5. Mostrar Registro");
        System.out.println("6. Editar registro");
        System.out.println("7. Salir");
        
        System.out.println("---|Elige una opcion: ");
    }
    
    public static void menu(int opcion) throws IOException{
        switch(opcion){
            case 1:
                BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
                
                System.out.println("¿Cuantos empleados va a ingresar? : ");
                
                int cantidad = Integer.parseInt(sc.readLine());
                
                for(int i = 0 ; i<cantidad ; i++){
                    
                    int indice = i+1;
                    
                    System.out.println("["+indice+"] Ingrese el nombre: ");
                    String nombre = sc.readLine();
                    
                    System.out.println("["+indice+"] Ventas en Enero: ");
                    int cantEnero = Integer.parseInt(sc.readLine());
                    
                    System.out.println("["+indice+"] Ventas en Febrero: ");
                    int cantFebrero = Integer.parseInt(sc.readLine());
                    
                    System.out.println("["+indice+"] Ventas en Marzo: ");
                    int cantMarzo = Integer.parseInt(sc.readLine());
                    
                    empleados.add(new Empleado(nombre,cantEnero,cantFebrero,cantMarzo));
                }
                
                System.out.println("Se agregaron " +cantidad+ " empleados nuevos.");
                break;
            case 2:
                tabla.ventasEnero();
                break;
            case 3:
                tabla.ventasFebrero();
                break;
            case 4:
                tabla.ventasMarzo();
                break;
            case 5:
                empleados.forEach(empleado -> {
                    System.out.println("Nombre: " + empleado.getNombre());
                    System.out.println("Ventas Enero: Q." + empleado.getEnero());
                    System.out.println("Ventas Febrero: Q." + empleado.getFebrero());
                    System.out.println("Ventas Marzo: Q." + empleado.getMarzo());
                    System.out.println("|VENTAS TOTALES = " + empleado.totalMeses()+"|");
                    System.out.println("\n");
                });
                break;
            case 6:
                break;
            default:
                System.out.println("No valido!");
                break;
        }
    }
    
    
    
}
