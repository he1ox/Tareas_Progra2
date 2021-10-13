/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.model;

import com.conexion.Conexion;
import java.sql.Connection;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author georg
 */
public class ReportesPdf extends Conexion {
    public void generar(){
        Connection con = getConexion();
        try {
            JasperReport reporte = null;
            
            //Ruta del archivo jasper en reportes
            String path = "C:\\Users\\georg\\Documents\\Patrones-De-Diseño\\Pokedex\\src\\main\\java\\reportes\\reporte.jasper";
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            /* 
            3 parametros 
            reporte -> Object JasperReport
            null -> no necesitamos enviar parametros al reporte
            con -> conexión a la base de datos.
             */
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            
            //Creamos la vista del reporte
            JasperViewer view = new JasperViewer(jprint, false);
            
            //Cierre para el reporte, y no se quede la tarea corriendo.
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException e) {
            e.printStackTrace(System.out);
        }
    }
}
