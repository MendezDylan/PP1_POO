package controlador;

import conexion.ConexionJavaMySQL;
import modelo.Recurso;
import modelo.Sala;
import vista.VentanaModificarSala;
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

public class ControladorModificarSalaDAO implements ActionListener {

    public SalaDAO daoSala;
    public RecursoDAO daoRecurso;
    public VentanaModificarSala vista;
    public Sala sala;
    public Recurso recurso;

    public ControladorModificarSalaDAO(VentanaModificarSala pVista, Sala pSala, Recurso pRecurso) {
        vista = pVista;
        sala = pSala;
        recurso = pRecurso;
        this.vista.btAgregarRecurso.addActionListener(this);
        this.vista.btEliminarRecurso.addActionListener(this);
        this.vista.btModificarCantRec.addActionListener(this);
        this.vista.btModificarEst.addActionListener(this);
        this.vista.btModificarUbi.addActionListener(this);
        this.vista.btRegresar.addActionListener(this);
        this.vista.btVerSalas.addActionListener(this);
        this.vista.btVerRecursos.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ver salas":
                mostrarSalas();
                break;
            case "Modificar Estado":
                modificarEstadoSala();
                break;
            case "Modificar Ubicación":
                modificarUbicacionSala();
                break;
            case "Modificar Cantidad":
                modificarCantidadRecursos();
                break;
            case "Agregar Recurso":
                agregarRecurso();
                break;
            case "Eliminar Recurso":
                eliminarRecurso();
                break;
            case "Ver recursos":
                verRecursosSala();
                break;
            case "Regresar":
                cerrarVentana();
                break;
            default:
                break;
        }
    }

    public void mostrarSalas() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.tablaSalas.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            ConexionJavaMySQL conn = new ConexionJavaMySQL();
            Connection con = conn.getConexion();
            String sql = "SELECT identificador, ubicacion, capacidad, idEstado , idHorario FROM Sala";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("identificador");
            modelo.addColumn("ubicacion");
            modelo.addColumn("capacidad");
            modelo.addColumn("idEstado");
            modelo.addColumn("idHorario");
            int[] anchos = {50, 100, 100, 50, 50, 50};
            for (int i = 0; i < vista.tablaSalas.getColumnCount(); i++) {
                vista.tablaSalas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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

    public void modificarUbicacionSala() {
        String identificador = vista.txtIdSala.getText();
        String ubicacion = vista.txtUbicacion.getText();
        Sala sala = new Sala(identificador, ubicacion);
        SalaDAO dao = new SalaDAO();
        if (!vista.txtUbicacion.getText().equals("")) {
            if (dao.modificarUbicacionSala(sala)) {
                JOptionPane.showMessageDialog(vista, "Cambios guardados");
                vista.limpiarCamposModificacionesSala();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al modificar datos");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Campo vacío");
        }
    }

    public void modificarEstadoSala() {
        String identificador = vista.txtIdSala.getText();
        int estado = Integer.parseInt(vista.txtEstado.getText());
        Sala sala = new Sala(identificador, estado);
        SalaDAO dao = new SalaDAO();
        if (!vista.txtEstado.getText().equals("")) {
            if (dao.modificarEstadoSala(sala)) {
                JOptionPane.showMessageDialog(vista, "Cambios guardados");
                vista.limpiarCamposModificacionesSala();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al modificar datos");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Campo vacío");
        }
    }

    public void verRecursosSala() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.tablaRecursos.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            ConexionJavaMySQL conn = new ConexionJavaMySQL();
            Connection con = conn.getConexion();
            String sql = "SELECT re.idRecurso, re.detalle, re.cantidad FROM Recurso re inner join Sala sa on re.idSala = sa.identificador Where sa.identificador = '" + vista.txtIdSala.getText() + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idRecurso");
            modelo.addColumn("detalle");
            modelo.addColumn("cantidad");
            int[] anchos = {50, 100, 100, 50};
            for (int i = 0; i < vista.tablaRecursos.getColumnCount(); i++) {
                vista.tablaRecursos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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

    public void modificarCantidadRecursos() {
        int idRecurso = Integer.parseInt(vista.txtIdRecurso.getText());
        int cantidad = Integer.parseInt(vista.txtModCant.getText());
        Recurso recurso = new Recurso(idRecurso, cantidad);
        RecursoDAO dao = new RecursoDAO();
        if (dao.modificarRecurso(recurso)) {
            JOptionPane.showMessageDialog(vista, "Registro guardado");
            vista.limpiarCamposRecurso();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar");
        }
    }

    public void agregarRecurso() {
        int idRecurso = Integer.parseInt(vista.txtIdRecurso.getText());
        String descripcion = vista.txtNombre.getText();
        int cantidad = Integer.parseInt(vista.txtCant.getText());
        String idSala = vista.txtIdSala.getText();
        Recurso recurso = new Recurso(idRecurso, descripcion, cantidad, idSala);
        RecursoDAO dao = new RecursoDAO();
        if (dao.registrarRecurso(recurso)) {
            JOptionPane.showMessageDialog(vista, "Registro guardado");
            vista.limpiarCamposRecurso();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar");
        }
    }

    public void eliminarRecurso() {
        int idRecurso = Integer.parseInt(vista.txtIdRecurso.getText());
        Recurso recurso = new Recurso(idRecurso);
        RecursoDAO dao = new RecursoDAO();
        if (dao.eliminarRecurso(recurso)) {
            JOptionPane.showMessageDialog(vista, "Registro eliminado");
            vista.limpiarCamposRecurso();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar");
        }
    }

    public void cerrarVentana() {
        vista.cerrarVentana();
    }
}
