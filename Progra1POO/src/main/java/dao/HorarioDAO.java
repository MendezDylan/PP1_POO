package dao;

import conexion.ConexionJavaMySQL;
import modelo.Horario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class HorarioDAO {

    ConexionJavaMySQL cn = new ConexionJavaMySQL();
    Connection cc = cn.getConexion();

    public boolean registrarHorario(Horario pHorario) {
        try {
            CallableStatement agregarHorario = cc.prepareCall("{call agregarHorario(?,?,?,?)}");
            agregarHorario.setString(1, pHorario.getNombreHorario());
            agregarHorario.setString(2, pHorario.getDiasDisponible());
            agregarHorario.setString(3, pHorario.getHoraApertura());
            agregarHorario.setString(4, pHorario.getHoraCierre());
            agregarHorario.execute();
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
