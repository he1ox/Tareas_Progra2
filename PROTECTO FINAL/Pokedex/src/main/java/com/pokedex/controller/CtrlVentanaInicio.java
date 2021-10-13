/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.controller;

import com.pokedex.view.VentanaInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author georg
 */
public class CtrlVentanaInicio implements ActionListener{
    
    private final VentanaInicio viewInicio;
    
    public CtrlVentanaInicio(VentanaInicio view){
        this.viewInicio = view;
        
        
        this.viewInicio.btnLogin.addActionListener(this);
        this.viewInicio.btnRegistrarse.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewInicio.btnLogin) {
            CtrlVentanaLogin.viewLogin.setVisible(true);
        }
        else if (e.getSource() == viewInicio.btnRegistrarse) {
            CtrlVentanaRegistro.viewRegistro.setVisible(true);
        }
    }
}
