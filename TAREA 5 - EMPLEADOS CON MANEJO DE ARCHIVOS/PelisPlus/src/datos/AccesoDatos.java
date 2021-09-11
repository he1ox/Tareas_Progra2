package datos;

import dominio.Empleado;
import excepciones.*;
import java.util.*;

public interface AccesoDatos {
    boolean existe(String nombre_archivo) throws AccesoDatosEx;
    public List<Empleado> listar(String nombreArchivo) throws LecturaDatosEx;
    void escribir(Empleado oPelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx;
    public void crearArchivo(String nombreArchivo) throws AccesoDatosEx;
    public void borrarArchivo(String nombreArchivo) throws AccesoDatosEx;
}
