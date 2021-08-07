package com.ventas;

public class ClsProducto {

    //Atributos
    private int cantidad;
    private String descripcion;
    private double precio;
    private double peso;

    //Constructores
    public ClsProducto() {
    }

    ;
    
    public ClsProducto(int cantidad, String descripcion, double precio, double peso) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.peso = peso;
    }

    //Getters & Setters
    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }
}
