
package negocio;

import datos.*;
import dominio.Empleado;
import excepciones.AccesoDatosEx;
import excepciones.LecturaDatosEx;
import java.util.*;


public class CatalogoPeliculasImpl implements CatalogoEmpleados {

    private final AccesoDatos datos;
    
    public CatalogoPeliculasImpl(){
        this.datos = new AccesoDatosImpl(); //poner * en el import datos.*
    }
    
    @Override
    public void agregarPelicula(String nombrePelicula,double enero,double febrero,double marzo,String nombreArchivo) {
       Empleado pelicula = new Empleado(nombrePelicula, enero, febrero, marzo);
        boolean anexar = false;
        try {
            anexar = datos.existe(nombreArchivo);//bandera si anexa o no
            datos.escribir(pelicula, nombreArchivo, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace();
        }
    }

    @Override
    public void listarPeliculas(String nombreArchivo) {

        try {
            List<Empleado> peliculas = datos.listar(nombreArchivo);
            for(Empleado pelicula : peliculas){
                System.out.println("NOMBRE: "+pelicula.getNombre());
                System.out.println("Ventas enero: "+pelicula.getEnero());
                System.out.println("Ventas febrero: "+pelicula.getFebrero());
                System.out.println("Ventas marzo: " + pelicula.getMarzo());
                System.out.println("PROMEDIO : " + pelicula.calcPromedio());
                System.out.println("");
            }
        } catch (LecturaDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace();
        }
        
    }

    @Override
    public void buscarPeliculas(String nombreArchivo, String buscar) {
        String resultado = null;
        try {
           
            resultado = datos.buscar(nombreArchivo, buscar);
        } catch (LecturaDatosEx ex) {
            System.out.println("Error al buscar la peli");
            ex.printStackTrace();
        }
         System.out.println("Resultado Busqueda:"+resultado);
    
    }

    @Override
    public void iniciarArchivo(String nombreArchivo) {
        
        try {
            if(datos.existe(nombreArchivo)){
                datos.borrarArchivo(nombreArchivo);
                datos.crearArchivo(nombreArchivo);
            } else {
                //crearmos archivo
                datos.crearArchivo(nombreArchivo);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace();
        }

    }
    
}
