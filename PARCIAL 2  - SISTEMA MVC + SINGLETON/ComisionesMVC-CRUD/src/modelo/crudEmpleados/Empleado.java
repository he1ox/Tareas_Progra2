package modelo.crudEmpleados;

public class Empleado {

    private int id;
    private String codigo;
    private String nombre;
    private Double enero;
    private Double febrero;
    private Double marzo;
    private Double promedio;
    private Double total;

    public Double getPromedio() {
        this.promedio = (enero + febrero + marzo) / 3;
        return promedio;

    }

    public Double getTotal() {
        this.total = enero + febrero + marzo;
        return total;
    }

    //Getters & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getEnero() {
        return enero;
    }

    public void setEnero(Double enero) {
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

}
