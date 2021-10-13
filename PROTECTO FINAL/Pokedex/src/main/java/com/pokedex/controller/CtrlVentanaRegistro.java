/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.controller;

import com.pokedex.dao.UsuarioDao;
import com.pokedex.model.Usuario;
import com.pokedex.view.VentanaRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author georg
 */
public class CtrlVentanaRegistro implements ActionListener {

    public static VentanaRegistro viewRegistro;
    private final Usuario usuario;
    private final UsuarioDao usuarioDao;
    
    
    public CtrlVentanaRegistro(VentanaRegistro ventana, Usuario usuario, UsuarioDao usuarioDao){
        CtrlVentanaRegistro.viewRegistro = ventana;
        this.usuario = usuario;
        this.usuarioDao = usuarioDao;
        
        viewRegistro.btnRegistrar.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewRegistro.btnRegistrar) {
            String userName = viewRegistro.txtboxNombreUsuario.getText();
            String password = viewRegistro.txtboxContraseña.getText();
            String correo = viewRegistro.txtboxContraseña.getText();
            
            if (userName.equals("") || password.equals("") || correo.equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son necesarios...");
            } else {
            
                usuario.setNombre(userName);
                usuario.setContraseña(password);
                usuario.setCorreo(correo);

                if (usuarioDao.registrar(usuario)) {
                    JOptionPane.showMessageDialog(null, "Registro con exito");
                    limpiarEntradas();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error");
                    limpiarEntradas();
                }
            
            }
            
        }
    }
    
    public void limpiarEntradas(){
        viewRegistro.txtboxContraseña.setText(null);
        viewRegistro.txtboxCorreo.setText(null);
        viewRegistro.txtboxNombreUsuario.setText(null);
    }
    
}
