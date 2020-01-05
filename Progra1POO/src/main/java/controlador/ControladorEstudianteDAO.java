package controlador;

import conexion.ConexionJavaMySQL;
import modelo.Estudiante;
import vista.VentanaRegistroEstudiante;
import dao.EstudianteDAO;
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

public class ControladorEstudianteDAO implements ActionListener {

    public EstudianteDAO dao;
    public VentanaRegistroEstudiante vista;
    public Estudiante modelo;

    public ControladorEstudianteDAO(VentanaRegistroEstudiante pVista) {
        vista = pVista;
        this.vista.btActualizar.addActionListener(this);
        this.vista.btAtras.addActionListener(this);
        this.vista.btRegistrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Actualizar":
                mostrarEstudiantes();
                break;
            case "Registrar":
                modificarEstudiante();
                break;
            case "Atrás":
                cerrarVentanaEstudiante();
                break;
            default:
                break;
        }
    }
    
    public void modificarEstudiante(){
      if ( !vista.validarDatos() ) {
          JOptionPane.showMessageDialog(vista, "Todos los datos son requeridos");
      } else {
        int cedula = Integer.parseInt(vista.txtCed.getText());
        String primerNombre = vista.txtPNombre.getText();
        String primerApellido = vista.txtPApellido.getText();
        String carrera = vista.txtCarrera.getText();
        String correo = vista.txtCorreo.getText();
        int telefono = Integer.parseInt(vista.txtTel.getText()); 
        int carnet = Integer.parseInt(vista.txtCarnet.getText());
        Estudiante estudiante = new Estudiante(cedula, primerNombre, primerApellido,carrera, correo, telefono, carnet);
        EstudianteDAO dao = new EstudianteDAO();
        UsuarioDAO dao2 = new UsuarioDAO();
        if ( dao.modificarEstudiante(estudiante) && dao2.modificarUsuario(estudiante)) {
          JOptionPane.showMessageDialog(vista, "Registro exitoso");
          vista.limpiarCampos();
        } else {
          JOptionPane.showMessageDialog(vista, "Error en el registro");
        }
       
      }
    }
    
     public void mostrarEstudiantes() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.tablaEstudiantes.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            ConexionJavaMySQL conn = new ConexionJavaMySQL();
            Connection con = conn.getConexion();

            String sql = "SELECT pe.cedula, pe.primerNombre, pe.primerApellido, pe.correo , pe.telefono FROM persona pe inner join usuario u on pe.cedula = u.cedula where u.estadoUsuario = 'Desactivado'";
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
            for (int i = 0; i < vista.tablaEstudiantes.getColumnCount(); i++) {
                vista.tablaEstudiantes.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
     
       public void cerrarVentanaEstudiante() {
        vista.cerrarVentana();
    }
}
