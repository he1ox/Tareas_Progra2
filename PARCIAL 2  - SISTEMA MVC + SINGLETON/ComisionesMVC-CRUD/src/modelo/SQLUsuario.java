package modelo;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLUsuario extends Conexion{
    
    
    public boolean registrar(Usuario usr){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO usuarios (usuario,password,nombre,correo,id_tipo) VALUES (?,?,?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getCorreo());
            ps.setInt(5, usr.getId_tipo());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                //Cerramos la conexión!
                con.close();
            } catch (SQLException e){
                e.printStackTrace(System.out);
            }
        }
        
    }
    
    public int existeUsuario(String usuario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1,usuario);
            
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
            return 1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return 1;
        } finally {
            try {
                //Cerramos la conexión!
                con.close();
            } catch (SQLException e){
                e.printStackTrace(System.out);
            }
        }
        
    }
    
    
    public boolean esMailValido(String mail){
        //Regex validar mail
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(mail);
        
        
        return matcher.find();
    }
    
    
    public boolean login(Usuario usr){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT id,usuario, password, nombre, id_tipo FROM usuarios WHERE usuario = ?";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1,usr.getUsuario());
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                if (usr.getPassword().equals(rs.getString(3))) {
                    
                    String sqlUpdateDate = "UPDATE usuarios SET last_session = ? WHERE id =?";
                    
                    ps = con.prepareStatement(sqlUpdateDate);
                    ps.setString(1, usr.getLast_session());
                    ps.setInt(2, rs.getInt(1));
                    
                    ps.execute();
                    
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setId_tipo(5);
                    return true;
                } else {
                    return false;
                }
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            try {
                //Cerramos la conexión!
                con.close();
            } catch (SQLException e){
                e.printStackTrace(System.out);
            }
        }
    }
    
}
