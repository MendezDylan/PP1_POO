package dao;

import conexion.ConexionJavaMySQL;
import modelo.Estudiante;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class EstudianteDAO {

    ConexionJavaMySQL cn = new ConexionJavaMySQL();
    Connection cc = cn.getConexion();

    public boolean registrarPersona(Estudiante pEstudiante) {
        try {
            CallableStatement insertarPersona = cc.prepareCall("{call agregarPersona(?,?,?,?,?)}");
            insertarPersona.setInt(1, pEstudiante.getCedula());
            insertarPersona.setString(2, pEstudiante.getPrimerNombre());
            insertarPersona.setString(3, pEstudiante.getPrimerApellido());
            insertarPersona.setString(4, pEstudiante.getCorreo());
            insertarPersona.setInt(5, pEstudiante.getTelefono());
            insertarPersona.execute();
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

    public boolean modificarEstudiante(Estudiante pEstudiante) {
        try {
            CallableStatement modificarEstudiante = cc.prepareCall("{call modificarEstudiante(?,?,?)}");
            modificarEstudiante.setInt(1, pEstudiante.getCedula());
            modificarEstudiante.setString(2, pEstudiante.getCarrera());
            modificarEstudiante.setInt(3, pEstudiante.getCarnet());
            modificarEstudiante.execute();
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

    public int obtenerCarnet(String pUsuario) {
        int carnet = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();

        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select p.carnet from persona p\n"
                + "inner join usuario u on p.cedula = u.cedula\n"
                + "where u.nombreUsuario = '"+pUsuario+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    carnet = rs.getInt("p.carnet");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return carnet;
    }
    
    public boolean restarCalificacion(int pCarnet) {
        try {
            CallableStatement restarCalificacion = cc.prepareCall("{call restarCalificacion(?)}");
            restarCalificacion.setInt(1, pCarnet);
            restarCalificacion.execute();
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
}
