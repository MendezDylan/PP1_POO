package dao;

import conexion.ConexionJavaMySQL;
import modelo.Reserva;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ReservaDAO {

    ConexionJavaMySQL cn = new ConexionJavaMySQL();
    Connection cc = cn.getConexion();

    public boolean registrarReserva(Reserva pReserva) {
        try {
            CallableStatement agregarReserva = cc.prepareCall("{call agregarReserva(?,?,?,?,?,?,?)}");
            agregarReserva.setInt(1, pReserva.getIdReserva());
            agregarReserva.setString(2, pReserva.getAsunto());
            agregarReserva.setString(3, pReserva.getHoraInicio());
            agregarReserva.setString(4, pReserva.getHoraFin());
            agregarReserva.setInt(5, pReserva.getCapacidad());
            agregarReserva.setInt(6, pReserva.getCedula());
            agregarReserva.setString(7, pReserva.getIdSala());
            agregarReserva.execute();
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

    public String obtenerfechaValidacion(String fechaReserva) {
        String estado = "";
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();
        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select fechaHoraInicio from reserva \n"
                + "where '" + fechaReserva + "' <= now()";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    estado = rs.getString("fechaHoraInicio");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return estado;
    }

    public boolean cambiarEstadoReserva(int idReserva) {
        try {
            CallableStatement cambiarEstadoReserva = cc.prepareCall("{call cambiarEstadoReserva(?)}");
            cambiarEstadoReserva.setInt(1, idReserva);
            cambiarEstadoReserva.execute();
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
