package mx.com.gm.mundopc;

public class Monitor {

    //Atributos
    private final int idMonitor;
    private static int contadorMonitores;
    private String marca;
    private Double tamano;

    //Constructor por defecto
    private Monitor() {
        this.idMonitor = ++Monitor.contadorMonitores;
    }

    //Sobrecarga
    public Monitor(String marca, double tamano) {
        this();
        this.marca = marca;
        this.tamano = tamano;
    }
    //Getters & Setters

    public int getIdMonitor() {
        return idMonitor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getTamano() {
        return tamano;
    }

    public void setTamano(Double tamano) {
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monitor{idMonitor=").append(idMonitor);
        sb.append(", marca=").append(marca);
        sb.append(", tamano=").append(tamano);
        sb.append('}');
        return sb.toString();
    }

    
}
