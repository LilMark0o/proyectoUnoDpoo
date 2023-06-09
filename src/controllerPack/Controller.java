package controllerPack;

import loginApp.Usuario;
import serviciosPack.Huesped;
import serviciosPack.Servicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import console.CambiarTarifa;
import console.InventarioFrame;
import console.PagoReserva;
import inventarioPack.Habitacion;
import inventarioPack.Inventario;
import loginApp.Login;

public class Controller {
	private static Usuario usuario;

	public static void guardarCambios() {
		try {
			Inventario.guardarCambios();
			Servicios.guardarCambios();
			Login.guardarCambios();
		} catch (IOException e) {
		}
	}

	public static String devolverEmpleo() {
		return usuario.getRol();
	}

	public static String devolverCorreo() {
		return usuario.getLogIn();
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

	public static boolean logInHuesped(String userName, String password) {
		usuario = Login.logInHuesped(userName, password);
		if (usuario != null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean crearUsuarioHuesped(String userName, String password) {
		if (Login.canCreateUser(userName, password)) {
			Login.createUser(userName, password);
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<String> leerPlataformas() throws FileNotFoundException {
		return Inventario.leerPlataformas();
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
			String tieneVista, String tieneCocina, int capacidadAdulto, int capacidadNino, int tamano,
			String aireAcondicionado,
			String calefaccion, int tamanoCama,
			String TV,
			String cafetera,
			String ropaDeCamayTapetesHipo,
			String plancha, String secadoraDePelo,
			double voltajeAC,
			String tomaUSB_A, String tomaUSB_C, String incluyeDesayuno)
			throws FileNotFoundException {

		if (usuario.getRol().equals("administracion")) {
			Habitacion habitacionNueva = new Habitacion(tipoHabitacion, ID, ubicacion, tieneBalcon, tieneVista,
					tieneCocina, capacidadAdulto, capacidadNino,
					tamano, aireAcondicionado, calefaccion, tamanoCama, TV, cafetera, ropaDeCamayTapetesHipo,
					plancha, secadoraDePelo, voltajeAC, tomaUSB_A, tomaUSB_C, incluyeDesayuno);
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
				InventarioFrame.showInfoFrameLargo(partes, 400);
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

	public static void cargarServicio(String ID, String nombreServicio, String pagado) {
		Servicios.registrarServicio(ID, nombreServicio, pagado);
	}

	public static int cargarServicioHabitacion(String ID, String pagado, String desde, String hasta) {
		return Servicios.registrarServicioHabitacion(ID, pagado, desde, hasta);
	}

	public static void cargarServicioRestaurante(String ID, String nombreServicio, String pagado) {
		Servicios.registrarServicioRestaurante(ID, nombreServicio, pagado);
	}

	public static String pagarServicio(String ID, String nombreServicio) {
		Servicios.pagarServicio(ID, nombreServicio);
		return "";
	}

	public static String mostrarMenu() {
		String txt = Inventario.mostrarMenu();
		return txt;
	}

	public static String mostrarServicios() {
		String txt = Inventario.mostrarServicios();
		return txt;
	}

	public static boolean existeReservante(String ID) {
		return Servicios.existeReservante(ID);
	}

	public static String generarFactura(String ID) throws IOException {
		String txt = Servicios.generarFactura(ID);
		return txt;
	}

	public static String checkOut(String IDReservante) throws IOException {
		if (usuario.getRol().equals("recepcion")) {
			boolean sePuede = Servicios.checkOut(IDReservante);
			if (sePuede) {
				CambiarTarifa.showSuccessFrame("Check-out hecho exitosamente, ¡Vuelva pronto!");
				return "Check-out hecho exitosamente, ¡Vuelva pronto!";
			} else {
				// !ahora vamos a ponerlo a pagar >:]
				// CambiarTarifa.showErrorFrame("No se puede realizar el Check-out");
				int cobroAHacer = Servicios.cuentaCobro(IDReservante);
				serviciosPack.Reservante reservante = Servicios.getReservantePerID(IDReservante);

				PagoReserva ventana = new PagoReserva(reservante.getNombre(), IDReservante, reservante.getFechaInicio(),
						reservante.getFechaFin());
				ventana.cobroAHacer = cobroAHacer;
				return "No se puede realizar el Check-out, revise si todos sus servicios están pagos, o si los ID está bien escrito";
			}
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	// ! Reservas
	public static String generarReserva(String nombreReservante, int edad, String IDReservante,
			String correoReservante, Long numeroCelular, int cantidadAcompanantes, String fechaInicial,
			String fechaFinal, String tipoDeHabitacion, ArrayList<Huesped> acompanantes) {
		if (usuario.getRol().equals("recepcion")) {
			try {
				return Servicios.generarReserva(nombreReservante, edad, IDReservante,
						correoReservante, numeroCelular,
						cantidadAcompanantes, fechaInicial, fechaFinal, tipoDeHabitacion, acompanantes);
			} catch (InterruptedException e) {
			}
			return "";
		} else {
			CambiarTarifa.showErrorFrame("Sólo un empleado de recepción puede llevar a cabo esta acción");
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static String generarReservaPorHuesped(String nombreReservante, int edad, String IDReservante,
			String correoReservante, Long numeroCelular, int cantidadAcompanantes, String fechaInicial,
			String fechaFinal, String tipoDeHabitacion, ArrayList<Huesped> acompanantes) {
		try {
			return Servicios.generarReservaPorHuesped(nombreReservante, edad, IDReservante,
					correoReservante, numeroCelular,
					cantidadAcompanantes, fechaInicial, fechaFinal, tipoDeHabitacion, acompanantes);
		} catch (InterruptedException e) {
		}
		return "";
	}

	public static int chismosearPrecio(String tipoHabitacion, String initialDate, String finalDate) {
		int costo = Inventario.tarifaAPagar(tipoHabitacion, initialDate, finalDate);
		return costo;
	}

	public static String chismosearPrecioLargo(String tipoHabitacion, String initialDate, String finalDate) {
		if (usuario.getRol().equals("recepcion")) {
			int costo = Inventario.tarifaAPagar(tipoHabitacion, initialDate, finalDate);
			if (costo == -1) {
				CambiarTarifa.showErrorFrame("No hay tarifas disponibles para estas fechas");
				return "No hay tarifas disponibles para estas fechas, lo sentimos :(";
			} else {
				CambiarTarifa.showSuccessFrame("El monto a pagar sería: $" + String.valueOf(costo));
				return "El monto a pagar sería: $" + String.valueOf(costo);
			}
		} else {
			CambiarTarifa.showErrorFrame("Sólo un empleado de recepción puede llevar a cabo esta acción");
			return "Sólo un empleado de recepción puede llevar a cabo esta acción";
		}
	}

	public static String cancelarReserva(String ID, String fechaActual) {
		if (usuario.getRol().equals("recepcion")) {
			if (Servicios.cancelarReserva(ID, fechaActual) == 1) {
				CambiarTarifa.showSuccessFrame("la reserva se canceló exitosamente");
				return "la reserva se canceló exitosamente";
			} else if (Servicios.cancelarReserva(ID, fechaActual) == 0) {
				CambiarTarifa.showErrorFrame("No se encontró el ID ingresado");
				return "No se encontró el ID ingresado";
			} else {
				CambiarTarifa.showErrorFrame("No se pueden cancelar en las últimas 48 horas");
				return "Las reservas no se pueden cancelar en las últimas 48 horas";
			}
		} else {
			CambiarTarifa.showErrorFrame("Sólo un empleado de recepción puede llevar a cabo esta acción");
			return "Sólo un empleado de recepción puede llevar a cabo esta acción";
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
