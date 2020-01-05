package controlador;

import vista.VentanaCancelarReserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorCancelarReservaDAO implements ActionListener {

    public VentanaCancelarReserva vista;

    public ControladorCancelarReservaDAO(VentanaCancelarReserva pVista) {
        vista = pVista;
        this.vista.btCancelarReserva.addActionListener(this);
        this.vista.btVerReservas.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ver reservas":
                //buscarSala();
                break;
            case "Cancelar Reserva":
                //cerrarVentana();
                break;
            case "Búsqueda Avanzada":
                //buscarSalaEspecifica();
                break;
            case "Añadir Participante":
                //añadirParticipante();
                break;
            case "Crear Reserva":
                //agregarReserva();
                break;
            default:
                break;
        }
    }
}
