package excepciones;

public class AccesoDatosEx extends Exception {
    String mensaje;
    
    public AccesoDatosEx(String msj){
        this.mensaje = msj;
    }
    
}
