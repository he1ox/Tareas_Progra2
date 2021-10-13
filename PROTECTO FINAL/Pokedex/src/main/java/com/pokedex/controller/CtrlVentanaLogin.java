/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.controller;

import com.pokedex.dao.PokemonDao;
import com.pokedex.dao.PokemonDaoImpl;
import com.pokedex.dao.UsuarioDao;
import com.pokedex.dao.UsuarioDaoImpl;
import com.pokedex.model.Pokemon;
import com.pokedex.model.Usuario;
import com.pokedex.view.VentanaLogin;
import com.pokedex.view.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author georg
 */
public class CtrlVentanaLogin implements ActionListener {

    public static VentanaLogin viewLogin;

    private final Usuario usuario;
    private final UsuarioDao usuarioDao;
    public CtrlVentanaPrincipal ctrlPrincipal;
    
    public CtrlVentanaLogin(VentanaLogin view, Usuario usuario, UsuarioDao usuarioDao) {
        CtrlVentanaLogin.viewLogin = view;

        this.usuario = usuario;
        this.usuarioDao = usuarioDao;

        viewLogin.btnIniciar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewLogin.btnIniciar) {
            String nombre = viewLogin.txtboxUsuario.getText();
            String password = String.valueOf(viewLogin.txtboxContraseña.getPassword());

            usuario.setNombre(nombre);
            usuario.setContraseña(password);

            if (usuarioDao.login(usuario)) {
                JOptionPane.showMessageDialog(null, "Datos correctos");
                limpiarEntradas();

                System.out.println("Usuario logeado : " + usuario.getNombre() + " " + usuario.getCorreo());

                var viewPrincipal = new VentanaPrincipal();
                PokemonDao pokemonDao = new PokemonDaoImpl();
                Pokemon pokemon = new Pokemon();
                
                ctrlPrincipal = new CtrlVentanaPrincipal(viewPrincipal, pokemonDao, pokemon, usuarioDao, usuario);
                
                
                CtrlVentanaPrincipal.view.setVisible(true);
                CtrlVentanaLogin.viewLogin.setVisible(false);
                
                
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Datos Incorrectos!!");
                limpiarEntradas();
            }

        }
    }

    public void limpiarEntradas() {
        viewLogin.txtboxContraseña.setText("");
        viewLogin.txtboxUsuario.setText("");
    }

}
