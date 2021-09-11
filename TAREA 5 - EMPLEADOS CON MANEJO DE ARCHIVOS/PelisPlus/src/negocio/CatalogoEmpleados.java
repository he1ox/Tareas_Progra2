package negocio;

public interface CatalogoEmpleados {
    public void agregarPelicula(String nombre,double enero,double febrero,double marzo,String nombreArchivo);
    public void listarPeliculas(String nombreArchivo);
    public void buscarPeliculas(String nombreArchivo, String buscar);
    public void iniciarArchivo(String nombreArchivo);
}
