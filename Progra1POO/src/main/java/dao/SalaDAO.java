package dao;

import conexion.ConexionJavaMySQL;
import modelo.Sala;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SalaDAO {

    ConexionJavaMySQL cn = new ConexionJavaMySQL();
    Connection cc = cn.getConexion();

    public boolean registrarSala(Sala pSala) {
        try {
            CallableStatement agregarSala = cc.prepareCall("{call agregarSala(?,?,?,?,?)}");
            agregarSala.setString(1, pSala.getIdSala());
            agregarSala.setString(2, pSala.getUbicacion());
            agregarSala.setInt(3, pSala.getCapacidadMaxima());
            agregarSala.setInt(4, pSala.getEstado());
            agregarSala.setInt(5, pSala.getIdHorario());
            agregarSala.execute();
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

    public boolean modificarEstadoSala(Sala pSala) {
        try {
            CallableStatement modificarEstado = cc.prepareCall("{call modificarEstadoSala(?,?)}");
            modificarEstado.setString(1, pSala.getIdSala());
            modificarEstado.setInt(2, pSala.getEstado());
            modificarEstado.execute();
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

    public boolean modificarUbicacionSala(Sala pSala) {
        try {
            CallableStatement modificarEstado = cc.prepareCall("{call modificarUbicacionSala(?,?)}");
            modificarEstado.setString(1, pSala.getIdSala());
            modificarEstado.setString(2, pSala.getUbicacion());
            modificarEstado.execute();
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

    public int obtenerEstadoSala(String pSala) {
        int estado = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();
        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select idEstado FROM Sala where identificador = '" + pSala + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    estado = rs.getInt("idEstado");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return estado;
    }
}
