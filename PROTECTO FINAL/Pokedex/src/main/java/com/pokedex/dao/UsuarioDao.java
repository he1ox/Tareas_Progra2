/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.dao;

import com.pokedex.model.Pokemon;
import com.pokedex.model.Usuario;

/**
 *
 * @author georg
 */
public interface UsuarioDao {
    public boolean registrar(Usuario user);
    public boolean login(Usuario user);
    public void agregarPokemon(Usuario user, Pokemon pokemonFavorito);
}
