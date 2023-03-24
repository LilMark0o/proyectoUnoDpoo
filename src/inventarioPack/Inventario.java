package inventarioPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Inventario {
	private static Map<String, ArrayList<Tarifa>> tarifas;
	private static List<Habitacion> habitaciones;
	private static List<ServicioRestaurante> menu;
	private static List<Servicio> servicios;

	public static void cargarInventario() throws FileNotFoundException {
		tarifas = new HashMap<String, ArrayList<Tarifa>>();
		cargarTarifas();
		menu = new ArrayList<ServicioRestaurante>();
		cargarMenu();
		servicios = new ArrayList<Servicio>();
		cargarServicios();
		habitaciones = new ArrayList<Habitacion>();
		cargarHabitaciones();
		cargarCamas();
	}

	public static void meterHabitacion(Habitacion habitacion) {
		habitaciones.add(habitacion);
	}

	public static boolean idYaExiste(String ID) {
		boolean yaExiste = false;
		for (Habitacion habitacion : habitaciones) {
			if (habitacion.getID().equals(ID)) {
				yaExiste = true;
			}
		}
		return yaExiste;
	}

	public static Habitacion habitacionPorID(String ID) {
		Habitacion habitacionBuscada = null;
		for (Habitacion habitacion : habitaciones) {
			if (habitacion.getID().equals(ID)) {
				habitacionBuscada = habitacion;
			}
		}
		return habitacionBuscada;
	}

	private static void cargarTarifas() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/inventarioData/" + "tarifasHabitaciones.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String date = partes[0];
			String day = partes[1];
			String habitacion = partes[2];
			int precio = Integer.parseInt(partes[3]);
			Tarifa tarifa = new Tarifa(habitacion, precio, day);
			if (!tarifas.containsKey(date)) {
				ArrayList<Tarifa> lista = new ArrayList<Tarifa>();
				lista.add(tarifa);
				tarifas.put(date, lista);
			} else {
				ArrayList<Tarifa> lista = tarifas.get(date);
				lista.add(tarifa);
				tarifas.put(date, lista);
			}
		}
		scan.close();
	}

	private static void cargarMenu() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/inventarioData/" + "menu.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String nombre = partes[0];
			String aCuarto = partes[1];
			String horaInicio = partes[2];
			String horaFinal = partes[3];
			int precio = Integer.parseInt(partes[4]);
			ServicioRestaurante servicioRestaurante = new ServicioRestaurante(nombre, precio, "persona", aCuarto,
					horaInicio, horaFinal);
			menu.add(servicioRestaurante);
		}
		scan.close();
	}

	private static void cargarServicios() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/inventarioData/" + "tarifasServicios.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			String cantidadCliente = partes[2];
			Servicio servicio = new Servicio(nombre, precio, cantidadCliente);
			servicios.add(servicio);
		}
		scan.close();
	}

	// [Tipo habitacion ; ID ; Ubicacion ; Tiene Balcón ; Tiene Vista ; Tiene Cocina
	// ; Capacidad Adulto ; Capacidad Niños]
	private static void cargarHabitaciones() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/inventarioData/" + "habitaciones.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String tipoHabitacion = partes[0];
			String ID = partes[1];
			String ubicacion = partes[2];
			String tieneBalcon = partes[3];
			String tieneVista = partes[4];
			String tieneCocina = partes[5];
			int capacidadAdulto = Integer.parseInt(partes[6]);
			int capacidadNino = Integer.parseInt(partes[7]);
			Habitacion habitacion = new Habitacion(tipoHabitacion, ID, ubicacion, tieneBalcon, tieneVista, tieneCocina,
					capacidadAdulto, capacidadNino);
			habitaciones.add(habitacion);
		}
		scan.close();
	}

	public static void cargarHabitaciones(String fileName) throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/inventarioData/" + fileName;
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String tipoHabitacion = partes[0];
			String ID = partes[1];
			String ubicacion = partes[2];
			String tieneBalcon = partes[3];
			String tieneVista = partes[4];
			String tieneCocina = partes[5];
			int capacidadAdulto = Integer.parseInt(partes[6]);
			int capacidadNino = Integer.parseInt(partes[7]);
			Habitacion habitacion = new Habitacion(tipoHabitacion, ID, ubicacion, tieneBalcon, tieneVista, tieneCocina,
					capacidadAdulto, capacidadNino);
			habitaciones.add(habitacion);
		}
		scan.close();
	}

	private static void cargarCamas() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/inventarioData/" + "camas.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String ID = partes[0];
			String paraQuien = partes[1];
			int capacidad = Integer.parseInt(partes[2]);
			int largo = Integer.parseInt(partes[3]);
			int ancho = Integer.parseInt(partes[4]);
			Cama cama = new Cama(paraQuien, capacidad, largo, ancho);
			meterCama(ID, cama);
		}
		scan.close();
	}

	private static Calendar string2Calendar(String fechaStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDate = null;
		try {
			fechaDate = sdf.parse(fechaStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaDate);

		return calendar;

	}

	public static void cambiarTarifa(String tipoHabitacion, String initialDate, String finalDate, String days,
			// ! esto es horrendo
			int tarifaNum) {
		Calendar initialCal = string2Calendar(initialDate);
		Calendar finalCal = string2Calendar(finalDate);
		ArrayList<Integer> diasDeLaSemanaNumbers = string2Integers(days);

		for (Calendar date = initialCal; date.compareTo(finalCal) <= 0; date.add(Calendar.DATE, 1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = sdf.format(date.getTime()); // acá ya está como YYYY-MM-dd
			int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
			if (diasDeLaSemanaNumbers.contains(dayOfWeek)) {
				// el día de la semana es uno de lo que nos sirve
				if ((tarifas.containsKey(dateStr))) {
					// si existe, busca el del mismo tipo de habitación
					boolean existe = false;
					for (Tarifa tarifa : tarifas.get(dateStr)) {
						if (tarifa.getHabitacion().equals(tipoHabitacion)) {
							// si el tipo de habitación es el mismo, hay que dejar la que tenga menor tarifa
							if (tarifa.getPrecio() > tarifaNum) {
								tarifa.setPrecio(tarifaNum);
							}
							existe = true;
						}
					}
					if (!(existe)) {
						ArrayList<Tarifa> listaTarifa = tarifas.get(dateStr);
						String dayTarifa = num2String(dayOfWeek);
						Tarifa tarifa = new Tarifa(tipoHabitacion, tarifaNum, dayTarifa);
						listaTarifa.add(tarifa);
					}
				} else {
					// si no existe, toca crear ese día y poner la nueva tarifa
					ArrayList<Tarifa> listaTarifas = new ArrayList<Tarifa>();
					String dayTarifa = num2String(dayOfWeek);
					Tarifa tarifa = new Tarifa(tipoHabitacion, tarifaNum, dayTarifa);
					listaTarifas.add(tarifa);
					tarifas.put(dateStr, listaTarifas);
				}
			}

		}

	}

	public static ArrayList<Integer> string2Integers(String days) {
		// sabado -> 7; domingo -> 1; lunes -> 2
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		if (days.contains("domingo")) {
			numbers.add(1);
		}
		if (days.contains("lunes")) {
			numbers.add(2);
		}
		if (days.contains("martes")) {
			numbers.add(3);
		}
		if (days.contains("miercoles")) {
			numbers.add(4);
		}
		if (days.contains("miércoles")) {
			numbers.add(4);
		}
		if (days.contains("jueves")) {
			numbers.add(5);
		}
		if (days.contains("viernes")) {
			numbers.add(6);
		}
		if (days.contains("sábado")) {
			numbers.add(7);
		}
		if (days.contains("sabado")) {
			numbers.add(7);
		}
		return numbers;
	}

	public static String num2String(int diaSemanaInt) {
		if (diaSemanaInt == 1) {
			return "domingo";
		} else if (diaSemanaInt == 2) {
			return "lunes";
		} else if (diaSemanaInt == 3) {
			return "martes";
		} else if (diaSemanaInt == 4) {
			return "miércoles";
		} else if (diaSemanaInt == 5) {
			return "jueves";
		} else if (diaSemanaInt == 6) {
			return "viernes";
		} else if (diaSemanaInt == 7) {
			return "sábado";
		} else {
			return "indeterminado";
		}
	}

	public static boolean faltaAlgunaTarifa() {
		boolean faltaAlgunaTarifa = false;
		int year = 2023; // año
		int mes = 2; // marzo -> 2
		int dia = 24; // día del mes
		Calendar start = Calendar.getInstance();
		start.set(year, mes, dia); // 24 de marzo de 2023
		Calendar end = Calendar.getInstance();
		end.set(year + 1, mes, dia); // 24 de marzo de 2024

		// Iterar sobre el rango de fechas
		for (Calendar date = start; date.compareTo(end) <= 0; date.add(Calendar.DATE, 1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// Formatear la fecha como un String
			String dateStr = sdf.format(date.getTime());
			if ((tarifas.containsKey(dateStr))) {
				ArrayList<Tarifa> lista = tarifas.get(dateStr);
				if (lista.size() < 3) {
					faltaAlgunaTarifa = true;
				}
			} else {
				faltaAlgunaTarifa = true;
			}
		}
		return faltaAlgunaTarifa;
	}

	private static void meterCama(String ID, Cama cama) {
		for (Habitacion habitacion : habitaciones) {
			if (habitacion.getID().equals(ID)) {
				habitacion.anadirCama(cama);
			}
		}
	}

	public static void guardarCambios() throws IOException {
		String textoGuardarHabitacionesSTR = textoGuardarHabitaciones();
		guardarArchivo(textoGuardarHabitacionesSTR, "habitaciones.txt");
		String textoGuardarMenuSTR = textoGuardarMenu();
		guardarArchivo(textoGuardarMenuSTR, "menu.txt");
		String textoGuardarTarifa = textoGuardarTarifa();
		guardarArchivo(textoGuardarTarifa, "tarifasHabitaciones.txt");
		String textoGuardarServicios = textoGuardarServicios();
		guardarArchivo(textoGuardarServicios, "tarifasServicios.txt");
	}

	// ! todo esto es horrible, pero es la unica forma que se me ocurrio para
	// ! guardarlo en el mismo formato :D

	private static String textoGuardarServicios() {
		String aMeterGrande = "";
		boolean primero = true;
		for (Servicio servicio : servicios) {
			if (primero) {
				primero = false;
			} else {
				aMeterGrande += "\n";
			}
			String uno = servicio.getNombre();
			String dos = String.valueOf(servicio.getCosto());
			String tres = servicio.getCantidadCliente();
			String aMeter = uno + ";" + dos + ";" + tres;
			aMeterGrande += aMeter;
		}
		return aMeterGrande;
	}

	private static String textoGuardarTarifa() {
		String texto = "";
		boolean primero = true;
		for (Entry<String, ArrayList<Tarifa>> entry : tarifas.entrySet()) {
			String fecha = entry.getKey();
			ArrayList<Tarifa> tarifas = entry.getValue();
			for (Tarifa tarifa : tarifas) {
				if (primero) {
					primero = false;
				} else {
					texto += "\n";
				}
				texto += fecha + ";" + tarifa.getDay() + ";" + tarifa.getHabitacion() + ";"
						+ (String.valueOf(tarifa.getPrecio())) + ";";
			}
		}
		return texto;
	}

	private static String textoGuardarMenu() {
		String aMeterGrande = "";
		boolean primero = true;
		for (ServicioRestaurante producto : menu) {
			if (primero) {
				primero = false;
			} else {
				aMeterGrande += "\n";
			}
			String uno = producto.getNombre();
			String dos = "";
			if (producto.isaCuarto()) {
				dos = "A habitación";
			} else {
				dos = "En restaurante";
			}
			String tres = producto.getHoraInicio();
			String cuatro = producto.getHoraFinal();
			String cinco = String.valueOf(producto.getCosto());
			String aMeter = uno + ";" + dos + ";" + tres + ";" + cuatro + ";" + cinco;
			aMeterGrande += aMeter;
		}
		return aMeterGrande;
	}

	private static String textoGuardarHabitaciones() {
		String aMeterGrande = "";
		boolean primero = true;
		for (Habitacion habitacion : habitaciones) {
			if (primero) {
				primero = false;
			} else {
				aMeterGrande += "\n";
			}
			String uno = habitacion.getTipoHabitacion();
			String dos = habitacion.getID();
			String tres = habitacion.getUbicacion();
			String cuatro = "";
			if (habitacion.isTieneBalcon()) {
				cuatro = "Sí";
			} else {
				cuatro = "No";
			}
			String cinco = "";
			if (habitacion.isTieneVista()) {
				cinco = "Sí";
			} else {
				cinco = "No";
			}
			String seis = "";
			if (habitacion.isTieneCocina()) {
				seis = "Sí";
			} else {
				seis = "No";
			}
			String siete = String.valueOf(habitacion.getCapacidadAdulto());
			String ocho = String.valueOf(habitacion.getCapacidadNino());
			String aMeter = uno + ";" + dos + ";" + tres + ";" + cuatro + ";" + cinco + ";" + seis + ";" + siete + ";"
					+ ocho;
			aMeterGrande += aMeter;
		}
		return aMeterGrande;
	}

	private static void guardarArchivo(String text, String fileName) throws IOException {
		String archivo = System.getProperty("user.dir") + "/data/inventarioData/" + fileName;
		FileWriter writer = new FileWriter(archivo);
		writer.write(text);
		writer.close();
	}

}
