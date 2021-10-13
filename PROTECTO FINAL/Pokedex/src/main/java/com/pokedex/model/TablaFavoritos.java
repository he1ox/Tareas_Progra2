/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author georg
 */
public class TablaFavoritos {
    private DefaultTableModel modelo;
    private JTable tabla;
    private querySql consulta;
    private Usuario usuario;
    
    public TablaFavoritos(JTable tabla, Usuario usuario){
        consulta = new querySql();
        this.tabla = tabla;
        this.usuario = usuario;
    }
    
    public void actualizar(){
        modelo = new DefaultTableModel();
        this.tabla.setModel(modelo);
        
        int idUsuario = usuario.getId();
        
        ResultSet resultados = consulta.conRetorno("SELECT name FROM \n" +
"pokemon WHERE id in (SELECT idpokemon FROM usuario_pokemon WHERE idusuario = '"+idUsuario+"')");
        
        try {
            ResultSetMetaData rsMd = resultados.getMetaData();
            
            int cantCol = rsMd.getColumnCount();
            
            modelo.addColumn("Nombre");
            
            while (resultados.next()) {                
                Object[] filas = new Object[cantCol];
                for (int i = 0; i < cantCol; i++) {
                    filas[i] = resultados.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        
        
    }
}
