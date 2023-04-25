package serviciosPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import console.CambiarTarifa;
import console.InventarioFrame;
import console.PreguntarAcompanante;
import controllerPack.Controller;
import inventarioPack.Habitacion;
import inventarioPack.Inventario;
import inventarioPack.Servicio;
import inventarioPack.ServicioRestaurante;

public class Servicios {
	private static HashMap<String, ArrayList<Servicio>> registroServicios;
	private static ArrayList<Huesped> huespeds;
	private static ArrayList<Reservante> reservantes;
	private static HashMap<Integer, ArrayList<Huesped>> grupos;

	public static int IDGrupoActual;
	public static boolean segui;
	public static ArrayList<Huesped> personitasImportadas;
	public static int acompanantesActual;

	public static void cargarServicios() throws FileNotFoundException {
		grupos = new HashMap<Integer, ArrayList<Huesped>>();
		registroServicios = new HashMap<String, ArrayList<Servicio>>();
		cargarRegistroServicios();
		reservantes = new ArrayList<Reservante>();
		cargarReservantes();
		huespeds = new ArrayList<Huesped>();
		cargarHuespedes();
	}

	public static void registrarServicio(String ID, String servicioNombre, String pagadoEnElMomento) {
		Servicio servicioBuscado = Inventario.buscarServicio(servicioNombre);
		if (servicioBuscado.equals(null)) {
			CambiarTarifa.showErrorFrame("No se ha encontrado ese servicio");

		} else {
			boolean pagado = false;
			if (pagadoEnElMomento.equals("Sí")) {
				pagado = true;
			}
			servicioBuscado.setPagado(pagado);
			if (registroServicios.containsKey(ID)) {
				ArrayList<Servicio> servicios = registroServicios.get(ID);
				servicios.add(servicioBuscado);
				registroServicios.put(ID, servicios);
			} else {
				ArrayList<Servicio> servicios = new ArrayList<Servicio>();
				servicios.add(servicioBuscado);
				registroServicios.put(ID, servicios);
			}
			CambiarTarifa.showSuccessFrame("¡Servicio encontrado y cargado exitosamente!");

		}
	}

	public static int generarIdGrupo() {
		int num = 1;
		boolean encontrado = false;
		while (!encontrado) {
			if (grupos.containsKey(num)) {
				num += 1;
			} else {
				encontrado = true;
			}
		}
		return num;
	}

	public static void hacerReserva(ArrayList<Huesped> huespedesAAlojar) {
		Reservante reservante = (Reservante) huespedesAAlojar.get(0);
		reservantes.add(reservante);
		for (Huesped huesped : huespedesAAlojar) {
			if (!(huesped.getID().equals(reservante.getID()))) {
				huespeds.add(huesped);
			}
		}
		grupos.put(reservante.getIDgrupo(), huespedesAAlojar);
	}

	public static void registrarServicioRestaurante(String ID, String servicioNombre, String pagadoEnElMomento) {
		ServicioRestaurante servicioBuscado = Inventario.buscarServicioRestauraServicio(servicioNombre);
		if (servicioBuscado.equals(null)) {
			CambiarTarifa.showErrorFrame("No se ha encontrado este producto");
		} else {
			boolean pagado = false;
			if (pagadoEnElMomento.equals("Sí")) {
				pagado = true;
			}
			servicioBuscado.setPagado(pagado);
			if (registroServicios.containsKey(ID)) {
				ArrayList<Servicio> servicios = registroServicios.get(ID);
				servicios.add(servicioBuscado);
				registroServicios.put(ID, servicios);
			} else {
				ArrayList<Servicio> servicios = new ArrayList<Servicio>();
				servicios.add(servicioBuscado);
				registroServicios.put(ID, servicios);
			}
			CambiarTarifa.showSuccessFrame("Producto encontrado y cargado exitosamente!");
		}
	}

	public static void pagarServicio(String ID, String servicioNombre) {
		if (registroServicios.containsKey(ID)) {
			ArrayList<Servicio> serviciosDelUsuario = registroServicios.get(ID);
			boolean existe = false;
			boolean hecho = false;
			for (Servicio servicio : serviciosDelUsuario) {
				if (servicio.getNombre().equals(servicioNombre)) {
					existe = true;
					if (!(hecho)) {
						servicio.setPagado(true);
						hecho = true;
					}
				}
			}
			if (existe) {
				CambiarTarifa.showSuccessFrame("¡pago realizado exitosamente!!");
			} else {
				CambiarTarifa.showErrorFrame("No se encontró el servicio a pagar, pago rechazado");

			}

		} else {
			CambiarTarifa.showErrorFrame("El ID con el que se quiere pagar no se encuentra registrado");

		}
	}

	public static String generarFactura(String ID) throws IOException {
		String textoFacturesco = "";
		if (existeReservante(ID)) {
			Reservante reservante = getReservantePerID(ID);
			textoFacturesco += "-----  Factura a nombre de " + reservante.getNombre() + " -----\n";
			textoFacturesco += "---  Con fecha de ingreso de " + String.valueOf(reservante.getFechaInicio())
					+ " ---\n";
			textoFacturesco += "---  Y fecha de salida de " + String.valueOf(reservante.getFechaFin())
					+ " ---\n";

			int IDGrupo = reservante.getIDgrupo();
			ArrayList<String> IDhabitaciones = reservante.getIDHabitaciones();
			for (String IDhabitacion : IDhabitaciones) {
				Habitacion habitacion = Inventario.getHabitacionPerID(IDhabitacion);
				// ! poner habitacion ID, tipo, tarifa
				textoFacturesco += "En su estadía se reservó la habitación de tipo " + habitacion.getTipoHabitacion()
						+ " con ID" + habitacion.getID();
				textoFacturesco += "\n";
				textoFacturesco += "La tarifa pagada fue de: $" + Inventario.tarifaAPagar(
						habitacion.getTipoHabitacion(), reservante.getFechaInicio(), reservante.getFechaFin());
				textoFacturesco += "\n";
			}
			for (String IDhabitacion : IDhabitaciones) {
				Habitacion habitacion = Inventario.getHabitacionPerID(IDhabitacion);
				// ! poner habitacion ID, tipo, tarifa
				if (registroServicios.containsKey(habitacion.getID())) {
					ArrayList<Servicio> serviciosDelUsuario = registroServicios.get(habitacion.getID());
					if (serviciosDelUsuario.size() != 0) {
						textoFacturesco += "A la  habitación con ID" + habitacion.getID() + " se cargó:";
						textoFacturesco += "\n";
						for (Servicio servicio : serviciosDelUsuario) {
							textoFacturesco += servicio.getTextoFactura();
							textoFacturesco += "\n";
						}
					}
				}
			}

			ArrayList<Huesped> grupitoPersonas = grupos.get(IDGrupo);
			for (Huesped huesped : grupitoPersonas) {
				if (registroServicios.containsKey(huesped.getID())) {
					ArrayList<Servicio> serviciosDelUsuario = registroServicios.get(huesped.getID());
					if (serviciosDelUsuario.size() != 0) {
						textoFacturesco += "El huesped " + huesped.getNombre() + " consumió:";
						textoFacturesco += "\n";
						for (Servicio servicio : serviciosDelUsuario) {
							textoFacturesco += servicio.getTextoFactura();
							textoFacturesco += "\n";
						}
					}
				}
			}
			String fileName = "Factura_" + reservante.getNombre() + "_" + ID + ".txt";
			Inventario.guardarArchivo(textoFacturesco, "facturas", fileName);
			CambiarTarifa.showSuccessFrame("¡Factura generada exitosamente!");
			return "Factura generada exitosamente, revise la carpeta \"facturas\"";

		} else {
			CambiarTarifa.showErrorFrame("Este ID no está registrado");
			return "el ID con el que se quiere generar la factura no se encuentra registrado";
		}
	}

	public static ArrayList<Integer> personas2Numbers(ArrayList<Huesped> personitas) {
		ArrayList<Integer> edades = new ArrayList<Integer>();
		Integer adultos = 0;
		Integer ninos = 0;
		for (Huesped huesped : personitas) {
			if (huesped.getEdad() > 12) {
				adultos += 1;
			} else if (huesped.getEdad() >= 2) {
				ninos += 1;
			}
		}
		edades.add(adultos);
		edades.add(ninos);
		return edades;
	}

	private static void cargarRegistroServicios() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/serviciosData/" + "registroServicios.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String ID = partes[0];
			String nombreServicio = partes[1];
			String pagadoStr = partes[2];
			boolean pagado = false;
			if (pagadoStr.equals("Sí")) {
				pagado = true;
			}
			Servicio servicioRegistrado;
			servicioRegistrado = Inventario.buscarServicio(nombreServicio);
			if (servicioRegistrado == null) {
				servicioRegistrado = Inventario.buscarServicioRestauraServicio(nombreServicio);
			}
			if (!(servicioRegistrado.equals(null))) {
				servicioRegistrado.setPagado(pagado);
				if (registroServicios.containsKey(ID)) {
					ArrayList<Servicio> lista = registroServicios.get(ID);
					lista.add(servicioRegistrado);
					registroServicios.put(ID, lista);
				} else {
					ArrayList<Servicio> lista = new ArrayList<Servicio>();
					lista.add(servicioRegistrado);
					registroServicios.put(ID, lista);
				}
			}
		}
		scan.close();
	}

	public static void guardarCambios() throws IOException {
		String textoGuardarServicios = textoGuardarServicios();
		Inventario.guardarArchivo(textoGuardarServicios, "serviciosData", "registroServicios.txt");
		String textoGuardarHuespedes = textoGuardarHuespeds();
		Inventario.guardarArchivo(textoGuardarHuespedes, "reservasRegistroFactura", "huespeds.txt");
		String textoGuardarReservantes = textoGuardaReservantes();
		Inventario.guardarArchivo(textoGuardarReservantes, "reservasRegistroFactura", "reservas.txt");
	}

	public static String generarReserva(String nombreReservante, int edad, String IDReservante,
			String correoReservante, Long numeroCelular, int cantidadAcompanantes, String fechaInicial,
			String fechaFinal, String tipoDeHabitacion) throws InterruptedException {
		ArrayList<String> habitacionesVacias = Inventario.hayHabitacion(tipoDeHabitacion, fechaInicial, fechaFinal);
		if (habitacionesVacias.size() > 0) {
			int IdGrupal = Servicios.generarIdGrupo();
			Reservante reservante = new Reservante(nombreReservante, edad, IDReservante, correoReservante,
					numeroCelular, cantidadAcompanantes, IdGrupal, fechaInicial, fechaFinal);
			ArrayList<Huesped> personitas = new ArrayList<Huesped>();
			personitas.add(reservante);
			// !
			// ! GRAN PROBLEMA AQUI, NO SE QUE HACER
			// TODO preguntar a German como seguir xd
			IDGrupoActual = IdGrupal;
			personitasImportadas = new ArrayList<Huesped>();
			acompanantesActual = cantidadAcompanantes;
			HiloMensaje hilo = new HiloMensaje();
			hilo.start();
			// hilo.join();
			for (Huesped persona : personitasImportadas) {
				personitas.add(persona);
			}

			// int i = 0;
			// while (i < cantidadAcompanantes) {
			// String nombreAcompanante = Controller.input("¿Cúal es el nombre del
			// acompañante?");
			// int edadAcompanante = Integer
			// .parseInt(Controller.input("¿Cúal es la edad del acompañante? "));
			// String IDAcompanante = Controller.input("¿Cúal es el ID del acompañante?");
			// Huesped persona = new Huesped(nombreAcompanante, edadAcompanante,
			// IDAcompanante, IdGrupal);
			// personitas.add(persona);
			// i += 1;
			// }
			// !
			// ! GRAN PROBLEMA AQUI, NO SE QUE HACER
			// !
			ArrayList<Integer> edades = Servicios.personas2Numbers(personitas);
			Integer adultos = edades.get(0);
			Integer ninos = edades.get(1);
			ArrayList<String> habitacionesNecesarias = Inventario.asignarHabitacion(habitacionesVacias,
					adultos, ninos);
			if (habitacionesNecesarias == null) {
				CambiarTarifa.showErrorFrame("Hay habitaciones, pero no para tantas personas");
				return "Hay habitaciones, pero no para tantas personas";
			} else {
				String mensaje = "Se le asignó exitosamente el ID de habitación:";
				for (String ID : habitacionesNecesarias) {
					mensaje += "\n" + ID;
					reservante.agregarHabitacion(ID);
				}
				int costo = Controller.chismosearPrecio(tipoDeHabitacion, fechaInicial, fechaFinal);
				if (costo == -1) {
					CambiarTarifa.showErrorFrame("No hay tarifas disponibles para estas fechas");
					return "No hay tarifas disponibles para estas fechas, lo sentimos :(";
				} else {
					mensaje += "\n" + "Su estadía en el hotel costará: $" + String.valueOf(costo);
					for (String ID : habitacionesNecesarias) {
						Habitacion habitacion = Inventario.getHabitacionPerID(ID);
						habitacion.hacerReserva(fechaInicial, fechaFinal, personitas);
					}
					Servicios.hacerReserva(personitas);
					String[] mensajeDesintegrado = mensaje.split("\n");
					InventarioFrame.showInfoFrameLargo(mensajeDesintegrado);
					return mensaje;
				}
			}
		} else {
			CambiarTarifa.showErrorFrame("No hay habitaciones de este tipo disponibles para estas fechas");
			return "No hay habitaciones de este tipo disponibles para estas fechas, lo sentimos :(";
		}
	}

	private static String textoGuardarServicios() {
		String texto = "";
		boolean primero = true;
		for (Entry<String, ArrayList<Servicio>> entry : registroServicios.entrySet()) {
			String ID = entry.getKey();
			ArrayList<Servicio> servicios = entry.getValue();
			for (Servicio servicio : servicios) {
				if (primero) {
					primero = false;
				} else {
					texto += "\n";
				}
				String pagadoTxt = "No";
				if (servicio.isPagado()) {
					pagadoTxt = "Sí";
				}
				texto += ID + ";" + servicio.getNombre() + ";" + pagadoTxt;
			}
		}
		return texto;
	}

	private static void cargarHuespedes() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/reservasRegistroFactura/" + "huespeds.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int edad = Integer.parseInt(partes[1]);
			String ID = partes[2];
			int IDGrupo = Integer.parseInt(partes[3]);
			Huesped huesped = new Huesped(nombre, edad, ID, IDGrupo);
			huespeds.add(huesped);
			if (!(grupos.containsKey(huesped.getIDgrupo()))) {
				ArrayList<Huesped> fam = new ArrayList<Huesped>();
				fam.add(huesped);
				grupos.put(huesped.getIDgrupo(), fam);
			} else {
				ArrayList<Huesped> fam = grupos.get(huesped.getIDgrupo());
				fam.add(huesped);
				grupos.put(huesped.getIDgrupo(), fam);
			}
		}
		scan.close();
	}

	private static String textoGuardarHuespeds() {
		String texto = "";
		boolean primero = true;
		for (Huesped huesped : huespeds) {
			if (primero) {
				primero = false;
			} else {
				texto += "\n";
			}
			texto += huesped.getNombre() + ";" + String.valueOf(huesped.getEdad()) + ";" + huesped.getID() + ";"
					+ String.valueOf(huesped.getIDgrupo());
		}
		return texto;
	}

	public static void cancelarReservanteYGrupo(Reservante reservante) {
		int IDGrupo = reservante.getIDgrupo();
		ArrayList<Huesped> grupitoPersonas = grupos.get(IDGrupo);
		reservantes.remove(reservante);
		for (Huesped huesped : grupitoPersonas) {
			huespeds.remove(huesped);
		}
		grupos.remove(IDGrupo);
	}

	public static int cancelarReserva(String ID, String fechaActual) {
		int sePuede = 1;
		if (existeReservante(ID)) {
			Reservante reservante = getReservantePerID(ID);
			ArrayList<String> habitaciones = reservante.getIDHabitaciones();
			boolean sePuedeCancelar = true;
			for (String IDHabitacion : habitaciones) {
				Habitacion habitacionRealG = Inventario.getHabitacionPerID(IDHabitacion);
				if (!(habitacionRealG.sePuedeCancelar(fechaActual, reservante))) {
					sePuedeCancelar = false;
				} else {
					habitacionRealG.cancelarReserva(reservante);
				}
			}
			if (sePuedeCancelar) {
				sePuede = 1;
				cancelarReservanteYGrupo(reservante);
			} else {
				sePuede = -1;
			}
		} else {
			sePuede = 0;
		}

		return sePuede;
	}

	private static void cargarReservantes() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/reservasRegistroFactura/" + "reservas.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			// [nombre;edad;ID;correo;númeroCelular;acompañantes;familiaID;fechaInicio;fechaFinal;ID-Habitacion]
			String nombre = partes[0];
			int edad = Integer.parseInt(partes[1]);
			String ID = partes[2];
			String correo = partes[3];
			Long numeroCelular = Long.parseLong(partes[4]);
			int acompanante = Integer.parseInt(partes[5]);
			int IDGrupo = Integer.parseInt(partes[6]);
			String fechaInicio = partes[7];
			String fechaFinal = partes[8];
			String IDHabitacion = partes[9];
			Reservante reservante = null;
			if (existeReservante(ID)) {
				reservante = getReservantePerID(ID);
				reservante.agregarHabitacion(IDHabitacion);
			} else {
				reservante = new Reservante(nombre, edad, ID, correo, numeroCelular, acompanante,
						IDGrupo,
						fechaInicio, fechaFinal);
				reservante.agregarHabitacion(IDHabitacion);
				reservantes.add(reservante);
				Habitacion habitacion = Inventario.getHabitacionPerID(IDHabitacion);
				habitacion.hacerReserva(fechaFinal, fechaFinal, huespeds);
				if (!(grupos.containsKey(reservante.getIDgrupo()))) {
					ArrayList<Huesped> fam = new ArrayList<Huesped>();
					fam.add(reservante);
					grupos.put(reservante.getIDgrupo(), fam);
				} else {
					ArrayList<Huesped> fam = grupos.get(reservante.getIDgrupo());
					fam.add(reservante);
					grupos.put(reservante.getIDgrupo(), fam);
				}
			}
		}
		hacerReservasAdentroHabitacion();
		scan.close();
	}

	private static void hacerReservasAdentroHabitacion() {
		for (Reservante reservante : reservantes) {
			ArrayList<String> IDHabitaciones = reservante.getIDHabitaciones();
			int IDGrupo = reservante.getIDgrupo();
			String fechaInicial = reservante.getFechaInicio();
			String fechaFinal = reservante.getFechaFin();
			ArrayList<Huesped> grupito = grupos.get(IDGrupo);
			for (String IDHabitacion : IDHabitaciones) {
				Habitacion habitacion = Inventario.getHabitacionPerID(IDHabitacion);
				habitacion.hacerReserva(fechaInicial, fechaFinal, grupito);
			}
		}
	}

	private static boolean existeReservante(String ID) {
		boolean encontrado = false;
		for (Reservante reservante : reservantes) {
			if (reservante.getID().equals(ID)) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	private static Reservante getReservantePerID(String ID) {
		Reservante encontrado = null;
		for (Reservante reservante : reservantes) {
			if (reservante.getID().equals(ID)) {
				encontrado = reservante;
			}
		}
		return encontrado;
	}

	private static String textoGuardaReservantes() {
		String texto = "";
		boolean primero = true;
		for (Reservante reservante : reservantes) {
			if (primero) {
				primero = false;
			} else {
				texto += "\n";
			}
			String nombre = reservante.getNombre();
			int edad = reservante.getEdad();
			String ID = reservante.getID();
			String correo = reservante.getCorreo();
			Long numeroCelular = reservante.getNumCell();
			int acompanante = reservante.getAcompanantes();
			int IDGrupo = reservante.getIDgrupo();
			String fechaInicio = reservante.getFechaInicio();
			String fechaFinal = reservante.getFechaFin();
			ArrayList<String> IDHabitaciones = reservante.getIDHabitaciones();
			boolean primeroAdentro = true;
			for (String IDHabitacion : IDHabitaciones) {
				if (primeroAdentro) {
					primeroAdentro = false;
				} else {
					texto += "\n";
				}
				texto += nombre + ";" + edad + ";" + ID + ";" + correo + ";" + numeroCelular + ";" + acompanante + ";"
						+ IDGrupo + ";" + fechaInicio + ";" + fechaFinal + ";" + IDHabitacion;
			}
		}
		return texto;
	}

	public static boolean checkOut(String IDReservante) {
		// bueno, vamo' a trabajar
		boolean vamosBien = true;
		if (existeReservante(IDReservante)) {
			Reservante reservante = getReservantePerID(IDReservante);
			ArrayList<String> IDhabitaciones = reservante.getIDHabitaciones();
			int IDgrupo = reservante.getIDgrupo();
			ArrayList<Huesped> personitas = grupos.get(IDgrupo);
			for (String IDHabitacion : IDhabitaciones) {
				if (registroServicios.containsKey(IDHabitacion)) {
					ArrayList<Servicio> serviciosDelUsuario = registroServicios.get(IDHabitacion);
					if (serviciosDelUsuario.size() != 0) {
						for (Servicio servicio : serviciosDelUsuario) {
							if (!(servicio.isPagado())) {
								vamosBien = false;
							}
						}
					}
				}
			}
			for (Huesped personita : personitas) {
				String IDpersonita = personita.getID();
				if (registroServicios.containsKey(IDpersonita)) {
					ArrayList<Servicio> serviciosDelUsuario = registroServicios.get(IDpersonita);
					if (serviciosDelUsuario.size() != 0) {
						for (Servicio servicio : serviciosDelUsuario) {
							if (!(servicio.isPagado())) {
								vamosBien = false;
							}
						}
					}
				}
			}
			if (vamosBien) {
				grupos.remove(IDgrupo);
				for (String IDHabitacion : IDhabitaciones) {
					Habitacion habitacion = Inventario.getHabitacionPerID(IDHabitacion);
					habitacion.cancelarReserva(reservante);
				}
				reservantes.remove(reservante);
				for (Huesped personita : personitas) {
					huespeds.remove(personita);
				}
			}

		} else {
			vamosBien = false;
		}
		return vamosBien;
	}
}

class HiloMensaje extends Thread {

	public void run() {
		// Imprimimos un mensaje cada segundo durante 5 segundos
		for (int i = 1; i <= Servicios.acompanantesActual; i++) {
			System.out.println("Acompañante num: " + i + " añadido.");
			try {
				Servicios.segui = false;
				new PreguntarAcompanante();
				while (!Servicios.segui) {
					Thread.sleep(500); // Esperamos 0.5 segundo antes de imprimir el siguiente mensaje
				}
			} catch (InterruptedException e) {
			}
		}

	}
}