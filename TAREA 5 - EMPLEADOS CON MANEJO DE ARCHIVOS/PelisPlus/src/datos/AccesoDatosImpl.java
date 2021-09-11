package datos;

import dominio.Empleado;
import excepciones.AccesoDatosEx;
import excepciones.EscrituraDatosEx;
import excepciones.LecturaDatosEx;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImpl implements AccesoDatos{

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Empleado> listar(String nombreArchivo) throws LecturaDatosEx {
          List<Empleado> empleados = new ArrayList();
        try {
           BufferedReader entrada = null;
            File archivo = new File(nombreArchivo);

            entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null){
                String[] separado;
                separado = linea.split(";");
                String nombre = separado[0];
                Double enero = Double.parseDouble(separado[1]);
                Double febrero = Double.parseDouble(separado[2]);
                Double marzo = Double.parseDouble(separado[3]);
                Empleado empleado = new Empleado(nombre,enero,febrero,marzo);
                empleados.add(empleado);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return empleados;
    }


    @Override
    public void escribir(Empleado empleado, String nombreArchivo,boolean anexar) throws EscrituraDatosEx{
        PrintWriter salida = null;
        try {
            File archivo = new File(nombreArchivo);
            salida = new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(empleado.toString());
            salida.close();
            System.out.println("Se ha escrito el registro en el archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            salida.close();
        }
    }


    @Override
    public String buscar(String nombreArchivo,String buscar){
        BufferedReader entrada = null;
        String resultado = null;
        try {
            File archivo = new File(nombreArchivo);

            entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            int i=0;
            linea = entrada.readLine();
            while (linea != null){
                //IgnoreCase ignora si es mayuscula o minuscula
                String[] lineaSeparada;
                lineaSeparada = linea.split(";");
                String nombre = lineaSeparada[0];
                if(buscar != null && buscar.equalsIgnoreCase(nombre)){
                    resultado = "Empleado: "+ nombre + " encontrada en el indice " + i;
                    break;
                }
                linea = entrada.readLine();
                i++;
            } entrada.close();
        } catch (FileNotFoundException ex) {
             ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                entrada.close();
            } catch (IOException ex) {
                 ex.printStackTrace(System.out);
            }
        }
        return resultado;
    }

    @Override
    public void crearArchivo(String nombreArchivo){
        PrintWriter salida = null;
            try {
                File archivo = new File(nombreArchivo);
                salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
              } catch (IOException ex) {
                ex.printStackTrace(System.out);
            } finally {
                salida.close();
            }
    }

    @Override
    public void borrarArchivo(String nombreArchivo) throws AccesoDatosEx{
        File archivo = new File(nombreArchivo);
        archivo.delete();
        System.out.println("Se ha borrado el archivo");
    }
    
}
