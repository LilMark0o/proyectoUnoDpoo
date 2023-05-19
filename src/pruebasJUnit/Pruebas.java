package pruebasJUnit;

import controllerPack.Controller;
import serviciosPack.Huesped;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

public class Pruebas {
	@BeforeEach
	public void setUp() {
		// por ahora nada jeje
		try {
			Controller.startApp();
		} catch (FileNotFoundException e) {
			fail("Hubo un error haciendo el setUp");
		}
	}

	private void LogInAdmin() {
		Controller.logIn("hola@administracion.com", "teAmoMas");
	}

	private void LogInRecepcion() {
		Controller.logIn("hola@recepcion.com", "teAmoMuack");
	}

	private void LogInRestaurante() {
		Controller.logIn("jairoMoreno@restaurante.com", "2323435Col");
	}

	@Test
	public void testLogInAdministracion() {
		try {
			setUp();
			LogInAdmin();
			assertEquals("No coincide el rol", Controller.devolverEmpleo(), "administracion");
			// Controller.cargarHabitacionArchivo("habitacionesNuevas.txt");
		} catch (Exception e) {
			System.out.println(e);
			fail("Hubo un error");
		}
	}

	@Test
	public void testLogInRecepcion() {
		try {
			setUp();
			LogInRecepcion();
			assertEquals("No coincide el rol", Controller.devolverEmpleo(), "recepcion");
			// Controller.cargarHabitacionArchivo("habitacionesNuevas.txt");
		} catch (Exception e) {
			System.out.println(e);
			fail("Hubo un error");
		}
	}

	@Test
	public void testLogInRestaurante() {
		try {
			setUp();
			LogInRestaurante();
			assertEquals("No coincide el rol", Controller.devolverEmpleo(), "restaurante");
			// Controller.cargarHabitacionArchivo("habitacionesNuevas.txt");
		} catch (Exception e) {
			System.out.println(e);
			fail("Hubo un error");
		}
	}

	// ! acá estarán las pruebas unitarias sobre las funcionalidades para la carga
	// ! de archivos

	@Test
	public void testFileChargeSuccessfully() {
		try {
			setUp();
			LogInAdmin();
			Controller.cargarHabitacionArchivo("habitacionesNuevas.txt");
		} catch (Exception e) {
			System.out.println(e);
			fail("Hubo un error");
		}
	}

	@Test
	public void testFileChargeWrong() {
		try {
			setUp();
			LogInAdmin();
			Controller.cargarHabitacionArchivo("archivoQueNoExiste.txt");
			fail("Hubo un error");
			// ! el error se pone adentro, debido a que este archivo no existe, por ende
			// ! la prueba debería fallar
		} catch (Exception e) {
		}
	}

	// ! funcionalidades relacionadas con la creación de reservas

	@Test
	public void testCreacionReservaSuccessfully() {
		try {
			setUp();
			LogInRecepcion();

			// datos prueba
			String NombreReservante = "Jalapeño";
			int edadReservante = 23;
			String IDReservante = "1023014093";
			String CorreoReservante = "JalapeñoChill@gmail.com";
			Long CelularReservante = (long) 310230485;
			int Acompanantes = 3;
			String desde = "2023-03-03";
			String hasta = "2023-03-08";
			String tipo = "estándar";
			ArrayList<Huesped> personitasImportadas = new ArrayList<Huesped>();
			Huesped persona = new Huesped("Godofredo", 92, "128932198", 100);
			personitasImportadas.add(persona);
			Huesped persona2 = new Huesped("José Afanador", 34, "81238", 100);
			personitasImportadas.add(persona2);
			Huesped persona3 = new Huesped("Patricio Estreella", 12, "84393", 100);
			personitasImportadas.add(persona3);
			// fin datos prueba

			Controller.generarReserva(NombreReservante, edadReservante, IDReservante,
					CorreoReservante, CelularReservante, Acompanantes, desde, hasta, tipo, personitasImportadas);
			assertEquals("No existe la persona que reservó", Controller.existeReservante(IDReservante),
					true);

		} catch (Exception e) {
			System.out.println(e);
			fail("Hubo un error");
		}
	}

	@Test
	public void testCreacionReservaWrongPorTrabajador() {
		try {
			setUp();
			LogInRestaurante(); // ! un trabajador del restaurante no puede reservar
			// datos prueba
			String NombreReservante = "Jalapeño";
			int edadReservante = 23;
			String IDReservante = "1023014093";
			String CorreoReservante = "JalapeñoChill@gmail.com";
			Long CelularReservante = (long) 310230485;
			int Acompanantes = 3;
			String desde = "2023-03-03";
			String hasta = "2023-03-08";
			String tipo = "estándar";
			ArrayList<Huesped> personitasImportadas = new ArrayList<Huesped>();
			Huesped persona = new Huesped("Godofredo", 92, "128932198", 100);
			personitasImportadas.add(persona);
			Huesped persona2 = new Huesped("José Afanador", 34, "81238", 100);
			personitasImportadas.add(persona2);
			Huesped persona3 = new Huesped("Patricio Estreella", 12, "84393", 100);
			personitasImportadas.add(persona3);
			// fin datos prueba

			Controller.generarReserva(NombreReservante, edadReservante, IDReservante,
					CorreoReservante, CelularReservante, Acompanantes, desde, hasta, tipo, personitasImportadas);

			// ! el error se pone adentro, debido a que este empleado no puede hacer
			// ! reservas, por ende la prueba debería fallar
			assertEquals("Existe la persona que reservó", Controller.existeReservante(IDReservante),
					false);

		} catch (Exception e) {
		}
	}

	@Test
	public void testCreacionReservaWrongMalosDatos() {
		try {
			setUp();
			LogInRecepcion();
			// datos prueba
			String NombreReservante = "Jalapeño";
			int edadReservante = 23;
			String IDReservante = "1023014093";
			String CorreoReservante = "JalapeñoChill@gmail.com";
			Long CelularReservante = (long) 310230485;
			int Acompanantes = 3; // ! se declaran 3 acompañantes pero no hay (el arraylist de abajo
									// ! está vacío), por ende también es un error
			String desde = "2019-03-03"; // ! fechas inhabilitadas e imposibles
			String hasta = "2018-03-08";
			String tipo = "estándar";
			ArrayList<Huesped> personitasImportadas = new ArrayList<Huesped>();
			// fin datos prueba

			Controller.generarReserva(NombreReservante, edadReservante, IDReservante,
					CorreoReservante, CelularReservante, Acompanantes, desde, hasta, tipo, personitasImportadas);

			// ! el error se pone adentro, debido a que estos datos no pueden hacer
			// ! una reserva, por ende la prueba debería fallar
			assertEquals("Existe la persona que reservó", Controller.existeReservante(IDReservante),
					false);

		} catch (Exception e) {
		}
	}

}
