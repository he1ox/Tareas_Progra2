package clases.comisiones;

public class Empleado implements Comparable<Empleado> {

    private String nombre;
    private Double enero;
    private Double febrero;
    private Double marzo;

    public Empleado() {
    }

    ;
    
    public Empleado(String nombre, double enero, double febrero, double marzo) {
        this.nombre = nombre;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
    }

    //Suma de todos los meses
    public double totalMeses() {
        return (this.enero + this.febrero + this.marzo);
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEnero() {
        return enero;
    }

    public void setEnero(double enero) {
        this.enero = enero;
    }

    public double getFebrero() {
        return febrero;
    }

    public void setFebrero(double febrero) {
        this.febrero = febrero;
    }

    public double getMarzo() {
        return marzo;
    }

    public void setMarzo(double marzo) {
        this.marzo = marzo;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{nombre=").append(nombre);
        sb.append(", enero=").append(enero);
        sb.append(", febrero=").append(febrero);
        sb.append(", marzo=").append(marzo);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Empleado emp) {
        return this.enero.compareTo(emp.getEnero());
    }

}
