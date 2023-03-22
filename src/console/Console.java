package console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import controllerPack.Controller;
import loginApp.Login;

public class Console {
	private static Boolean logedIn;

	public static void main(String[] args) throws FileNotFoundException {
		Login.cargarLogIn();
		logedIn = false;
		System.out.println("Hotel Property Managament System\n");
		boolean continuar = true;
		while (continuar) {
			try {
				mostrarOpciones();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					logInMenu();
				} else if (opcion_seleccionada == 2) {

				} else if (opcion_seleccionada == 3) {

				} else if (opcion_seleccionada == 4) {

				} else if (opcion_seleccionada == 5) {
					System.out.println("Chaolin pin pín	");
					continuar = false;
				} else {
					System.out.println("Por favor seleccione una opción válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	public static void logInMenu() {
		boolean continuarEnOpción = true;
		while (continuarEnOpción) {
			try {
				logInOptions();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					String userName = input("Correo de ingreso: ");
					String password = input("Contraseña: ");
					if (Controller.logIn(userName, password)) {
						System.out.println("¡Se inició sesión exitosamente!");
						logedIn = true;
					} else {
						System.out.println("Hubo un error iniciando sesión.");
						logedIn = false;
					}

				} else if (opcion_seleccionada == 2) {
					if(logedIn!=true) {
						Controller.logOut();
					}else {
						System.out.println("Debes iniciar sesión primero");
					}
				} else if (opcion_seleccionada == 3) {
					if(logedIn!=true) {
						String newPassword = input("Nueva contraseña: ");
						Controller.cambiarContrasena(newPassword);
					}else {
						System.out.println("Debes iniciar sesión primero");
					}
				} else if (opcion_seleccionada == 4) {
					System.out.println("Chaolin pin pín	");
					continuarEnOpción = false;
				} else {
					System.out.println("Por favor seleccione una opción válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	// EN ESTA PARTE IRAN TODAS LAS FUNCIONES DE MENÚ U OPERACIONALES
	public static void logInOptions() {
		System.out.println("\nOpciones respecto al ingreso al sistema\n");
		System.out.println("1. Log-in");
		System.out.println("2. Log-out");
		System.out.println("3. Cambio de contraseña");
		System.out.println("4. Salir del menú\n");
	}

	public static void mostrarOpciones() {
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Opciones del Log-in, Log-out, cambio de contraseña");
		System.out.println("2. Mostrar Datos");
		System.out.println("3. Hacer un pedido");
		System.out.println("4. Terminar el pedido e imprimir factura");
		System.out.println("5. Salir de la aplicación\n");
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
