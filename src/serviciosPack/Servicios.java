package serviciosPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import inventarioPack.Inventario;
import inventarioPack.Servicio;
import inventarioPack.ServicioRestaurante;

public class Servicios {
	private static HashMap<String, ArrayList<Servicio>> registroServicios;

	public static void cargarServicios() throws FileNotFoundException {
		registroServicios = new HashMap<String, ArrayList<Servicio>>();
		cargarRegistroServicios();
	}

	public static String registrarServicio(String ID, String servicioNombre, String pagadoEnElMomento) {
		Servicio servicioBuscado = Inventario.buscarServicio(servicioNombre);
		if (servicioBuscado.equals(null)) {
			return "No se ha encontrado ese servicio, intente con otro nombre";
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
			return "Servicio encontrado y cargado exitosamente al usuario";
		}
	}

	public static String registrarServicioRestaurante(String ID, String servicioNombre, String pagadoEnElMomento) {
		ServicioRestaurante servicioBuscado = Inventario.buscarServicioRestauraServicio(servicioNombre);
		if (servicioBuscado.equals(null)) {
			return "No se ha encontrado ese servicio, intente con otro nombre";
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
			return "Servicio encontrado y cargado exitosamente al usuario";
		}
	}

	public static String pagarServicio(String ID, String servicioNombre) {
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
				return "¡pago realizado exitosamente!";
			} else {
				return "no se encontró el servicio a pagar, pago rechazado";
			}

		} else {
			return "el ID con el que se quiere pagar no se encuentra registrado, pago rechazado";
		}
	}

	public static String generarFactura(String ID) throws IOException {
		if (registroServicios.containsKey(ID)) {
			ArrayList<Servicio> serviciosDelUsuario = registroServicios.get(ID);
			if (serviciosDelUsuario.size() == 0) {
				return "No hay servicios enlazados a este ID";
			} else {
				boolean primero = true;
				String txt = "";
				for (Servicio servicio : serviciosDelUsuario) {
					if (primero) {
						primero = false;
					} else {
						txt += "\n";
					}
					txt += servicio.getTextoFactura();
				}
				String fileName = "Factura_" + ID;
				Inventario.guardarArchivo(txt, "facturas", fileName);
				return "Factura generada exitosamente, revise la carpeta \"facturas\"";
			}

		} else {
			return "el ID con el que se quiere generar la factura no se encuentra registrado";
		}
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

}
