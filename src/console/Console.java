package console;

import java.io.IOException;

import controllerPack.Controller;
import inventarioPack.Inventario;
import loginApp.Login;

public class Console {
	private static Boolean logedIn;

	public static void main(String[] args) throws IOException {
		Controller.startApp();
		logedIn = false;
		System.out.println("Hotel Property Managament System\n");
		boolean continuar = true;
		while (continuar) {
			try {
				mostrarOpciones();
				int opcion_seleccionada = Integer.parseInt(Controller.input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					logInMenu();
				} else if (opcion_seleccionada == 2) {
					inventarioMenu();
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

	public static void logInMenu() throws IOException {
		boolean continuarEnOpción = true;
		while (continuarEnOpción) {
			try {
				logInOptions();
				int opcion_seleccionada = Integer.parseInt(Controller.input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					String userName = Controller.input("Correo de ingreso: ");
					String password = Controller.input("Contraseña: ");
					if (Controller.logIn(userName, password)) {
						System.out.println("¡Se inició sesión exitosamente!");
						logedIn = true;
					} else {
						System.out.println("Hubo un error iniciando sesión.");
						logedIn = false;
					}
				} else if (opcion_seleccionada == 2) {
					if (logedIn == true) {
						Controller.logOut();
					} else {
						System.out.println("Debes iniciar sesión primero");
					}
				} else if (opcion_seleccionada == 3) {
					if (logedIn == true) {
						String newPassword = Controller.input("Nueva contraseña: ");
						Controller.cambiarContrasena(newPassword);
					} else {
						System.out.println("Debes iniciar sesión primero");
					}
				} else if (opcion_seleccionada == 4) {
					Login.guardarCambios();
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

	public static void inventarioMenu() throws IOException {
		boolean continuarEnOpción = true;
		while (continuarEnOpción) {
			try {
				inventarioOptions();
				int opcion_seleccionada = Integer.parseInt(Controller.input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					// cargar Archivo
					if (logedIn == true) {
						String fileName = Controller.input("¿Cúal es el nombre del archivo? ");
						System.out.println(Controller.cargarHabitacionArchivo(fileName));
					} else {
						System.out.println("Debes iniciar sesión primero");
					}

				} else if (opcion_seleccionada == 2) {
					// cargar Individual
					if (logedIn == true) {
						String tipoHabitacion = Controller.input(
								"¿Qué tipo de habitación quiere? (estándar, suite, suite doble) ");
						String ID = Controller.input("¿Qué ID quiere darle a la habitación? ");
						String ubicacion = Controller
								.input("¿Qué ubicación quiere darle a la habitación? (ej: Torre 2) ");
						String tieneBalcon = Controller.input("¿La habitación tiene balcón? (Sí o No) ");
						String tieneVista = Controller.input("¿La habitación tiene balcón? (Sí o No) ");
						String tieneCocina = Controller.input("¿La habitación tiene balcón? (Sí o No) ");
						int capacidadAdulto = Integer
								.parseInt(Controller.input("¿Cúal es la capacidad de adultos de la habitación?"));
						int capacidadNino = Integer
								.parseInt(Controller.input("¿Cúal es la capacidad de niños de la habitación?"));

						System.out.println(Controller.cargarHabitacionManual(tipoHabitacion, ID, ubicacion,
								tieneBalcon, tieneVista, tieneCocina, capacidadAdulto, capacidadNino));
					} else {
						System.out.println("Debes iniciar sesión primero");
					}
				} else if (opcion_seleccionada == 3) {
					// consultar Habitación
					if (logedIn == true) {
						String ID = Controller.input("¿Qué ID de habitación desea buscar?: ");
						System.out.println(Controller.consultarHabitación(ID));
					} else {
						System.out.println("Debes iniciar sesión primero");
					}
				} else if (opcion_seleccionada == 4) {
					// cambiar tarifa :(
					if (logedIn == true) {
						String tipoHabitacion = Controller
								.input("¿Desde tipo de habitación quiere cambiar? (estándar, suite, suite doble): ");
						String initialDate = Controller.input("¿Desde qué fecha? (ej:2023-03-23): ");
						String finalDate = Controller.input("¿Hasta qué fecha? (ej:2023-03-23): ");
						String days = Controller.input("¿Qué días? (en minúscula y con tilde): ");
						int tarifaNum = Integer
								.parseInt(Controller.input("¿Cúal es la tarifa a aplicar?"));

						System.out.println(Controller.cambiarTarifa(
								tipoHabitacion, initialDate, finalDate, days, tarifaNum));
					} else {
						System.out.println("Debes iniciar sesión primero");
					}
				} else if (opcion_seleccionada == 7) {
					Inventario.guardarCambios();
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
	public static void inventarioOptions() {
		System.out.println("\nOpciones respecto al inventario del hotel\n");
		System.out.println("1. Cargar Habitación (Cargar Archivo)");
		System.out.println("2. Cargar Habitación (Individual)");
		System.out.println("3. Consultar Habitación (Por ID)");
		System.out.println("4. Cambiar Tarifa");
		System.out.println("5. Cambiar Menú");
		System.out.println("6. Cambiar Servicio");
		System.out.println("7. Salir del menú\n");
	}

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
		System.out.println("2. Opciones del Inventario");
		System.out.println("3. Hacer un pedido");
		System.out.println("4. Terminar el pedido e imprimir factura");
		System.out.println("5. Salir de la aplicación\n");
	}

}
