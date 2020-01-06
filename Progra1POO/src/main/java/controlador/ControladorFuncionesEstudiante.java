package controlador;
import controlador.ControladorCalificarSala;
import modelo.Reserva;
import vista.VentanaCancelarReserva;
import vista.VentanaEstudiante;
import vista.VentanaRegistroReservas;
import dao.EstudianteDAO;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFuncionesEstudiante implements ActionListener {

    public VentanaEstudiante vista;

    public ControladorFuncionesEstudiante(VentanaEstudiante pVista) {
        vista = pVista;
        this.vista.btAgregarReserva.addActionListener(this);
        this.vista.btCalificarSala.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Agregar Reserva":
                agregarSala();
                break;
            case "Cancelar Reserva":
                agregarSala();
                break;
           case "Calificar Sala":               
                this.vista.setVisible(false);
                ControladorCalificarSala controladorCalificarSala = new ControladorCalificarSala();
                controladorCalificarSala.vista.txtNU.setText(this.vista.txtNU.getText());
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    public void agregarSala() {
        VentanaRegistroReservas vista = new VentanaRegistroReservas();
        Reserva reserva = new Reserva();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        EstudianteDAO daoEstudiante = new EstudianteDAO();
        ControladorAgregarReservaDAO controladorReserva = new ControladorAgregarReservaDAO(vista, reserva);
        controladorReserva.vista.txtNU.setText(this.vista.txtNU.getText());
        String cedula = String.valueOf(daoUsuario.obtenerCedula(controladorReserva.vista.txtNU.getText()));
        int carnet = daoEstudiante.obtenerCarnet(controladorReserva.vista.txtNU.getText());
        controladorReserva.vista.txtCedula.setText(cedula);
        controladorReserva.vista.txtCarnet.setText(String.valueOf(carnet));
        controladorReserva.vista.setVisible(true);
        controladorReserva.vista.setLocationRelativeTo(null);
    }
    
    public void cancelarReserva(){
        VentanaCancelarReserva vista = new VentanaCancelarReserva();
        ControladorCancelarReservaDAO controladorCancelarReserva = new ControladorCancelarReservaDAO(vista);
        controladorCancelarReserva.vista.setVisible(true);
        controladorCancelarReserva.vista.setLocationRelativeTo(null);
    }
    
    public void calificarSala(){;
    }
}
