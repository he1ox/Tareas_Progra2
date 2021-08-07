
package com.ventas;

import java.util.Scanner;

public class ClsVendedores {

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the enero
     */
    public double getEnero() {
        return enero;
    }

    /**
     * @param enero the enero to set
     */
    public void setEnero(double enero) {
        if(enero<=100){
            this.enero = enero;
        }
    }

    /**
     * @return the febrero
     */
    public double getFebrero() {
        return febrero;
    }

    /**
     * @param febrero the febrero to set
     */
    public void setFebrero(double febrero) {
        this.febrero = febrero;
    }

    /**
     * @return the marzo
     */
    public double getMarzo() {
        return marzo;
    }

    /**
     * @param marzo the marzo to set
     */
    public void setMarzo(double marzo) {
        this.marzo = marzo;
    }
    //Atributos
    private String nombre;
    private double enero;
    private double febrero;
    private double marzo;
    
    //Constructor
    
    //Métodos
    public void llenaInformacion() {
        Scanner t = new Scanner(System.in);
        System.out.print("Ingrese nombre : ");
        this.setNombre(t.nextLine());
        
        System.out.print("Ingrese enero: ");
        this.setEnero((double) Double.valueOf(t.nextLine()));
        
        System.out.print("Ingrese febrero: ");
        this.setFebrero((double) Double.valueOf(t.nextLine()));
        
        System.out.print("Ingrese marzo: ");
        this.setMarzo((double) Double.valueOf(t.nextLine()));
    }
    
    
}
