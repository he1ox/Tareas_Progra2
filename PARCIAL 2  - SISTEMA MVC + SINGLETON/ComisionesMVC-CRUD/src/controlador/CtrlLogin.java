/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 *
 * @author georg
 */
public class CtrlLogin implements ActionListener {
    private SQLUsuario modSql;
    private Usuario mod;
    public static Login frmLogin;
    
    public CtrlLogin(Login frmLogin, Usuario mod, SQLUsuario modSql){
        this.modSql = modSql;
        this.mod = mod;
        CtrlLogin.frmLogin = frmLogin;
        
        
        CtrlLogin.frmLogin.setTitle("Inicio de sesión");
        CtrlLogin.frmLogin.setLocationRelativeTo(null);
        
        //Definimos los eventos para los botones en el frmLogin
        frmLogin.btnIngresar.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
               //Fecha y hora de nuestro sistema
        Date date = new Date();
        //Para formar la fecha de arriba
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");
        
        String pass = new String(CtrlLogin.frmLogin.txtPassword.getPassword());
        
        if (!"".equals(CtrlLogin.frmLogin.txtUsuario.getText()) || !"".equals(CtrlLogin.frmLogin.txtPassword.getPassword())) {
            String nuevoPass = Hash.sha1(pass);
            
            mod.setUsuario(CtrlLogin.frmLogin.txtUsuario.getText());
            mod.setPassword(nuevoPass);
            mod.setLast_session(fechaHora.format(date));
            
            if (modSql.login(mod)) {
                JOptionPane.showMessageDialog(null, "Datos correctos.");
                
                /* 
                Declaramos el frmLogin como null otra vez 
                para que podamos crear otra instancia desde el frmInicio, de no ser así
                el sistema pensará que ya tenemos una venta frmLogin abierta, y no nos dejará
                abrir otra
                */
                
//                CtrlInicio.frmLogin = null;
                
                //Con esto cerramos la ventana UNICAMENTE
                CtrlLogin.frmLogin.dispose();
                
                CtrlEmpleados.frmEmpleados.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Usuarios o contraseña incorrectos.");
            }

        }
       
    }
    
}
