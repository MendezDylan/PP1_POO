package controlador;

import modelo.Usuario;
import vista.VentanaEstudiante;
import vista.VentanaFuncionario;
import vista.VentanaRegistroUsuario;
import vista.VentanaLogin;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {

    public UsuarioDAO dao;
    public VentanaLogin vista;
    public Usuario modelo;
    public static String nombreUser;

    public ControladorLogin(VentanaLogin pVista, Usuario pModelo) {
        vista = pVista;
        modelo = pModelo;
        this.vista.btInicio.addActionListener(this);
        this.vista.btSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Iniciar Sesión":
                logIn();
                break;
            case "Salir":
                cerrarVentanaLogin();
                break;
            default:
                break;
        }
    }

    public void logIn() {
        if (vista.validarDatos() == true) {
            String nombreUsuario = vista.txtNombreUsuario.getText();
            String contraseña = vista.txtPass.getText();
            modelo = new Usuario(nombreUsuario, contraseña);
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuarioActual = dao.iniciarSesion(modelo);
            if (usuarioActual != null) {
                JOptionPane.showMessageDialog(vista, "El usuario indicado no existe");
            } else {
                vista.setVisible(false);
                if (dao.validacionEstado(nombreUsuario).equals("Activado")) {
                    switch (dao.validarRol(nombreUsuario, contraseña)) {
                        case 1:
                            VentanaRegistroUsuario vista2 = new VentanaRegistroUsuario();
                            Usuario modelo2 = new Usuario();
                            ControladorUsuarioDAO controladorPersona = new ControladorUsuarioDAO(vista2, modelo2);
                            JOptionPane.showMessageDialog(vista, "Bienvenido: " + modelo.getNombreUsuario());
                            vista.limpiarCampos();
                            controladorPersona.vista.setVisible(true);
                            controladorPersona.vista.setLocationRelativeTo(null);
                            break;
                        case 2:
                            VentanaFuncionario vista3 = new VentanaFuncionario();
                            ControladorFuncionarioDAO controladorFuncionario = new ControladorFuncionarioDAO(vista3);
                            JOptionPane.showMessageDialog(vista, "Bienvenido: " + modelo.getNombreUsuario());
                            vista.limpiarCampos();
                            controladorFuncionario.vista.setVisible(true);
                            controladorFuncionario.vista.setLocationRelativeTo(null);
                            break;
                        case 3:
                            nombreUser = vista.txtNombreUsuario.getText();
                            VentanaEstudiante vistaEstudiante = new VentanaEstudiante();
                            JOptionPane.showMessageDialog(vista, "Bienvenido: " + modelo.getNombreUsuario());
                            vista.limpiarCampos();
                            ControladorFuncionesEstudiante controladorFuncionesEstudiante = new ControladorFuncionesEstudiante(vistaEstudiante);
                            controladorFuncionesEstudiante.vista.txtNU.setText(nombreUser);
                            controladorFuncionesEstudiante.vista.setVisible(true);
                            controladorFuncionesEstudiante.vista.setLocationRelativeTo(null);
                            break;
                        default:
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "El usuario se encuentra desactivado");
                    vista.setVisible(true);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Todos los datos son requeridos");
        }
    }

    public void cerrarVentanaLogin() {
        vista.salirLogin();
    }
}
