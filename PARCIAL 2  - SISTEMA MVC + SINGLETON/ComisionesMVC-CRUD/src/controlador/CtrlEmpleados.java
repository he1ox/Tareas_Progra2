package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.crudEmpleados.Empleado;
import modelo.crudEmpleados.SQLEmpleado;
import vista.Home;


public class CtrlEmpleados implements ActionListener {

    private Empleado mod;
    private SQLEmpleado modSql;
    public static Home frmEmpleados;

    public CtrlEmpleados(Home frmEmpleados, Empleado mod, SQLEmpleado modSql) {
        CtrlEmpleados.frmEmpleados = frmEmpleados;
        this.mod = mod;
        this.modSql = modSql;

        //Añadimos titulo a la ventana
        CtrlEmpleados.frmEmpleados.setTitle("Administración Principal");
        CtrlEmpleados.frmEmpleados.setLocationRelativeTo(null);
        //Deshabilitamos el txt Id
        CtrlEmpleados.frmEmpleados.txtId.setEditable(false);
        //Definimos los eventos de los botones
        CtrlEmpleados.frmEmpleados.btnIngresar.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnEliminar.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnModificar.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnBuscar.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnAscendente.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnDescendente.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnRecargar.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnGenerarPdf.addActionListener(this);
        CtrlEmpleados.frmEmpleados.btnExcel.addActionListener(this);
        CtrlEmpleados.frmEmpleados.cbxFiltros.addActionListener(this);
        //Deshabilitamos la edición del jtable
        CtrlEmpleados.frmEmpleados.tbEmpleados.setDefaultEditor(Object.class, null);

        //Cargamos la tabla
        modSql.updateTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Botón Ingresar 
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnIngresar) {

            try {
                String codigo = CtrlEmpleados.frmEmpleados.txtCodigo.getText();
                String nombre = CtrlEmpleados.frmEmpleados.txtNombre.getText();
                Double enero = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtEnero.getText());
                Double febrero = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtFebrero.getText());
                Double marzo = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtMarzo.getText());

                //Le pasamos los datos al modelo de empleado
                mod.setCodigo(codigo);
                mod.setNombre(nombre);
                mod.setEnero(enero);
                mod.setFebrero(febrero);
                mod.setMarzo(marzo);

                //Consulta sql
                if (modSql.registrar(mod)) {
                    JOptionPane.showMessageDialog(null, "Se ha registrado con exito.");
                    limpiarTxt();
                    modSql.updateTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Falló al registrar empleado");
                    limpiarTxt();
                }
            } catch (HeadlessException | NumberFormatException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Revisa los campos.");
            }

        }

        //Botón eliminar
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnEliminar) {
            try {
                String codigo = CtrlEmpleados.frmEmpleados.txtCodigo.getText();
                String nombre = CtrlEmpleados.frmEmpleados.txtNombre.getText();
                Double enero = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtEnero.getText());
                Double febrero = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtFebrero.getText());
                Double marzo = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtMarzo.getText());

                //Le pasamos los datos al modelo de empleado
                mod.setCodigo(codigo);
                mod.setNombre(nombre);
                mod.setEnero(enero);
                mod.setFebrero(febrero);
                mod.setMarzo(marzo);

                //Consulta sql
                if (modSql.eliminar(mod)) {
                    JOptionPane.showMessageDialog(null, "Se ha eliminado con exito.");
                    limpiarTxt();
                    modSql.updateTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Falló al eliminar registro.");
                    limpiarTxt();
                }
            } catch (HeadlessException | NumberFormatException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Revisa los campos.");
            }
        }

        //Botón buscar
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnBuscar) {
            try {
                String codigo = CtrlEmpleados.frmEmpleados.txtCodigo.getText();

                //Le pasamos los datos al modelo de empleado
                mod.setCodigo(codigo);

                //Consulta sql
                if (modSql.buscar(mod)) {
                    CtrlEmpleados.frmEmpleados.txtId.setText(String.valueOf(mod.getId()));
                    CtrlEmpleados.frmEmpleados.txtEnero.setText(String.valueOf(mod.getEnero()));
                    CtrlEmpleados.frmEmpleados.txtFebrero.setText(String.valueOf(mod.getFebrero()));
                    CtrlEmpleados.frmEmpleados.txtMarzo.setText(String.valueOf(mod.getMarzo()));
                    CtrlEmpleados.frmEmpleados.txtNombre.setText(mod.getNombre());

                } else {
                    JOptionPane.showMessageDialog(null, "No se han encontrado incidencias con el código : " + codigo);
                    limpiarTxt();
                }
            } catch (HeadlessException | NumberFormatException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Revisa los campos.");
            }
        }

        //Boton modificar
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnModificar) {
            try {
                String codigo = CtrlEmpleados.frmEmpleados.txtCodigo.getText();
                String nombre = CtrlEmpleados.frmEmpleados.txtNombre.getText();
                Double enero = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtEnero.getText());
                Double febrero = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtFebrero.getText());
                Double marzo = Double.parseDouble(CtrlEmpleados.frmEmpleados.txtMarzo.getText());

                //Le pasamos los datos al modelo de empleado
                mod.setCodigo(codigo);
                mod.setNombre(nombre);
                mod.setEnero(enero);
                mod.setFebrero(febrero);
                mod.setMarzo(marzo);

                //Consulta sql
                if (modSql.modificar(mod)) {
                    JOptionPane.showMessageDialog(null, "Se ha modificado con exito.");
                    limpiarTxt();
                    modSql.updateTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Falló al eliminar registro");
                    limpiarTxt();
                }
            } catch (HeadlessException | NumberFormatException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Revisa los campos.");
            }
        }

        //Botón ascendente
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnAscendente) {
            try {
                modSql.updateTablaAscendente();
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }

        //Botón descendente
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnDescendente) {
            try {
                modSql.updateTablaDescendente();;
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }

        //Botón recargar
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnRecargar) {
            modSql.updateTabla();
        }
        
        //Botón PDF
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnGenerarPdf) {
            modSql.generarPDF();
        }
        
        if (e.getSource() == CtrlEmpleados.frmEmpleados.btnExcel) {
            modSql.generarExcel();
        }
        
        if (e.getSource() == CtrlEmpleados.frmEmpleados.cbxFiltros) {
            String ItemSeleccionado = (String )CtrlEmpleados.frmEmpleados.cbxFiltros.getSelectedItem();
            switch(ItemSeleccionado){
                case "Enero":
                    modSql.filtroEnero();
                    break;
                case "Febrero":
                    modSql.filtroFebrero();
                    break;
                case "Marzo":
                    modSql.filtroMarzo();
                    break;
                    
            }
        }

    }

    public void limpiarTxt() {
        CtrlEmpleados.frmEmpleados.txtCodigo.setText(null);
        CtrlEmpleados.frmEmpleados.txtEnero.setText(null);
        CtrlEmpleados.frmEmpleados.txtFebrero.setText(null);
        CtrlEmpleados.frmEmpleados.txtMarzo.setText(null);
        CtrlEmpleados.frmEmpleados.txtNombre.setText(null);
        CtrlEmpleados.frmEmpleados.txtId.setText(null);
    }

}
