package inventarioPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventario {
	private static Map<String, ArrayList<Tarifa>> tarifas;
	public static List<Habitacion> habitaciones;
	public static List<ServicioRestaurante> menu;
	public static List<Servicio> servicios;

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

	@SuppressWarnings("null")
	private static void cargarTarifas() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/" + "tarifasHabitaciones.txt";
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
				ArrayList<Tarifa> lista = null;
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
		String archivo = System.getProperty("user.dir") + "/data/" + "menu.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String nombre = partes[0];
			String aDonde = partes[1];
			String horaInicio = partes[2];
			String horaFinal = partes[3];
			int precio = Integer.parseInt(partes[4]);
			ServicioRestaurante servicioRestaurante = new ServicioRestaurante(nombre, precio, "persona", aDonde,
					horaInicio, horaFinal);
			menu.add(servicioRestaurante);
		}
		scan.close();
	}

	private static void cargarServicios() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/" + "tarifasServicios.txt";
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
		String archivo = System.getProperty("user.dir") + "/data/" + "habitaciones.txt";
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
		String archivo = System.getProperty("user.dir") + "/data/" + "camas.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String ID = partes[0];
			int capacidad = Integer.parseInt(partes[6]);
			int largo = Integer.parseInt(partes[6]);
			int ancho = Integer.parseInt(partes[7]);
			Cama cama = new Cama(ID, capacidad, largo, ancho);
			meterCama(ID, cama);
		}
		scan.close();
	}

	private static void meterCama(String ID, Cama cama) {
		for (Habitacion habitacion : habitaciones) {
			if (habitacion.getID().equals(ID)) {
				habitacion.anadirCama(cama);
			}
		}
	}

}
