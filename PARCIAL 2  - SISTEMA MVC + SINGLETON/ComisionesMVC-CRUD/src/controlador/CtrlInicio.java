package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import vista.*;


public class CtrlInicio implements ActionListener {
    private final Inicio frmInicio;
    
    
    public CtrlInicio(Inicio vista){
        this.frmInicio = vista;

        
        //Determinamos las funciones de los botones en el Inicio
        
        /*
        1. Para botón de Registrar Usuario
        2. Para botón de Iniciar Sesión
        */
        
        this.frmInicio.btnIniciar.addActionListener(this);
        this.frmInicio.btnRegistrar.addActionListener(this);
    }
    
    public void iniciarPrograma(){
        this.frmInicio.setTitle("Administración de comisiones");
        this.frmInicio.setLocationRelativeTo(null);
        this.frmInicio.setResizable(false);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == this.frmInicio.btnRegistrar) {
            CtrlRegistro.frmRegistro.setVisible(true);
        }
        if(e.getSource() == this.frmInicio.btnIniciar){
           CtrlLogin.frmLogin.setVisible(true);
        }
    }
    
}
