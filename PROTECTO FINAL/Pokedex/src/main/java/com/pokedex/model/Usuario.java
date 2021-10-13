/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.model;

/**
 *
 * @author georg
 */
public class Usuario {
    private int id;
    private String nombre;
    private String contraseña;
    private String correo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", contrase\u00f1a=").append(contraseña);
        sb.append(", correo=").append(correo);
        sb.append('}');
        return sb.toString();
    }
    
    
          
}
