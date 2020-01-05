package controlador;

import conexion.ConexionJavaMySQL;
import modelo.Recurso;
import modelo.Sala;
import vista.VentanaAgregarSala;
import dao.RecursoDAO;
import dao.SalaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorSalaDAO implements ActionListener {

    public SalaDAO dao;
    public VentanaAgregarSala vista;
    public Sala modelo;
    public Recurso recurso;

    public ControladorSalaDAO(VentanaAgregarSala pVista, Sala pSala, Recurso pRecurso) {
        vista = pVista;
        modelo = pSala;
        recurso = pRecurso;
        this.vista.btAgregar.addActionListener(this);
        this.vista.btHorarios.addActionListener(this);
        this.vista.btAgregarRecurso.addActionListener(this);
        this.vista.btSalir.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ver Horarios":
                cargarHorarios();
                break;
            case "Agregar":
                agregarSala();
                break;
            case "Agregar Recurso":
                agregarRecurso();
                break;
            case "Regresar":
                cerrarVentana();
                break;
            default:
                break;
        }
    }

    public void cargarHorarios() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.tablaHorarios.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            ConexionJavaMySQL conn = new ConexionJavaMySQL();
            Connection con = conn.getConexion();
            String sql = "SELECT idHorario, nombreHorario, diasDisponible, horaApertura , horaCierre FROM Horario";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idHorario");
            modelo.addColumn("nombreHorario");
            modelo.addColumn("diasDisponibles");
            modelo.addColumn("horaApertura");
            modelo.addColumn("horaCierre");
            int[] anchos = {50, 100, 100, 50, 50, 50};
            for (int i = 0; i < vista.tablaHorarios.getColumnCount(); i++) {
                vista.tablaHorarios.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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

    public void agregarSala() {
        if (!vista.validarDatosSala()) {
            JOptionPane.showMessageDialog(vista, "Se requieren todos los datos");
        } else {
            String idSala = vista.txtIdentificador.getText();
            String ubicacion = vista.txtUbicacion.getText();
            int capacidad = Integer.parseInt(vista.txtCapacidad.getText());
            int estado;
            if (vista.cbxEstado.getSelectedItem().equals("Activa")) {
                estado = 1;
            } else if (vista.cbxEstado.getSelectedItem().equals("Mantenimiento")) {
                estado = 2;
            } else {
                estado = 3;
            }
            int idHorario = Integer.parseInt(vista.txtIdHorario.getText());
            modelo = new Sala(idSala, ubicacion, capacidad, estado, idHorario);
            SalaDAO daoSala = new SalaDAO();
            if (daoSala.registrarSala(modelo)) {
                JOptionPane.showMessageDialog(vista, "Sala agregada");
                vista.limpiarCamposSala();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar la sala");
            }
        }
    }

    public void agregarRecurso() {
        if (!vista.validarDatosRecurso()) {
            JOptionPane.showMessageDialog(vista, "Se requieren todos los datos");
        } else {
            String idSala = vista.txtIdentificador.getText();
            int idRecurso = Integer.parseInt(vista.txtIdRecurso.getText());
            String nombreRec = vista.txtNombreRec.getText();
            int cantidadRec = Integer.parseInt(vista.txtCantidad.getText());
            recurso = new Recurso(idRecurso, nombreRec, cantidadRec, idSala);
            RecursoDAO dao = new RecursoDAO();
            if (dao.registrarRecurso(recurso)) {
                modelo.agregarRecurso(recurso);
                JOptionPane.showMessageDialog(vista, "Recurso Agregado");
                vista.limpiarCamposRecurso();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar recurso");
            }
        }
    }

    public void cerrarVentana() {
        vista.cerrarVentana();
    }
}
