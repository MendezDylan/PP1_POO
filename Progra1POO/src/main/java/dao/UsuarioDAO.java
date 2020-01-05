package dao;

import conexion.ConexionJavaMySQL;
import modelo.Estudiante;
import modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    conexion.ConexionJavaMySQL con = new ConexionJavaMySQL();
    Connection cc = con.getConexion();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean registrarUsuario(Usuario pUsuario) {
        try {
            CallableStatement insertarUsuario = cc.prepareCall("{call agregarUsuario(?,?,?,?,?)}");
            insertarUsuario.setInt(1, pUsuario.getCedula());
            insertarUsuario.setString(2, pUsuario.getNombreUsuario());
            insertarUsuario.setString(3, pUsuario.getContrasena());
            insertarUsuario.setString(4, pUsuario.getEstadoUsuario());
            insertarUsuario.setInt(5, pUsuario.getIdRol());
            insertarUsuario.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                cc.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean modificarUsuario(Estudiante pEstudiante) {
        try {
            CallableStatement modificarUsuario = cc.prepareCall("{call modificarUsuario(?)}");
            modificarUsuario.setInt(1, pEstudiante.getCedula());
            modificarUsuario.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                cc.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public Usuario iniciarSesion(Usuario pUsuario) {
        if (validarUsuario(pUsuario) == 0) {
            return pUsuario;
        } else {
            return null;
        }
    }

    private int validarUsuario(Usuario usuario) {
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();
        Connection cn = (Connection) mysql.getConexion();
        String sql = "SELECT count(nombreUsuario) FROM Usuario WHERE nombreUsuario = ? and contrasena = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.getConexion().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public int validarRol(String nombreU, String pass) {
        int idRol = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();

        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select idRol FROM Usuario where nombreUsuario = '" + nombreU + "' and contrasena = '" + pass + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    idRol = rs.getInt("idRol");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return idRol;
    }

    public String validacionEstado(String usuario) {
        String estadoUsu = "";
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();
        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select estadoUsuario FROM Usuario where nombreUsuario = '" + usuario + "'";
        try {

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(SQL);

            if (rs != null) {
                while (rs.next()) {
                    estadoUsu = rs.getString("estadoUsuario");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return estadoUsu;
    }

    public int obtenerCedula(String usuario) {
        int cedula = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();
        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select cedula FROM Usuario where nombreUsuario = '" + usuario + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    cedula = rs.getInt("cedula");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return cedula;
    }
}
