package inventarioPack;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import serviciosPack.Huesped;

public class Habitacion {
	private String tipoHabitacion;
	private String ID;
	private String ubicacion;
	private boolean tieneBalcon;
	private boolean tieneVista;
	private boolean tieneCocina;
	private int capacidadAdulto;
	private int capacidadNino;
	private List<Cama> camas;
	private HashMap<String, ArrayList<Huesped>> reservasProfundidad;

	public Habitacion(String tipoHabitacion, String ID, String ubicacion, String tieneBalcon, String tieneVista,
			String tieneCocina, int capacidadAdulto, int capacidadNino) {
		this.tipoHabitacion = tipoHabitacion;
		this.ID = ID;
		this.ubicacion = ubicacion;
		camas = new ArrayList<Cama>();
		reservasProfundidad = new HashMap<String, ArrayList<Huesped>>();

		if (tieneBalcon.equals("Sí")) {
			this.tieneBalcon = true;
		} else {
			this.tieneBalcon = false;
		}

		if (tieneVista.equals("Sí")) {
			this.tieneVista = true;
		} else {
			this.tieneVista = false;
		}

		if (tieneCocina.equals("Sí")) {
			this.tieneCocina = true;
		} else {
			this.tieneCocina = false;
		}
		this.capacidadAdulto = capacidadAdulto;
		this.capacidadNino = capacidadNino;
	}

	public void hacerReserva(ArrayList<String> dates, ArrayList<Huesped> huespeds) {
		for (String date : dates) {
			reservasProfundidad.put(date, huespeds);
		}
	}

	public boolean sePuedeReservar(ArrayList<String> dates) {
		boolean sePuede = true;
		for (String date : dates) {
			if (reservasProfundidad.containsKey(date)) {
				sePuede = false;
			}
		}
		return sePuede;
	}

	public void cancelarReserva(ArrayList<String> dates) {
		for (String date : dates) {
			if (reservasProfundidad.containsKey(date)) {
				reservasProfundidad.remove(date);
			}
		}
	}

	public boolean sePuedeCancelar(String fechaActual, Huesped huesped) {
		boolean sePuede = true;
		ArrayList<String> fechasReservadas = fechasPerCliente(huesped);
		for (String fecha : fechasReservadas) {
			int diferencia = diferenciaFechas(fechaActual, fecha);
			if (diferencia > 2) {
				sePuede = false;
			}
		}
		return sePuede;
	}

	private int diferenciaFechas(String fecha1, String fecha2) {
		LocalDate date1 = LocalDate.parse(fecha1);
		LocalDate date2 = LocalDate.parse(fecha2);

		// Calcular la diferencia entre las fechas en días
		int diff = (int) ChronoUnit.DAYS.between(date1, date2);
		return diff;
	}

	private ArrayList<String> fechasPerCliente(Huesped huesped) {
		ArrayList<String> fechasEscogidas = new ArrayList<String>();
		for (Entry<String, ArrayList<Huesped>> entry : reservasProfundidad.entrySet()) {
			String fecha = entry.getKey();
			ArrayList<Huesped> huespeds = entry.getValue();
			if (huespeds.contains(huesped)) {
				fechasEscogidas.add(fecha);
			}
		}
		return fechasEscogidas;
	}

	public void anadirCama(Cama cama) {
		camas.add(cama);
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isTieneBalcon() {
		return tieneBalcon;
	}

	public void setTieneBalcon(boolean tieneBalcon) {
		this.tieneBalcon = tieneBalcon;
	}

	public boolean isTieneVista() {
		return tieneVista;
	}

	public void setTieneVista(boolean tieneVista) {
		this.tieneVista = tieneVista;
	}

	public boolean isTieneCocina() {
		return tieneCocina;
	}

	public void setTieneCocina(boolean tieneCocina) {
		this.tieneCocina = tieneCocina;
	}

	public int getCapacidadAdulto() {
		return capacidadAdulto;
	}

	public void setCapacidadAdulto(int capacidadAdulto) {
		this.capacidadAdulto = capacidadAdulto;
	}

	public int getCapacidadNino() {
		return capacidadNino;
	}

	public void setCapacidadNino(int capacidadNino) {
		this.capacidadNino = capacidadNino;
	}

	public List<Cama> getCamas() {
		return camas;
	}

	public void setCamas(List<Cama> camas) {
		this.camas = camas;
	}

	public String toString() {
		String resp = "";
		resp += "La habitación es una " + tipoHabitacion + ", ";
		resp += "tiene el ID" + ID + ", ";
		if (tieneBalcon) {
			resp += "tiene un hermoso balcón, ";
		} else {
			resp += "no tiene balcón, ";
		}
		if (tieneVista) {
			resp += "tiene una hermosa vista, ";
		} else {
			resp += "no tiene vista, ";
		}
		if (tieneCocina) {
			resp += "tiene una hermosa cocina, ";
		} else {
			resp += "no tiene cocina, ";
		}
		resp += "le caben " + String.valueOf(capacidadAdulto) + " adultos y ";
		resp += "le caben " + String.valueOf(capacidadNino) + " niños.";
		return resp;
	}

}
