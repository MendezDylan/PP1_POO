package modelo;

public class Participante {

    private String primerNombre;
    private String primerApellido;
    private String correo;
    private int idReserva;

    public Participante() {
    }

    public Participante(String primerNombre, String primerApellido, String correo, int idReserva) {
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.correo = correo;
        this.idReserva = idReserva;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    @Override
    public String toString() {
        return "Participante{" + "primerNombre=" + primerNombre + ", primerApellido=" + primerApellido + ", correo=" + correo + ", idReserva=" + idReserva + '}';
    }

    
}
