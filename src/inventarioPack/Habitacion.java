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
	// parte nueva
	private int tamano;
	private boolean aireAcondicionado;
	private boolean calefaccion;
	private int tamanoCama;
	private boolean TV;
	private boolean cafetera;
	private boolean ropaDeCamayTapetesHipo;
	private boolean plancha;
	private boolean secadoraDePelo;
	private double voltajeAC;
	private boolean tomaUSB_A;
	private boolean tomaUSB_C;
	private boolean incluyeDesayuno;
	private List<Cama> camas;
	private HashMap<String, ArrayList<Huesped>> reservasProfundidad;

	public Habitacion(String tipoHabitacion, String ID, String ubicacion, String tieneBalcon, String tieneVista,
			String tieneCocina, int capacidadAdulto, int capacidadNino,
			// esta es la parte nueva
			int tamano, String aireAcondicionado,
			String calefaccion, int tamanoCama,
			String TV,
			String cafetera,
			String ropaDeCamayTapetesHipo,
			String plancha, String secadoraDePelo,
			double voltajeAC,
			String tomaUSB_A, String tomaUSB_C, String incluyeDesayuno) {
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
		this.tamano = tamano;
		if (aireAcondicionado.equals("Sí")) {
			this.aireAcondicionado = true;
		} else {
			this.aireAcondicionado = false;
		}
		if (calefaccion.equals("Sí")) {
			this.calefaccion = true;
		} else {
			this.calefaccion = false;
		}
		if (TV.equals("Sí")) {
			this.TV = true;
		} else {
			this.TV = false;
		}
		this.tamanoCama = tamanoCama;
		if (cafetera.equals("Sí")) {
			this.cafetera = true;
		} else {
			this.cafetera = false;
		}
		if (ropaDeCamayTapetesHipo.equals("Sí")) {
			this.ropaDeCamayTapetesHipo = true;
		} else {
			this.ropaDeCamayTapetesHipo = false;
		}
		if (plancha.equals("Sí")) {
			this.plancha = true;
		} else {
			this.plancha = false;
		}
		if (secadoraDePelo.equals("Sí")) {
			this.secadoraDePelo = true;
		} else {
			this.secadoraDePelo = false;
		}
		this.voltajeAC = voltajeAC;
		if (tomaUSB_A.equals("Sí")) {
			this.tomaUSB_A = true;
		} else {
			this.tomaUSB_A = false;
		}
		if (tomaUSB_C.equals("Sí")) {
			this.tomaUSB_C = true;
		} else {
			this.tomaUSB_C = false;
		}
		if (incluyeDesayuno.equals("Sí")) {
			this.incluyeDesayuno = true;
		} else {
			this.incluyeDesayuno = false;
		}
	}

	public void hacerReserva(String initialDate, String finalDate, ArrayList<Huesped> huespeds) {
		ArrayList<String> dates = Inventario.fechas2rango(initialDate, finalDate);
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

	public void cancelarReserva(Huesped huesped) {
		ArrayList<String> fechasReservadas = fechasPerCliente(huesped);
		for (String date : fechasReservadas) {
			reservasProfundidad.remove(date);
		}
	}

	public boolean sePuedeCancelar(String fechaActual, Huesped huesped) {
		boolean sePuede = true;
		ArrayList<String> fechasReservadas = fechasPerCliente(huesped);
		for (String fecha : fechasReservadas) {
			int diferencia = diferenciaFechas(fechaActual, fecha);
			if ((diferencia <= 2) || (isAfter(fechaActual, fecha))) {
				sePuede = false;
			}
		}
		return sePuede;
	}

	private boolean isAfter(String fechaInicio, String fechaAComparar) {
		LocalDate fecha1 = LocalDate.parse(fechaInicio);
		LocalDate fecha2 = LocalDate.parse(fechaAComparar);

		// Comparar fechas usando isAfter()
		if (fecha1.isAfter(fecha2)) {
			return true;
		} else {
			return false;
		}
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
		resp += "le caben " + String.valueOf(capacidadAdulto) + " adultos y, ";
		resp += "le caben " + String.valueOf(capacidadNino) + " niños,";
		resp += "Tiene un tamaño de " + String.valueOf(tamano) + " metros cuadrados, ";
		resp += "La cama mide " + String.valueOf(tamanoCama) + " metros cuadrados, ";
		resp += "El voltaje AC es de " + String.valueOf(voltajeAC) + " voltios, ";
		if (aireAcondicionado) {
			resp += "tiene aire acondicionado, ";
		} else {
			resp += "no tiene aire acondicionado, ";
		}
		if (calefaccion) {
			resp += "tiene calefaccion, ";
		} else {
			resp += "no tiene calefaccion, ";
		}
		if (TV) {
			resp += "tiene TV, ";
		} else {
			resp += "no tiene TV, ";
		}
		if (cafetera) {
			resp += "tiene cafetera, ";
		} else {
			resp += "no tiene cafetera, ";
		}
		if (ropaDeCamayTapetesHipo) {
			resp += "tiene ropa de cama y tapetes hipoalergénicos, ";
		} else {
			resp += "no tiene ropa de cama y tapetes hipoalergénicos, ";
		}
		if (plancha) {
			resp += "tiene plancha, ";
		} else {
			resp += "no tiene plancha, ";
		}
		if (secadoraDePelo) {
			resp += "tiene secador de pelo, ";
		} else {
			resp += "no tiene secador de pelo, ";
		}
		if (tomaUSB_A) {
			resp += "tiene toma USB-A, ";
		} else {
			resp += "no tiene toma USB-A, ";
		}
		if (tomaUSB_C) {
			resp += "tiene tomas USB-C, ";
		} else {
			resp += "no tiene tomas USB-C, ";
		}
		if (incluyeDesayuno) {
			resp += "Incluye desayuno, ";
		} else {
			resp += "no incluye desayuno, ";
		}
		resp += "El Hotel tiene Parqueadero pago en el hotel, Parqueadero gratuito en el hotel, Piscina, Zonas húmedas, BBQ";
		resp += ", Wifi gratis, Recepción 24 horas, Admite mascotas";
		return resp;
	}

	// X int tamano;
	// boolean aireAcondicionado;
	// boolean calefaccion;
	// X int tamanoCama;
	// boolean TV;
	// boolean cafetera;
	// boolean ropaDeCamayTapetesHipo;
	// boolean plancha;
	// boolean secadoraDePelo;
	// X double voltajeAC;
	// boolean tomaUSB_A;
	// boolean tomaUSB_C;
	// boolean incluyeDesayuno;

	public HashMap<String, ArrayList<Huesped>> getReservasProfundidad() {
		return reservasProfundidad;
	}

	public void setReservasProfundidad(HashMap<String, ArrayList<Huesped>> reservasProfundidad) {
		this.reservasProfundidad = reservasProfundidad;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public boolean isAireAcondicionado() {
		return aireAcondicionado;
	}

	public void setAireAcondicionado(boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	public boolean isCalefaccion() {
		return calefaccion;
	}

	public void setCalefaccion(boolean calefaccion) {
		this.calefaccion = calefaccion;
	}

	public int getTamanoCama() {
		return tamanoCama;
	}

	public void setTamanoCama(int tamanoCama) {
		this.tamanoCama = tamanoCama;
	}

	public boolean isTV() {
		return TV;
	}

	public void setTV(boolean tV) {
		TV = tV;
	}

	public boolean isCafetera() {
		return cafetera;
	}

	public void setCafetera(boolean cafetera) {
		this.cafetera = cafetera;
	}

	public boolean isRopaDeCamayTapetesHipo() {
		return ropaDeCamayTapetesHipo;
	}

	public void setRopaDeCamayTapetesHipo(boolean ropaDeCamayTapetesHipo) {
		this.ropaDeCamayTapetesHipo = ropaDeCamayTapetesHipo;
	}

	public boolean isPlancha() {
		return plancha;
	}

	public void setPlancha(boolean plancha) {
		this.plancha = plancha;
	}

	public boolean isSecadoraDePelo() {
		return secadoraDePelo;
	}

	public void setSecadoraDePelo(boolean secadoraDePelo) {
		this.secadoraDePelo = secadoraDePelo;
	}

	public double getVoltajeAC() {
		return voltajeAC;
	}

	public void setVoltajeAC(double voltajeAC) {
		this.voltajeAC = voltajeAC;
	}

	public boolean isTomaUSB_A() {
		return tomaUSB_A;
	}

	public void setTomaUSB_A(boolean tomaUSB_A) {
		this.tomaUSB_A = tomaUSB_A;
	}

	public boolean isTomaUSB_C() {
		return tomaUSB_C;
	}

	public void setTomaUSB_C(boolean tomaUSB_C) {
		this.tomaUSB_C = tomaUSB_C;
	}

	public boolean isIncluyeDesayuno() {
		return incluyeDesayuno;
	}

	public void setIncluyeDesayuno(boolean incluyeDesayuno) {
		this.incluyeDesayuno = incluyeDesayuno;
	}

}
