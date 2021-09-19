package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;


public class CtrlRegistro implements ActionListener {
    private SQLUsuario modSql;
    private Usuario mod;
    public static Registro frmRegistro;
    
    
    public CtrlRegistro(Registro frmRegistro,Usuario mod, SQLUsuario modSql){
        CtrlRegistro.frmRegistro = frmRegistro;
        this.mod = mod;
        this.modSql = modSql;
        
        this.frmRegistro.setTitle("Registrate en el sistema");
        this.frmRegistro.setLocationRelativeTo(null);
        
        //Determinando funciones de los botones
        this.frmRegistro.btnRegistrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frmRegistro.btnRegistrar){

        //Evaluamos las passwords
        //El cuadro de contraseña no devuelve un string
        String pass = new String(this.frmRegistro.txtPassword.getPassword());
        String passCon = new String(this.frmRegistro.txtConfirmarPassword.getPassword());

        if ("".equals(this.frmRegistro.txtNombre.getText()) || "".equals(this.frmRegistro.txtUsuario.getText()) || "".equals(pass) || "".equals(passCon) || "".equals(this.frmRegistro.txtCorreo.getText())) {
            JOptionPane.showMessageDialog(null, "Hay campos vacíos");
        } else {

            //Si las dos contraseñas coinciden
            if (pass.equals(passCon)) {

                if (modSql.existeUsuario(this.frmRegistro.txtUsuario.getText()) == 0) {
                    //Si el valor es 0 significa que el usuario no existe

                    if (modSql.esMailValido(this.frmRegistro.txtCorreo.getText())) {

                        //Ciframos la contraseña con sha1 desde el método estatico sha1
                        String passCifrado = Hash.sha1(pass);

                        //Enviamos datos al modelo
                        mod.setUsuario(this.frmRegistro.txtUsuario.getText());
                        mod.setPassword(passCifrado);
                        mod.setNombre(this.frmRegistro.txtNombre.getText());
                        mod.setCorreo(this.frmRegistro.txtCorreo.getText());
                        mod.setId_tipo(1);

                        //SQL
                        if (modSql.registrar(mod)) {
                            JOptionPane.showMessageDialog(null, "Usuario registrado. Revista tu correo electrónico.");
                            limpiarCasillas();
                            Email.enviarMail(mod.getCorreo(), mod.getNombre(),pass,mod.getUsuario());
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al registrar");
                            limpiarCasillas();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo no valido!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe.");
                    limpiarCasillas();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Has ingresado mal un registro...");
            }
        }
        }
    }
    
    
    public void limpiarCasillas(){
        this.frmRegistro.txtUsuario.setText(null);
        this.frmRegistro.txtPassword.setText(null);
        this.frmRegistro.txtConfirmarPassword.setText(null);
        this.frmRegistro.txtNombre.setText(null);
        this.frmRegistro.txtCorreo.setText(null);
    }
}
