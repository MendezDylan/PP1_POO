package dao;

import conexion.ConexionJavaMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class LoginDAO {

    conexion.ConexionJavaMySQL con = new ConexionJavaMySQL();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int validarRol(String nombreU, String pass) {
        int idRol = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();

        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select idRol FROM Usuario where nombreUsuario = '" + nombreU + "' and pass = '" + pass + "'";
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

    public int validacionUsuario(String usuario) {
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();
        Connection cn = (Connection) mysql.getConexion();
        String sql = "SELECT count(nombreUsuario) FROM Usuario WHERE nombreUsuario = ?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
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

    public int validacionPass(String pass) {
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();
        Connection cn = (Connection) mysql.getConexion();
        String sql = "SELECT count(pass) FROM Usuario WHERE pass = ?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, pass);
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
                    estadoUsu = (String) rs.getObject("estadoUsuario");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return estadoUsu;
    }
}
