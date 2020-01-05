package controlador;

import conexion.ConexionJavaMySQL;
import modelo.Participante;
import modelo.Reserva;
import vista.VentanaRegistroReservas;
import dao.ParticipanteDAO;
import dao.ReservaDAO;
import dao.SalaDAO;
import dao.VariosDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorAgregarReservaDAO implements ActionListener {
    public ReservaDAO dao;
    public VentanaRegistroReservas vista;
    public Reserva reserva;

    public ControladorAgregarReservaDAO(VentanaRegistroReservas pVista, Reserva pReserva) {
        vista = pVista;
        reserva = pReserva;
        this.vista.btBuscar.addActionListener(this);
        this.vista.btBusquedaAvanzada.addActionListener(this);
        this.vista.btAtras.addActionListener(this);
        this.vista.btAddParticipante.addActionListener(this);
        this.vista.btCrearReserva.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Buscar":
                buscarSala();
                break;
            case "Atrás":
                cerrarVentana();
                break;
            case "Búsqueda Avanzada":
                buscarSalaEspecifica();
                break;
            case "Añadir Participante":
                añadirParticipante();
                break;
            case "Crear Reserva":
                agregarReserva();
                break;
            default:
                break;
        }
    }

    public void buscarSala() {
        if (vista.validarDatos()) {
            String horaInicio = vista.txtHoraIni.getText();
            String horaFin = vista.txtHoraFin.getText();
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                vista.tablaSalas.setModel(modelo);

                PreparedStatement ps = null;
                ResultSet rs = null;
                ConexionJavaMySQL conn = new ConexionJavaMySQL();
                Connection con = conn.getConexion();
                /*
            String sql = "select s.identificador from sala s\n"
                    + "where s.identificador not in (\n"
                    + "Select s.identificador from sala s \n"
                    + "inner join reserva r on s.identificador = r.idSala\n"
                    + "where '"+horaInicio+"' = r.fechaHoraInicio and '"+horaFin+"' = r.fechaHoraFinalizacion )";
                 */

                String sql = " Select s.identificador from sala s\n"
                        + " where s.identificador not in( \n"
                        + " Select s.identificador from sala s \n"
                        + " inner join reserva re on s.identificador = re.idSala\n"
                        + " where ('" + horaFin + "' between re.fechaHoraInicio and re.fechaHoraFinalizacion) \n"
                        + " or ('" + horaInicio + "' between re.fechaHoraInicio and re.fechaHoraFinalizacion));";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();
                modelo.addColumn("Identificador");
                int[] anchos = {100, 100};
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
        } else {
            JOptionPane.showMessageDialog(vista, "Se necesitan todos los datos");
        }
    }

    public void buscarSalaEspecifica() {
        if (vista.validarDatosCompletos()) {
            String idSala = vista.txtIdSala.getText();
            String recurso = vista.txtRecursoPrimario.getText();
            int cantPersonas = Integer.parseInt(vista.txtCantPersonas.getText());
            try {
                DefaultTableModel modelo = new DefaultTableModel();
                vista.tablaBusAvan.setModel(modelo);

                PreparedStatement ps = null;
                ResultSet rs = null;
                ConexionJavaMySQL conn = new ConexionJavaMySQL();
                Connection con = conn.getConexion();

                String sql = "Select distinct (s.identificador),s.ubicacion,s.capacidad from sala s \n"
                        + " inner join recurso rec on rec.idSala = s.identificador\n"
                        + " where s.identificador = '" + idSala + "' and '" + recurso + "' = rec.detalle and s.capacidad >= " + cantPersonas + "";;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Identificador");
                modelo.addColumn("Ubicación");
                modelo.addColumn("Capacidad");

                int[] anchos = {50, 100, 100, 50, 50, 50};
                for (int i = 0; i < vista.tablaBusAvan.getColumnCount(); i++) {
                    vista.tablaBusAvan.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
        } else {
            JOptionPane.showMessageDialog(vista, "Se necesitan todos los datos");
        }
    }

    public void añadirParticipante() {
        if (vista.validarDatosParticipante()) {
            String primerNombre = vista.txtPriNombre.getText();
            String primerApellido = vista.txtPriApe.getText();
            String correo = vista.txtCorreo.getText();
            int idReserva = Integer.parseInt(vista.txtIdReserva.getText());
            Participante participante = new Participante(primerNombre, primerApellido, correo, idReserva);
            ParticipanteDAO daoParticipante = new ParticipanteDAO();
            VariosDAO dao = new VariosDAO();
            int capacidad = Integer.parseInt(vista.txtCapacidadSala.getText());
            if (dao.validarCantidadParticipantes(reserva) < capacidad) {
                if (daoParticipante.registrarParticipante(participante)) {
                    JOptionPane.showMessageDialog(vista, "Registro exitoso");
                    reserva.agregarParticipante(participante);
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al registrar");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Cantidad de participantes superada");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Se necesitan todos los datos");
        }
    }

    public void agregarReserva() {
        int idReserva = Integer.parseInt(vista.txtIdReserva.getText());
        String asunto = vista.txtTema.getText();
        String fechaHoraInicio = vista.txtHoraIni.getText();
        String fechaHoraFin = vista.txtHoraFin.getText();
        int cantidadPersonas = Integer.parseInt(vista.txtCantPersonas.getText());
        int cedula = Integer.parseInt(vista.txtCedula.getText());
        String idSala = vista.txtIdSala.getText();
        Reserva reserva = new Reserva(idReserva, asunto, fechaHoraInicio, fechaHoraFin, cantidadPersonas, cedula, idSala);
        ReservaDAO daoReserva = new ReservaDAO();
        VariosDAO daoVarios = new VariosDAO();
        SalaDAO daoSala = new SalaDAO();
        if (!vista.validarDatosReserva()) {
            JOptionPane.showMessageDialog(vista, "Se requiere toda la información");
        } else {
            if (daoVarios.validarCalificacion(vista.txtNU.getText()) > 70
                    && daoVarios.validarCantidadIncidentes(vista.txtNU.getText()) < 5
                    && daoSala.obtenerEstadoSala(idSala) == 1) {
                if (daoReserva.registrarReserva(reserva)) {
                    JOptionPane.showMessageDialog(vista, "Reserva creada con éxito");
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al crear Reserva");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Error,no se cumplen los requisitos necesarios");
            }
        }
    }

    public String generarCodigo() {
        String codigo = "";
        codigo = codigo + vista.txtIdSala.getText() + vista.txtIdReserva.getText() + vista.txtCarnet.getText();
        return codigo;
    }

    public void cerrarVentana() {
        vista.cerrarVentana();
    }
}
