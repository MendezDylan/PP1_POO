package dao;

import conexion.ConexionJavaMySQL;
import modelo.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class VariosDAO {

    conexion.ConexionJavaMySQL con = new ConexionJavaMySQL();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int validarCantidadParticipantes(Reserva pReserva) {
        int cantidad = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();

        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select count(pa.idParticipante) from participante pa\n"
                + "inner join reserva re on pa.idReserva = re.idReserva\n"
                + "where re.idReserva = " + pReserva.getIdReserva() + "";
        try {

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(SQL);

            if (rs != null) {
                while (rs.next()) {
                    cantidad = rs.getInt("count(pa.idParticipante)");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return cantidad;
    }

    public int validarCalificacion(String pUsuario) {
        int calificacion = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();

        Connection cn = (Connection) mysql.getConexion();
        String SQL = "select p.calificacion from persona p\n"
                + "inner join usuario u on p.cedula = u.cedula\n"
                + "where u.nombreUsuario = '" + pUsuario + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    calificacion = rs.getInt("calificacion");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return calificacion;
    }

    public int validarCantidadIncidentes(String pUsuario) {
        int cantIncidentes = 0;
        ConexionJavaMySQL mysql = new ConexionJavaMySQL();

        Connection cn = (Connection) mysql.getConexion();
        String SQL = "Select count(p.cedula) from persona p\n"
                + "inner join incidente i on p.cedula = i.cedula\n"
                + "inner join usuario u on u.cedula = p.cedula\n"
                + "where u.nombreUsuario = '"+pUsuario+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    cantIncidentes = rs.getInt("count(p.cedula)");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return cantIncidentes;
    }
}
