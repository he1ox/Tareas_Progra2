/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.dao;

import com.pokedex.model.Columna;
import com.pokedex.model.Pokemon;
import com.pokedex.model.querySql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author georg
 */
public class PokemonDaoImpl implements PokemonDao {

    @Override
    public Pokemon buscarPorId(int id) {
        querySql Consulta = new querySql();
        ResultSet resultados = Consulta.conRetorno("SELECT * FROM pokemon WHERE id = " + id);
        Pokemon pokemon = new Pokemon();
        
        try {
            if (resultados.next()) {
                pokemon.setId(resultados.getInt(Columna.ID));
                pokemon.setName(resultados.getString(Columna.NAME));
                pokemon.setHeight((int) resultados.getDouble(Columna.HEIGHT));
                pokemon.setWeight((int) resultados.getDouble(Columna.WEIGHT));
                pokemon.setSpecies(resultados.getString(Columna.SPECIES));
            }
            return pokemon;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        
    }
    

}
