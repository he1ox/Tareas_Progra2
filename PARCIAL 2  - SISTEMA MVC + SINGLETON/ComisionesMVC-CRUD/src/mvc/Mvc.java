package mvc;

import controlador.*;
import modelo.*;
import modelo.crudEmpleados.Empleado;
import modelo.crudEmpleados.SQLEmpleado;
import vista.*;

public class Mvc {

    public static void main(String[] args) {
        SQLUsuario modSql = new SQLUsuario();
        Usuario mod = new Usuario();

        Empleado empMod = new Empleado();
        SQLEmpleado empModSql = new SQLEmpleado();

        Registro frmRegistro = new Registro();
        Inicio frmInicio = new Inicio();
        Login frmLogin = new Login();
        Home frmEmpleados = new Home();

        CtrlLogin ctrlLogin = new CtrlLogin(frmLogin, mod, modSql);
        CtrlRegistro ctrlRegistro = new CtrlRegistro(frmRegistro, mod, modSql);

        CtrlEmpleados ctrlEmpleados = new CtrlEmpleados(frmEmpleados, empMod, empModSql);

        CtrlInicio ctrlInicio = new CtrlInicio(frmInicio);

        ctrlInicio.iniciarPrograma();
        frmInicio.setVisible(true);
    }
}
