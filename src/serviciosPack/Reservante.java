package serviciosPack;

import java.util.ArrayList;

public class Reservante extends Huesped {

    private String correo;
    private Long numCell;
    private int acompanantes;
    private String fechaInicio;
    private String fechaFin;
    private ArrayList<String> IDHabitaciones;

    public Reservante(String nombre, int edad, String iD, String correo, Long numCell, int acompanantes,
            int IDGrupo, String fechaInicio, String fechaFin) {
        super(nombre, edad, iD, IDGrupo);
        // [nombre;edad;ID;correo;númeroCelular;acompañantes;familiaID;fechaInicio;fechaFinal;ID-Habitacion]
        this.correo = correo;
        this.numCell = numCell;
        this.acompanantes = acompanantes;

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.IDHabitaciones = new ArrayList<String>();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getNumCell() {
        return numCell;
    }

    public void setNumCell(Long numCell) {
        this.numCell = numCell;
    }

    public int getAcompanantes() {
        return acompanantes;
    }

    public void setAcompanantes(int acompanantes) {
        this.acompanantes = acompanantes;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<String> getIDHabitaciones() {
        return IDHabitaciones;
    }

    public void setIDHabitacion(ArrayList<String> iDHabitacion) {
        IDHabitaciones = iDHabitacion;
    }

    public void agregarHabitacion(String ID) {
        IDHabitaciones.add(ID);
    }

}
