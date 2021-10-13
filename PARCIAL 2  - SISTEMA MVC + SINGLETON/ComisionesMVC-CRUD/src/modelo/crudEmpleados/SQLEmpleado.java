package modelo.crudEmpleados;

import controlador.CtrlEmpleados;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SQLEmpleado extends Conexion {

    private final String baseDeDatos = "db_comision";

    public boolean registrar(Empleado empleado) {
        Connection con = getConexion(baseDeDatos);

        PreparedStatement ps = null;

        String sql = "INSERT INTO tb_empleado(codigo,nombre,enero,febrero,marzo,promedio,total) VALUES (?,?,?,?,?,?,?)";

        try {
            //Le pasamos la sentencia sql al ps
            ps = con.prepareStatement(sql);

            //Asignamos los ? a su atributo correspondiente
            ps.setString(1, empleado.getCodigo());
            ps.setString(2, empleado.getNombre());
            ps.setDouble(3, empleado.getEnero());
            ps.setDouble(4, empleado.getFebrero());
            ps.setDouble(5, empleado.getMarzo());

            //COLUMNAS CALCULADAS (PROMEDIO Y TOTAL)
            ps.setDouble(6, empleado.getPromedio());
            ps.setDouble(7, empleado.getTotal());

            //Ejecutamos la sentencia
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public boolean modificar(Empleado empleado) {
        Connection con = getConexion(baseDeDatos);

        String sql = "UPDATE tb_empleado SET codigo = ?, nombre = ?, enero = ?, febrero = ?, marzo = ? WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getCodigo());
            ps.setString(2, empleado.getNombre());
            ps.setDouble(3, empleado.getEnero());
            ps.setDouble(4, empleado.getFebrero());
            ps.setDouble(5, empleado.getMarzo());
            ps.setDouble(6, empleado.getId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

    }

    public boolean eliminar(Empleado empleado) {
        Connection con = getConexion(baseDeDatos);

        PreparedStatement ps = null;

        String sql = "DELETE FROM tb_empleado WHERE codigo = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empleado.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

    }

    public boolean buscar(Empleado empleado) {
        Connection con = getConexion(baseDeDatos);
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM tb_empleado WHERE codigo = ?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getCodigo());

            rs = ps.executeQuery();

            if (rs.next()) {
                empleado.setId(Integer.parseInt(rs.getString("id")));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setEnero(Double.parseDouble(rs.getString("enero")));
                empleado.setFebrero(Double.parseDouble(rs.getString("febrero")));
                empleado.setMarzo(Double.parseDouble(rs.getString("marzo")));
                return true;
            }
            return false;
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

    }

    public void updateTabla() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            CtrlEmpleados.frmEmpleados.tbEmpleados.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = getConexion(baseDeDatos);

            String sql = "SELECT id, codigo, nombre, enero, febrero, marzo,promedio,total FROM tb_empleado";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantCol = rsMd.getColumnCount();

            modelo.addColumn("Id");
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Enero");
            modelo.addColumn("Febrero");
            modelo.addColumn("Marzo");
            modelo.addColumn("Promedio Ventas");
            modelo.addColumn("Total ventas");

            int[] anchos = {5, 10, 100, 15, 15, 15, 15, 40, 25};

            for (int i = 0; i < cantCol; i++) {
                CtrlEmpleados.frmEmpleados.tbEmpleados.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantCol];
                for (int i = 0; i < cantCol; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void updateTabla(String orden) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            CtrlEmpleados.frmEmpleados.tbEmpleados.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = getConexion(baseDeDatos);

            //Orden ascendente
            String sql = "SELECT id,codigo, nombre, enero, febrero, marzo, promedio, total FROM tb_empleado ORDER BY total "+orden;

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantCol = rsMd.getColumnCount();

            modelo.addColumn("Id");
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Enero");
            modelo.addColumn("Febrero");
            modelo.addColumn("Marzo");
            modelo.addColumn("Promedio Ventas");
            modelo.addColumn("Total ventas");

            int[] anchos = {5, 10, 100, 15, 15, 15, 15, 40, 25};

            for (int i = 0; i < cantCol; i++) {
                CtrlEmpleados.frmEmpleados.tbEmpleados.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantCol];
                for (int i = 0; i < cantCol; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }


    public void generarPDF() {
        Connection con = getConexion(baseDeDatos);

        try {
            JasperReport reporte = null;
            //Ruta del archivo jasper en reportes
            String path = "src\\reportes\\Empleados.jasper";

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

            //Lo hacemos visible
            view.setVisible(true);

        } catch (JRException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "No se ha generado el PDF por un error.");
        }

    }

    public void generarExcel() {

        //Creamos un workbook
        Workbook book = new XSSFWorkbook();

        //Creamos una sheet del book
        Sheet sheet = book.createSheet("Reporte de ventas");

        try {
            //Agregamos una imagen al sheet
            InputStream is = new FileInputStream("src\\Icons\\multi-report-1.png");

            try {
                //Traemos la imagen y la convertimos
                byte[] bytes = IOUtils.toByteArray(is);
                //Pasamos el tipo de formato de la imagen al método
                int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                //Cerramos el inputstream
                is.close();

                //La agregamos al archivo
                CreationHelper help = book.getCreationHelper();
                Drawing draw = sheet.createDrawingPatriarch();

                //Anchos del img
                ClientAnchor anchor = help.createClientAnchor();
                //Definimos en que columna vamos a colocar la img
                anchor.setCol1(0);
                //Definimos la fila
                anchor.setRow1(1);

                //Creamos la imagen
                Picture pict = draw.createPicture(anchor, imgIndex);

                //Le cambiamos el tamaño
                pict.resize(1, 3);

                //ESTILOS DEL TITULO
                CellStyle tituloEstilo = book.createCellStyle();
                tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
                tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
                //Fuentes
                org.apache.poi.ss.usermodel.Font fuenteTitulo = book.createFont();
                fuenteTitulo.setFontName("Consolas");
                fuenteTitulo.setBold(true);
                fuenteTitulo.setFontHeightInPoints((short) 14);

                tituloEstilo.setFont(fuenteTitulo);

                //Fila donde estará el titulo
                Row filaTitulo = sheet.createRow(1);
                Cell celdaTitulo = filaTitulo.createCell(1);
                celdaTitulo.setCellStyle(tituloEstilo);
                celdaTitulo.setCellValue("Reporte de Ventas por Empleado");

                //Celdas del encabezado
                String[] cabecera = new String[]{"ID", "Código","Nombre", "Enero", "Febrero", "Marzo", "Promedio", "Total"};

                //ESTILOS DEL ENCABEZADO
                CellStyle headerStyle = book.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);
                headerStyle.setBorderTop(BorderStyle.THIN);

                org.apache.poi.ss.usermodel.Font font = book.createFont();
                font.setFontName("Arial");
                font.setBold(true);
                font.setColor(IndexedColors.WHITE.getIndex());
                font.setFontHeightInPoints((short) 12);

                headerStyle.setFont(font);

                //Nuevas fila
                Row filaEncabezados = sheet.createRow(4);
                for (int i = 0; i < cabecera.length; i++) {
                    Cell celdaEncabezado = filaEncabezados.createCell(i);
                    celdaEncabezado.setCellStyle(headerStyle);
                    celdaEncabezado.setCellValue(cabecera[i]);
                }
                
                
                //Traemos la info de sql
                Connection con = getConexion(baseDeDatos);
                PreparedStatement ps = null;
                ResultSet rs = null;
                
                int numFilaDatos = 5;
                
                
                CellStyle datosEstilo = book.createCellStyle();
                datosEstilo.setBorderBottom(BorderStyle.THIN);
                datosEstilo.setBorderLeft(BorderStyle.THIN);
                datosEstilo.setBorderRight(BorderStyle.THIN);
                datosEstilo.setBorderTop(BorderStyle.THIN);
                
                try {
                    ps = con.prepareStatement("SELECT * FROM tb_empleado");
                    
                    rs = ps.executeQuery();
                    
                    int numCol = rs.getMetaData().getColumnCount();
                    
                    while (rs.next()) {                        
                        Row filaDatos = sheet.createRow(numFilaDatos);
                        
                        for (int a = 0; a < numCol; a++) {
                            Cell celdaDatos = filaDatos.createCell(a);
                            celdaDatos.setCellStyle(datosEstilo);
                            
                            celdaDatos.setCellValue(rs.getString(a+1));
                        }
                        numFilaDatos++;
                    }
                    
                    for (int i = 2; i < 8; i++) {
                        sheet.autoSizeColumn(i);
                    }
                    
                    sheet.setZoom(200);
                    
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace(System.out);
                    }
                }
                
                /*
                1. Fila donde comienza
                2. Fila donde termina
                1. Columna donde empieza
                5. Columna donde termina
                 */
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 5));

                //Empezamos a generar el reporte
                FileOutputStream fileOut = new FileOutputStream("ReporteVentas.xlsx");
                book.write(fileOut);

                fileOut.close();
                JOptionPane.showMessageDialog(null, "Reporte generado con éxito.");
                
                try {
                    //Si el archivo existe entonces lo abrimos automaticamente
                    File archivoExcel = new File("ReporteVentas.xlsx");
                    if (!Desktop.isDesktopSupported()) {
                        System.out.println("No soporta desktop lib");
                    } 
                    Desktop escritorio = Desktop.getDesktop();
                    if (archivoExcel.exists()) {
                        escritorio.open(archivoExcel);
                    }
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }
                

            } catch (IOException ex) {
                ex.printStackTrace(System.out);
                //JOptionPane abajo
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            //Poner un JOptionPane despues
        }

    }
    
    public void filtro(String mes){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            CtrlEmpleados.frmEmpleados.tbEmpleados.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = getConexion(baseDeDatos);

            //Orden ascendente
            String sql = "SELECT id,codigo, nombre, "+mes+" FROM tb_empleado ORDER BY "+mes+" DESC";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantCol = rsMd.getColumnCount();

            modelo.addColumn("Id");
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn(mes);
//            modelo.addColumn("Febrero");
//            modelo.addColumn("Marzo");
//            modelo.addColumn("Promedio Ventas");
//            modelo.addColumn("Total ventas");

            int[] anchos = {5, 10, 100, 15};

            for (int i = 0; i < cantCol; i++) {
                CtrlEmpleados.frmEmpleados.tbEmpleados.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantCol];
                for (int i = 0; i < cantCol; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
    
    
}
