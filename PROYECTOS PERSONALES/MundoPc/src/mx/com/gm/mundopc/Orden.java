
package mx.com.gm.mundopc;

public class Orden {
    private final int idOrden;
    private static int contadorOrdenes;
    private int contadorComputadoras;
    private Computadora[] computadoras;
    private static final int MAX_COMPUTADORAS = 10;
    
    public Orden(){
        this.idOrden = ++Orden.contadorOrdenes;
        computadoras = new Computadora[Orden.MAX_COMPUTADORAS];
    }
    
    
    public void agregarComputadora(Computadora computadora){
        if(this.contadorComputadoras < Orden.MAX_COMPUTADORAS){
            this.computadoras[this.contadorComputadoras++] = computadora;
        } else {
            System.out.println("Max. computadoras agregadas.");
        }
    }
    
    public void MostrarOrden(){
        System.out.println("No. orden: " + this.idOrden);
        System.out.println("Cant. Computadoras: " + this.contadorComputadoras);
        
        for(int i = 0 ; i<this.contadorComputadoras; i++){
            System.out.println(this.computadoras[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden{idOrden=").append(idOrden);
        sb.append(", contadorComputadoras=").append(contadorComputadoras);
        sb.append(", computadoras=").append(computadoras);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}

