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
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import vista.VentanaCalificarSala;
/**
 *
 * @author Jeremy
 */
public class ControladorCalificarSala implements ActionListener {
  VentanaCalificarSala vista = new VentanaCalificarSala();
          public String aleatorio(String resultado, String palabras, int numero) {

        String[] arrayPalabra = palabras.split("");

        int numeroAleatorio = 0;

        for (int i = 0; i < numero; i++) {

            numeroAleatorio = (int) (Math.random() * (arrayPalabra.length - 1) + 0);



            resultado = resultado + arrayPalabra[numeroAleatorio];

        }

        return resultado;
    
}
  
  
  
  
  public ControladorCalificarSala (){
    this.vista.setVisible(true);
    this.vista.botonGenerar.addActionListener(this);
  }
  
  @Override
  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Generar Codigo":
        
        try {
          String nombre=vista.txtNU.getText();
          PreparedStatement ps = null;
          ResultSet rs = null;
          ConexionJavaMySQL conn = new ConexionJavaMySQL();
          Connection con = conn.getConexion();
          String carne;
          String sql = "Select persona.telefono from persona join usuario on usuario.cedula=persona.cedula where nombreUsuario='"+nombre+"'";
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
            String LetrasMay, LetrasMin, CaracteresEsp, numeros, resultado = "";
        LetrasMin = "abcdefghijhlmnñopqrtuwxyz";
        LetrasMay = "ABCDEFGHIJQLMNÑOPQRTSUVWXYZ";
        CaracteresEsp = "+-*/=%&#!?^“‘~|<>()[]{}:;.,";
        numeros = "1234567890";
        Scanner sca = new Scanner(System.in);
        resultado=aleatorio(resultado, LetrasMin, 3);
        resultado=aleatorio(resultado, LetrasMay, 2);
        resultado=aleatorio(resultado, CaracteresEsp, 2);
        resultado=aleatorio(resultado, numeros, 3);
        System.out.println(""+resultado);
            int numero;
            numero=(int) filas[0];
            String s = String.valueOf(numero);
            Sms.enviarMensaje(resultado, s);
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

