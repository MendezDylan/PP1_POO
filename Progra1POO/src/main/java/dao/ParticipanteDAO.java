package dao;

import conexion.ConexionJavaMySQL;
import modelo.Participante;
import modelo.Sala;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ParticipanteDAO {

    ConexionJavaMySQL cn = new ConexionJavaMySQL();
    Connection cc = cn.getConexion();

    public boolean registrarParticipante(Participante pParticipante) {
        try {
            CallableStatement agregarParticipante = cc.prepareCall("{call agregarParticipante(?,?,?,?)}");
            agregarParticipante.setString(1, pParticipante.getPrimerNombre());
            agregarParticipante.setString(2, pParticipante.getPrimerApellido());
            agregarParticipante.setString(3, pParticipante.getCorreo());
            agregarParticipante.setInt(4, pParticipante.getIdReserva());
            agregarParticipante.execute();
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
