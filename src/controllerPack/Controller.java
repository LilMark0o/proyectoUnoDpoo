package controllerPack;

import loginApp.Usuario;
import serviciosPack.Servicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import console.CambiarTarifa;
import console.InventarioFrame;
import inventarioPack.Habitacion;
import inventarioPack.Inventario;
import loginApp.Login;

public class Controller {
	private static Usuario usuario;

	public static String devolverEmpleo() {
		return usuario.getRol();
	}

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

	public static void consultarHabitación(String ID) {
		if (Inventario.idYaExiste(ID)) {
			Habitacion habitacion = Inventario.habitacionPorID(ID);
			if (habitacion.equals(null)) {
				CambiarTarifa.showErrorFrame("Tuvimos un problema encontrando la habitación");
			} else {
				String textoHabitacion = habitacion.toString();
				String[] partes = textoHabitacion.split(", ");
				InventarioFrame.showInfoFrameLargo(partes);
			}
		} else {
			InventarioFrame.showInfoFrame("El ID dado no existe, vuelva a intentarlo con un ID nuevo");
		}
	}

	public static String cambiarTarifa(String tipoHabitacion, String initialDate, String finalDate, String days,
			int tarifaNum) {
		if (usuario.getRol().equals("administracion")) {
			Inventario.cambiarTarifa(tipoHabitacion, initialDate, finalDate, days, tarifaNum);
			return "¡Cambio exitoso!";
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static void cambiarMenu(String nombreMenu, String initialHour, String finalHour, String aCuarto,
			int tarifaNum) {
		if (usuario.getRol().equals("administracion")) {
			Inventario.cambiarMenu(nombreMenu, initialHour, finalHour, aCuarto, tarifaNum);
			InventarioFrame.showInfoFrame("¡Producto cambiado/Creado!");
		} else {
			CambiarTarifa.showErrorFrame("Tuvimos un problema cambiando el menú");
		}
	}

	public static void cambiarServicios(String nombreServicio, String cantidadPersonas, int precio) {
		if (usuario.getRol().equals("administracion")) {
			Inventario.cambiarServicio(nombreServicio, cantidadPersonas, precio);
			InventarioFrame.showInfoFrame("Servicio cambiado/Creado!");
		} else {
			CambiarTarifa.showErrorFrame("Tuvimos un problema cambiando el menú");
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

	public static String checkOut(String IDReservante) throws IOException {
		if (usuario.getRol().equals("recepcion")) {
			boolean sePuede = Servicios.checkOut(IDReservante);
			if (sePuede) {
				return "Check-out hecho exitosamente, ¡Vuelva pronto!";
			} else {
				return "No se puede realizar el Check-out, revise si todos sus servicios están pagos, o si los ID está bien escrito";
			}
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	// ! Reservas
	public static String generarReserva(String nombreReservante, int edad, String IDReservante,
			String correoReservante, Long numeroCelular, int cantidadAcompanantes, String fechaInicial,
			String fechaFinal, String tipoDeHabitacion) {
		if (usuario.getRol().equals("recepcion")) {
			return Servicios.generarReserva(nombreReservante, edad, IDReservante,
					correoReservante, numeroCelular,
					cantidadAcompanantes, fechaInicial, fechaFinal, tipoDeHabitacion);
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static int chismosearPrecio(String tipoHabitacion, String initialDate, String finalDate) {
		int costo = Inventario.tarifaAPagar(tipoHabitacion, initialDate, finalDate);
		return costo;
	}

	public static String chismosearPrecioLargo(String tipoHabitacion, String initialDate, String finalDate) {
		if (usuario.getRol().equals("recepcion")) {
			int costo = Inventario.tarifaAPagar(tipoHabitacion, initialDate, finalDate);
			if (costo == -1) {
				return "No hay tarifas disponibles para estas fechas, lo sentimos :(";
			} else {
				return "El monto a pagar sería: $" + String.valueOf(costo);
			}
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static String cancelarReserva(String ID, String fechaActual) {
		if (usuario.getRol().equals("recepcion")) {
			if (Servicios.cancelarReserva(ID, fechaActual) == 1) {
				return "la reserva se canceló exitosamente";
			} else if (Servicios.cancelarReserva(ID, fechaActual) == 0) {
				return "No se encontró el ID ingresado";
			} else {
				return "Las reservas no se pueden cancelar en las últimas 48 horas";
			}
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
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
