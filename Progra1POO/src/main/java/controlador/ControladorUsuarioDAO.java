package controlador;

import conexion.ConexionJavaMySQL;
import modelo.Estudiante;
import modelo.Usuario;
import vista.VentanaLogin;
import vista.VentanaRegistroPersona;
import vista.VentanaRegistroUsuario;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorUsuarioDAO implements ActionListener {

    public UsuarioDAO dao;
    public VentanaRegistroUsuario vista;
    public Usuario modelo;

    public ControladorUsuarioDAO(VentanaRegistroUsuario pVista, Usuario pUsuario) {
        vista = pVista;
        modelo = pUsuario;
        this.vista.btAddUser.addActionListener(this);
        this.vista.btOpciones.addActionListener(this);
        this.vista.btSalir.addActionListener(this);
        this.vista.btUpdate.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Añadir Usuario":
                añadirUsuario();
                break;
            case "Opciones":
                abrirVentanaOpciones();
                break;
            case "Actualizar":
                mostrarPersonas();
                break;
            case "Salir":
                cerrarVentanaAgregarUsuario();
                break;
            default:
                break;
        }
    }

    public void añadirUsuario() {
        if (vista.validarDatos() == true) {
            int cedula = Integer.parseInt(vista.txtId.getText());
            String nombreUsuario = vista.txtUser.getText();
            String pass = vista.txtPass.getText();
            String estado = (String) vista.cbxEstado.getSelectedItem();
            int rol = Integer.parseInt((String) vista.cbxRol.getSelectedItem());
            modelo = new Usuario(cedula, nombreUsuario, pass, estado, rol);
            UsuarioDAO dao = new UsuarioDAO();
            if (dao.registrarUsuario(modelo)) {
                JOptionPane.showMessageDialog(vista, "Usuario ingresado correctamente");
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar el usuario");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Todos los datos son requeridos");
        }
    }

    public void abrirVentanaOpciones() {
        Estudiante modelo = new Estudiante();
        VentanaRegistroPersona vista = new VentanaRegistroPersona();
        ControladorPersonaDAO controladorUsuario = new ControladorPersonaDAO(vista, modelo);
        controladorUsuario.vista.setVisible(true);
        controladorUsuario.vista.setLocationRelativeTo(null);
    }

    public void mostrarPersonas() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.tablaPersona.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            ConexionJavaMySQL conn = new ConexionJavaMySQL();
            Connection con = conn.getConexion();

            String sql = "SELECT cedula, primerNombre, primerApellido, correo , telefono FROM Persona";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Cédula");
            modelo.addColumn("PrimerNombre");
            modelo.addColumn("PrimerApellido");
            modelo.addColumn("Correo");
            modelo.addColumn("Teléfono");

            int[] anchos = {50, 100, 100, 50, 50, 50};
            for (int i = 0; i < vista.tablaPersona.getColumnCount(); i++) {
                vista.tablaPersona.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    public void cerrarVentanaAgregarUsuario() {
        vista.cerrarVentana();
        Usuario modelo = new Usuario();
        VentanaLogin vista = new VentanaLogin();
        ControladorLogin controladorUsuario = new ControladorLogin(vista, modelo);
        controladorUsuario.vista.setVisible(true);
        controladorUsuario.vista.setLocationRelativeTo(null);
    }
}
