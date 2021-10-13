/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.app;

import com.pokedex.controller.CtrlVentanaInicio;
import com.pokedex.controller.CtrlVentanaLogin;
import com.pokedex.controller.CtrlVentanaPrincipal;
import com.pokedex.controller.CtrlVentanaRegistro;
import com.pokedex.dao.PokemonDao;
import com.pokedex.dao.PokemonDaoImpl;
import com.pokedex.dao.UsuarioDao;
import com.pokedex.dao.UsuarioDaoImpl;
import com.pokedex.model.Pokemon;
import com.pokedex.model.Usuario;
import com.pokedex.view.VentanaInicio;
import com.pokedex.view.VentanaLogin;
import com.pokedex.view.VentanaPrincipal;
import com.pokedex.view.VentanaRegistro;

/**
 *
 * @author georg
 */
public class App {
    
    
    
    
    public static void main(String[] args) {
        
        PokemonDao pokemonDao = new PokemonDaoImpl();
        Pokemon pokemon = new Pokemon();
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        
        VentanaInicio vistaInicio = new VentanaInicio();
        VentanaLogin vistaLogin = new VentanaLogin();
        VentanaPrincipal vistaPrincipal = new VentanaPrincipal();
        VentanaRegistro vistaRegistro = new VentanaRegistro();
        
//        CtrlVentanaPrincipal ctrlVista = new CtrlVentanaPrincipal(vistaPrincipal, pokemonDao, pokemon, usuarioDao);
        CtrlVentanaInicio ctrlInicio = new CtrlVentanaInicio(vistaInicio);
        CtrlVentanaLogin ctrlLogin = new CtrlVentanaLogin(vistaLogin,usuario, usuarioDao);
        CtrlVentanaRegistro ctrlRegistro = new CtrlVentanaRegistro(vistaRegistro, usuario, usuarioDao);
        
        
        vistaInicio.setVisible(true);
    }
}
