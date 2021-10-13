/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.dao;

import com.conexion.Conexion;
import com.pokedex.model.Pokemon;
import com.pokedex.model.Usuario;
import com.pokedex.model.querySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author georg
 */
public class UsuarioDaoImpl extends Conexion implements UsuarioDao {
    private final querySql consulta = new querySql();
    
    @Override
    public boolean registrar(Usuario user){
        Connection con = getConexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO usuario(nombre,contraseña,correo) VALUES(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getContraseña());
            ps.setString(3, user.getCorreo());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        
    }

    @Override
    public boolean login(Usuario user) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT id,nombre,contraseña,correo FROM usuario WHERE nombre = ?";
        
        try {
            
            ps = con.prepareStatement(sql);
            
            ps.setString(1, user.getNombre());
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                if (user.getContraseña().equals(rs.getString(3))) {
                    
                    user.setId(rs.getInt(1));
                    user.setNombre(rs.getString(2));
                    user.setCorreo(rs.getString(4));
                    
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        
    }

    @Override
    public void agregarPokemon(Usuario usuario, Pokemon pokemonFavorito) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        int idusuario = usuario.getId();
        int idpokemon = pokemonFavorito.getId();
        
        String sql = "INSERT INTO usuario_pokemon(idusuario,idpokemon) VALUES(?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idusuario);
            ps.setInt(2, idpokemon);
            
            ps.execute();
            System.out.println("Se ha agregado el pokemon id = " + idpokemon + " al usuario id = " +idusuario);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
             
    }
}
