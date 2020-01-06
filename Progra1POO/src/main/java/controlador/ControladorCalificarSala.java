package controlador;
import api.Sms;
import conexion.ConexionJavaMySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import vista.VentanaCalificarSala;
/**
 *
 * @author Jeremy
 */
public class ControladorCalificarSala implements ActionListener {
  VentanaCalificarSala vista = new VentanaCalificarSala();
  
  
  
  
  public ControladorCalificarSala (){
    this.vista.setVisible(true);
    this.vista.botonGenerar.addActionListener(this);
  }
  
  @Override
  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Generar Codigo":
        
        try {
          PreparedStatement ps = null;
          ResultSet rs = null;
          ConexionJavaMySQL conn = new ConexionJavaMySQL();
          Connection con = conn.getConexion();
          String carne;
          String sql = "Select persona.telefono from persona join usuario on usuario.cedula=persona.cedula where nombreUsuario='Jere'";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
          ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
          int cantidadColumnas = rsMd.getColumnCount();
          System.out.println("Si entra123");
          while (rs.next()) {
            Object[] filas = new Object[cantidadColumnas];
            for (int i = 0; i < cantidadColumnas; i++) {
              filas[i] = rs.getObject(i + 1);
            }
            int numero;
            numero=(int) filas[0];
            String s = String.valueOf(numero);
            Sms.enviarMensaje("Hola Maldito", s);
            System.out.println(numero);
          }  
        } catch (SQLException ex) {
          System.err.println(ex.toString());
        }
        
        break;
      default:
        System.out.println("Si entra123");
        break;
    }
  }
}

