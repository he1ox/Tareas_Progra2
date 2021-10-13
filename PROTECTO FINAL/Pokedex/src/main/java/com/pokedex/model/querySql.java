/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.model;

import com.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author georg
 */
public class querySql extends Conexion {
    
    private Connection con = null;
    private Statement ps = null;
    private ResultSet rs = null;
    
    public ResultSet conRetorno(String query){
        
        con = getConexion();
        
        try {
            ps = con.createStatement();
            rs = ps.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
        return rs;
    }
    

}
