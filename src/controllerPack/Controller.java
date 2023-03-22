package controllerPack;

import loginApp.Usuario;
import loginApp.Login;


public class Controller {
	private static Usuario usuario;
	public static boolean logIn(String userName, String password) {
		usuario = Login.logIn(userName, password);
		if (usuario!=null) {
			return true;
		}else {
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
}
