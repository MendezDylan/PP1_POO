package modelo;

public class Recurso {

    private int idRecurso;
    private String detalle;
    private int cantidad;
    private String idSala;

    public Recurso() {
    }

    public Recurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }
    
    public Recurso(int idRecurso, int cantidad) {
        this.idRecurso = idRecurso;
        this.cantidad = cantidad;
    }

    public Recurso(int pIdRecurso, String pDetalle, int pCantidad, String pIdSala) {
        this.idRecurso = pIdRecurso;
        this.detalle = pDetalle;
        this.cantidad = pCantidad;
        this.idSala = pIdSala;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public String getDetalle() {
        return detalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getIdSala() {
        return idSala;
    }

    @Override
    public String toString() {
        return "Recurso{" + "idRecurso=" + idRecurso + ", detalle=" + detalle + ", cantidad=" + cantidad + '}';
    }
}
