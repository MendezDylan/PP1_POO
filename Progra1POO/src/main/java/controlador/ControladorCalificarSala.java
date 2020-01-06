package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VentanaCalificarSala;
/**
 *
 * @author Jeremy
 */
public class ControladorCalificarSala implements ActionListener {
  VentanaCalificarSala vista = new VentanaCalificarSala(); 
  
  
  public void ControladorCalificarSala (){
    this.vista.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}

