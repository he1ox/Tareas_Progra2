/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.dao;

import com.pokedex.model.Pokemon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author georg
 */
public interface PokemonDao {
    //Acceso a la data
    public Pokemon buscarPorId(int id);
}

