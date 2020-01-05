package controlador;

import modelo.Estudiante;
import vista.VentanaRegistroPersona;
import dao.EstudianteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorPersonaDAO implements ActionListener {

    public Estudiante dao;
    public VentanaRegistroPersona vista;
    public Estudiante modelo;

    public ControladorPersonaDAO(VentanaRegistroPersona pVista, Estudiante pEstudiante) {
        vista = pVista;
        modelo = pEstudiante;
        this.vista.btRegistrar.addActionListener(this);
        this.vista.btRegresar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Registrar":
                añadirPersona();
                break;
            case "Regresar":
                cerrarVentanaAgregarPersona();
                break;
            default:
                break;
        }
    }

    public void añadirPersona() {
        if (vista.validarDatos() == true) {
            int cedula = Integer.parseInt(vista.txtCed.getText());
            String primerNombre = vista.txtNombre.getText();
            String primerApellido = vista.txtApellido.getText();
            String correo = vista.txtCorreo.getText();
            int telefono = Integer.parseInt(vista.txtTel.getText());
            modelo = new Estudiante(cedula, primerNombre, primerApellido, correo, telefono);
            EstudianteDAO dao = new EstudianteDAO();
            if (dao.registrarPersona(modelo)) {
                JOptionPane.showMessageDialog(vista, "Registro ingresado correctamente");
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar el registro");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Todos los datos son requeridos");
        }
    }

    public void cerrarVentanaAgregarPersona() {
        vista.cerrarVentana();
    }

}
