package controllerPack;

import loginApp.Usuario;
import serviciosPack.Servicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import inventarioPack.Habitacion;
import inventarioPack.Inventario;
import loginApp.Login;

public class Controller {
	private static Usuario usuario;

	public static void startApp() throws FileNotFoundException {
		Login.cargarLogIn();
		Inventario.cargarInventario();
		Servicios.cargarServicios();
	}

	public static boolean logIn(String userName, String password) {
		usuario = Login.logIn(userName, password);
		if (usuario != null) {
			return true;
		} else {
			return false;
		}
	}

	public static void logOut() {
		usuario = null;
	}

	public static String cambiarContrasena(String newPassword) {
		String resp = Login.changePassword(usuario, newPassword);
		return resp;
	}

	public static String cargarHabitacionArchivo(String fileName) throws FileNotFoundException {
		if (usuario.getRol().equals("administracion")) {
			Inventario.cargarHabitaciones(fileName);
			return "¡Cargado exitosamente!";
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static String cargarHabitacionManual(String tipoHabitacion, String ID, String ubicacion, String tieneBalcon,
			String tieneVista, String tieneCocina, int capacidadAdulto, int capacidadNino)
			throws FileNotFoundException {

		if (usuario.getRol().equals("administracion")) {
			Habitacion habitacionNueva = new Habitacion(tipoHabitacion, ID, ubicacion, tieneBalcon, tieneVista,
					tieneCocina, capacidadAdulto, capacidadNino);
			if (Inventario.idYaExiste(ID)) {
				return "El ID dado ya existe, vuelva a intentarlo con un ID nuevo";
			} else {
				Inventario.meterHabitacion(habitacionNueva);
				return "¡Habitación cargada exitosamente!";
			}
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static String consultarHabitación(String ID) {
		if (Inventario.idYaExiste(ID)) {
			Habitacion habitacion = Inventario.habitacionPorID(ID);
			if (habitacion.equals(null)) {
				return "Tuvimos un problema encontrando la habitación";
			} else {
				return habitacion.toString();
			}
		} else {
			return "El ID dado no existe, vuelva a intentarlo con un ID nuevo";
		}
	}

	public static String cambiarTarifa(String tipoHabitacion, String initialDate, String finalDate, String days,
			int tarifaNum) {
		if (usuario.getRol().equals("administracion")) {
			if (Inventario.faltaAlgunaTarifa()) {
				System.out.println(
						"Hay alguna fecha dentro de los próximos 365 días en la que no exista una tarifa asignada para un cierto tipo de habitación.");
			}
			Inventario.cambiarTarifa(tipoHabitacion, initialDate, finalDate, days, tarifaNum);
			return "¡Cambio exitoso!";
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static String cargarServicio(String ID, String nombreServicio, String pagado) {
		String resp = Servicios.registrarServicio(ID, nombreServicio, pagado);
		return resp;
	}

	public static String cargarServicioRestaurante(String ID, String nombreServicio, String pagado) {
		String resp = Servicios.registrarServicioRestaurante(ID, nombreServicio, pagado);
		return resp;
	}

	public static String pagarServicio(String ID, String nombreServicio) {
		String resp = Servicios.pagarServicio(ID, nombreServicio);
		return resp;
	}

	public static String mostrarMenu() {
		String txt = Inventario.mostrarMenu();
		return txt;
	}

	public static String mostrarServicios() {
		String txt = Inventario.mostrarServicios();
		return txt;
	}

	public static String generarFactura(String ID) throws IOException {
		String txt = Servicios.generarFactura(ID);
		return txt;
	}

	public static String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
