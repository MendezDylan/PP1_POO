package modelo;

import java.util.ArrayList;

public class Sala {

    private static int cantidadSalas = 0;
    private String idSala;
    private String ubicacion;
    private int capacidadMaxima;
    private int calificacion;
    private int estado;
    private int idHorario;
    private ArrayList<Recurso> listaRecursos;
    private ArrayList<Horario> listaHorarios;
    private ArrayList<HorarioExcepcion> listaExcepciones;

    public Sala() {
    }

    public Sala(String pIdSala, String pUbicacion) {
        this.idSala = pIdSala;
        this.ubicacion = pUbicacion;
        cantidadSalas++;
    }

    public Sala(String pIdSala, int pEstado) {
        this.idSala = pIdSala;
        this.estado = pEstado;
        cantidadSalas++;
    }

    public Sala(String pIdSala, String pUbicacion, int pCapacidadMaxima, int pEstado, int pIdHorario) {
        this.idSala = pIdSala;
        this.ubicacion = pUbicacion;
        this.capacidadMaxima = pCapacidadMaxima;
        this.calificacion = 100;
        this.estado = pEstado;
        this.idHorario = pIdHorario;
        this.listaRecursos = new ArrayList<Recurso>();
        this.listaHorarios = new ArrayList<Horario>();
        this.listaExcepciones = new ArrayList<HorarioExcepcion>();
        cantidadSalas++;
    }

    public static int getCantidadSalas() {
        return cantidadSalas;
    }

    public String getIdSala() {
        return idSala;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String pUbicacion) {
        this.ubicacion = pUbicacion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int pCalificacion) {
        this.calificacion = pCalificacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int pEstado) {
        this.estado = pEstado;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void agregarRecurso(Recurso pRecurso) {
        Recurso recurso = new Recurso(pRecurso.getIdRecurso(), pRecurso.getDetalle(), pRecurso.getCantidad(), pRecurso.getIdSala());
        listaRecursos.add(recurso);
    }

    public void eliminarRecurso() {
        Recurso recurso = new Recurso();
        listaRecursos.remove(recurso);
    }

    public void agregarHorario() {
        Horario horario = new Horario();
        listaHorarios.add(horario);
    }

    public void agregarHorarioExcepcion() {
        HorarioExcepcion horario = new HorarioExcepcion();
        listaExcepciones.add(horario);
    }
}
