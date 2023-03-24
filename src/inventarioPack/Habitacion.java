package inventarioPack;

import java.util.ArrayList;
import java.util.List;

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

	public Habitacion(String tipoHabitacion, String ID, String ubicacion, String tieneBalcon, String tieneVista,
			String tieneCocina, int capacidadAdulto, int capacidadNino) {
		this.tipoHabitacion = tipoHabitacion;
		this.ID = ID;
		this.ubicacion = ubicacion;
		camas = new ArrayList<Cama>();

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
}
