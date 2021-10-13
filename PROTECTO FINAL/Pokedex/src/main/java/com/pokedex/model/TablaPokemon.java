/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.model;

import com.pokedex.controller.CtrlVentanaPrincipal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author georg
 */
public class TablaPokemon {
    private DefaultTableModel modelo;
    private JTable tabla;
    private querySql consulta;
    
    public TablaPokemon(JTable tabla){
        consulta = new querySql();
        this.tabla = tabla;
    }
    
    public void actualizar(){
        
        modelo = new DefaultTableModel();
        this.tabla.setModel(modelo);
        
        ResultSet resultados = consulta.conRetorno("SELECT id, name, height, weight, species, color, base_experience FROM pokemon");
        
        try {
            ResultSetMetaData rsMd = resultados.getMetaData();
            int cantCol = rsMd.getColumnCount();
            
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Tamaño");
            modelo.addColumn("Peso");
            modelo.addColumn("Categoría");
            modelo.addColumn("Color");
            modelo.addColumn("Puntos xp");
            
            while (resultados.next()) {                
                Object[] filas = new Object[cantCol];
                for (int i = 0; i < cantCol; i++) {
                    filas[i] = resultados.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    public void buscarPor(String parametro){
        
        modelo = new DefaultTableModel();
        this.tabla.setModel(modelo);
        
        ResultSet resultados = consulta.conRetorno("SELECT id, name, height, weight, species, color, base_experience FROM pokemon WHERE name LIKE '"+parametro+"%'");
        
        try {
            ResultSetMetaData rsMd = resultados.getMetaData();
            int cantCol = rsMd.getColumnCount();
            
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Tamaño");
            modelo.addColumn("Peso");
            modelo.addColumn("Categoría");
            modelo.addColumn("Color");
            modelo.addColumn("Puntos xp");
            
            while (resultados.next()) {                
                Object[] filas = new Object[cantCol];
                for (int i = 0; i < cantCol; i++) {
                    filas[i] = resultados.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void aplicarFiltro(String propiedad){
        modelo = new DefaultTableModel();
        this.tabla.setModel(modelo);
        
        ResultSet resultados = consulta.conRetorno("SELECT id, name, "+propiedad+" FROM pokemon");
        
        try {
            ResultSetMetaData rsMd = resultados.getMetaData();
            int cantCol = rsMd.getColumnCount();
            
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn(propiedad);
            
            while (resultados.next()) {                
                Object[] filas = new Object[cantCol];
                for (int i = 0; i < cantCol; i++) {
                    filas[i] = resultados.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
