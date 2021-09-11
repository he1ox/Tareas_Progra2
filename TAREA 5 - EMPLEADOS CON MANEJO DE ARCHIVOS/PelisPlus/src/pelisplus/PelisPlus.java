package pelisplus;

import java.util.Scanner;
import negocio.*;


public class PelisPlus {

    private static final Scanner scanner = new Scanner(System.in);
    private static int opcion = -1;
    private static final String nombreArchivo = "E:\\temp\\peliculas.txt";
    private static final CatalogoEmpleados catalogoPeli = new CatalogoPeliculasImpl();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Mientras la opcion elegida sea 0, preguntamos al usuario
        while (opcion != 0) {
            try {
                System.out.println("Elige opcion:\n1.- Iniciar catalogo empleados"
                        + "\n2.- Agregar empleado\n"
                        + "3.- Listar empleado\n"
                        + "4.- Buscar empleado\n"
                        + "0.- Salir");

                opcion = Integer.parseInt(scanner.nextLine());

                //Ejemplo de switch case en Java
                switch (opcion) {
                    case 1:
                        //1. Creamos el objeto que administra el catalogo de personas
                        //La creacion del archivo es opcional, de todas maneras se creara
                        //al escribir por primera vez en el archivo
                        catalogoPeli.iniciarArchivo(nombreArchivo);
                        break;
                    case 2:
                        //2. agregar informacion archivo
                        System.out.println("Introduce el nombre de un empleado a agregar:");
                        String nombre = scanner.nextLine();
                        System.out.println("Ventas enero: ");
                        Double enero = scanner.nextDouble();
                        System.out.println("Ventas febrero: ");
                        Double febrero = scanner.nextDouble();
                        System.out.println("Ventas marzo: ");
                        Double marzo = scanner.nextDouble();
                        catalogoPeli.agregarPelicula(nombre,enero,febrero,marzo, nombreArchivo);
                        break;
                    case 3:
                        //3. listar catalogo completo
                        catalogoPeli.listarPeliculas(nombreArchivo);
                        break;
                    case 4:
                        //4. Buscar pelicula
                        System.out.println("Introduce el nombre de un empleado a buscar:");
                        String buscar = scanner.nextLine();
                        catalogoPeli.buscarPeliculas(nombreArchivo, buscar);
                        break;
                    case 0:
                        System.out.println("!Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opcion no reconocida");
                        break;
                }
                System.out.println("\n");

            } catch (Exception e) {
            }
        }

    }
}