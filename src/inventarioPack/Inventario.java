package inventarioPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

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
				yaExiste = false;
			}
		}
		return yaExiste;
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
			if (primero) {
				primero = false;
			} else {
				texto += "\n";
			}
			String fecha = entry.getKey();
			ArrayList<Tarifa> tarifas = entry.getValue();
			for (Tarifa tarifa : tarifas) {
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
