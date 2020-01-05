package modelo;

import java.util.ArrayList;

public class Reserva {

    private int idReserva;
    private String asunto;
    private String horaInicio;
    private String horaFin;
    private int capacidad;
    private int idEstado;
    private int cedula;
    private String idSala;
    private ArrayList<Participante> listaParticipantes;

    public Reserva() {
    }

    public Reserva(int pIdReserva, String asunto, String horaInicio, String horaFin, int capacidad, int cedula, String idSala) {
        this.idReserva = pIdReserva;
        this.asunto = asunto;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.capacidad = capacidad;
        this.idEstado = 1;
        this.cedula = cedula;
        this.idSala = idSala;
        this.listaParticipantes = new ArrayList<Participante>();
    }

    public int getIdReserva() {
        return idReserva;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public int getCedula() {
        return cedula;
    }

    public String getIdSala() {
        return idSala;
    }

    public void agregarParticipante(Participante pParticipante) {
        Participante part = new Participante(pParticipante.getPrimerNombre(), pParticipante.getPrimerApellido(), pParticipante.getCorreo(), pParticipante.getIdReserva());
        listaParticipantes.add(part);
    }

    @Override
    public String toString() {
        return "Reserva{" + "asunto=" + asunto + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", capacidad=" + capacidad + ", idEstado=" + idEstado + ", cedula=" + cedula + ", idSala=" + idSala + '}';
    }
}
