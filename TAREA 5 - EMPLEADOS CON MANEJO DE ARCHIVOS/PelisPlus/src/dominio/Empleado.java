package dominio;

public class Empleado {

    private String nombre;
    private double enero;
    private double febrero;
    private double marzo;

    //Constructor por defecto
    public Empleado(String nombre, double enero, double febrero, double marzo) {
        this.nombre = nombre;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getEnero(){
        return this.enero;
    }
    
    public void setEnero(Double enero){
        this.enero = enero;
    }
    

    public Double getFebrero() {
        return febrero;
    }

    public void setFebrero(Double febrero) {
        this.febrero = febrero;
    }

    public Double getMarzo() {
        return marzo;
    }

    public void setMarzo(Double marzo) {
        this.marzo = marzo;
    }
    
    public Double calcPromedio(){
        double promedio = (enero+febrero+marzo)/3;
        return promedio;
    } 
    
    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre);
        sb.append(';');
        sb.append(this.enero);
        sb.append(';');
        sb.append(this.febrero);
        sb.append(';');
        sb.append(this.marzo);
        return sb.toString();
    }
}
