package loginApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {
	private static Map<String, String> users;
	private static Map<String, String> usersHuespeds;

	public static void cargarLogIn() throws FileNotFoundException {
		users = new HashMap<String, String>();
		cargarDataBase();
		usersHuespeds = new HashMap<String, String>();
		cargarDataBaseHuespeds();
	}

	private static void cargarDataBase() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/" + "dataBase.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String userName = partes[0];
			String passwordUser = partes[1];
			users.put(userName, passwordUser);
		}
		scan.close();
	}

	private static void cargarDataBaseHuespeds() throws FileNotFoundException {
		String archivo = System.getProperty("user.dir") + "/data/huespedesData/" + "dataBase.txt";
		File file = new File(archivo);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String userName = partes[0];
			String passwordUser = partes[1];
			usersHuespeds.put(userName, passwordUser);
		}
		scan.close();
	}

	private static boolean isInUsers(String user) {
		if (users.containsKey(user)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isInUsersHuesped(String user) {
		if (usersHuespeds.containsKey(user)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean canCreateUser(String userName, String password) {
		if (usersHuespeds.containsKey(userName)) {
			return false;
		} else {
			return true;
		}
	}

	public static void createUser(String userName, String password) {
		usersHuespeds.put(userName, password);
	}

	public static String changePassword(Usuario usuario, String newPassword) {
		if (usuario == null) {
			return "No se ha definido un usuario al que se le cambie la contraseña, no se puede llevar a cabo esta acción";

		}
		if (usuario.getPassword() != newPassword) {
			users.put(usuario.getLogIn(), newPassword);
			return "Contraseña cambiada exitosamente";
		} else {
			return "La contraseña nueva es la misma que la vieja";
		}
	}

	private static boolean canLogIn(String user, String password) {
		if ((isInUsers(user)) && (users.containsValue(password))) {
			if (users.get(user).equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private static boolean canLogInHuesped(String user, String password) {
		if ((isInUsersHuesped(user)) && (usersHuespeds.containsValue(password))) {
			if (usersHuespeds.get(user).equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static Usuario logIn(String user, String password) {
		if (canLogIn(user, password)) {
			Usuario usuario = new Usuario(user, password);
			return usuario;
		} else {
			return null;
		}
	}

	public static Usuario logInHuesped(String user, String password) {
		if (canLogInHuesped(user, password)) {
			Usuario usuario = new Usuario(user, password);
			return usuario;
		} else {
			return null;
		}
	}

	public static void guardarCambios() throws IOException {
		String text = textoDataBase();
		String archivo = System.getProperty("user.dir") + "/data/" + "dataBase.txt";
		FileWriter writer = new FileWriter(archivo);
		writer.write(text);
		writer.close();
		// otra parte xd
		text = textoDataBaseHuespeds();
		archivo = System.getProperty("user.dir") + "/data/huespedesData/" + "dataBase.txt";
		writer = new FileWriter(archivo);
		writer.write(text);
		writer.close();
	}

	private static String textoDataBase() {
		String texto = "";
		boolean primero = true;
		for (Map.Entry<String, String> entry : users.entrySet()) {
			if (primero) {
				primero = false;
			} else {
				texto += "\n";
			}
			String key = entry.getKey();
			String value = entry.getValue();
			texto += key + ";" + value;
		}
		return texto;
	}

	private static String textoDataBaseHuespeds() {
		String texto = "";
		boolean primero = true;
		for (Map.Entry<String, String> entry : usersHuespeds.entrySet()) {
			if (primero) {
				primero = false;
			} else {
				texto += "\n";
			}
			String key = entry.getKey();
			String value = entry.getValue();
			texto += key + ";" + value;
		}
		return texto;
	}

}
