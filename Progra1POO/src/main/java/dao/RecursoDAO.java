package dao;

import conexion.ConexionJavaMySQL;
import modelo.Recurso;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RecursoDAO {

    ConexionJavaMySQL cn = new ConexionJavaMySQL();
    Connection cc = cn.getConexion();

    public boolean registrarRecurso(Recurso pRecurso) {
        try {
            CallableStatement agregarRecurso = cc.prepareCall("{call agregarRecurso(?,?,?,?)}");
            agregarRecurso.setInt(1, pRecurso.getIdRecurso());
            agregarRecurso.setString(2, pRecurso.getDetalle());
            agregarRecurso.setInt(3, pRecurso.getCantidad());
            agregarRecurso.setString(4, pRecurso.getIdSala());
            agregarRecurso.execute();
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

    public boolean modificarRecurso(Recurso pRecurso) {
        try {
            CallableStatement modificarRecurso = cc.prepareCall("{call modificarRecurso(?,?)}");
            modificarRecurso.setInt(1, pRecurso.getCantidad());
            modificarRecurso.setInt(2, pRecurso.getIdRecurso());
            modificarRecurso.execute();
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

    public boolean eliminarRecurso(Recurso pRecurso) {
        try {
            CallableStatement eliminarRecurso = cc.prepareCall("{call eliminarRecurso(?)}");
            eliminarRecurso.setInt(1, pRecurso.getIdRecurso());
            eliminarRecurso.execute();
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
