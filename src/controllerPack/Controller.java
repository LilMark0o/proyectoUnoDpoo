package controllerPack;

import loginApp.Usuario;

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

	public static String cargarHabitacionArchivo() throws FileNotFoundException {
		if (usuario.getRol().equals("administracion")) {
			String fileName = input("¿Cúal es el nombre del archivo? ");
			Inventario.cargarHabitaciones(fileName);
			return "¡Cargado exitosamente!";
		} else {
			return "Sólo un empleado de administración puede llevar a cabo esta acción";
		}
	}

	public static String cargarHabitacionManual() throws FileNotFoundException {
		if (usuario.getRol().equals("administracion")) {

			String tipoHabitacion = input("¿Qué tipo de habitación quiere? (estándar, suite, suite doble) ");
			String ID = input("¿Qué ID quiere darle a la habitación? ");
			String ubicacion = input("¿Qué ubicación quiere darle a la habitación? (ej: Torre 2) ");
			String tieneBalcon = input("¿La habitación tiene balcón? (Sí o No) ");
			String tieneVista = input("¿La habitación tiene balcón? (Sí o No) ");
			String tieneCocina = input("¿La habitación tiene balcón? (Sí o No) ");
			int capacidadAdulto = Integer.parseInt(input("¿Cúal es la capacidad de adultos de la habitación?"));
			int capacidadNino = Integer.parseInt(input("¿Cúal es la capacidad de niños de la habitación?"));
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
