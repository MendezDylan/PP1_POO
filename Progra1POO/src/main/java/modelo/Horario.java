
package modelo;

public class Horario {
    private String nombreHorario;
    private String diasDisponible;
    private String horaApertura;
    private String horaCierre;

    public Horario() {
    }

    public Horario(String nombreHorario, String diasDisponible, String horaApertura, String horaCierre) {
        this.nombreHorario = nombreHorario;
        this.diasDisponible = diasDisponible;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public String getNombreHorario() {
        return nombreHorario;
    }

    public String getDiasDisponible() {
        return diasDisponible;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    @Override
    public String toString() {
        return "Horario{" + "nombreHorario=" + nombreHorario + ", diasDisponible=" + diasDisponible + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + '}';
    }
    
    
}
