package inventarioPack;

public class Cama {
	private int largo;
	private int ancho;
	private boolean soloNinos;
	private int capacidad;
	
	public Cama(String persona, int capacidad, int largo, int ancho) {
		if (persona.equals("niño")) {
			soloNinos = true;
		}else {
			soloNinos = false;
		}
		this.largo = largo;
		this.ancho = ancho;
		this.capacidad = capacidad;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public boolean isSoloNinos() {
		return soloNinos;
	}

	public void setSoloNinos(boolean soloNinos) {
		this.soloNinos = soloNinos;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
}
