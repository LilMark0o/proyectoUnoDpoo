package loginApp;

public class Usuario {
	private String logIn;
	private String password;
	private String rol;

	public Usuario(String logIn, String password) {
		this.logIn = logIn;
		this.password = password;
		rol = generarRol();
	}

	private String generarRol() {
		String[] porArroba = logIn.split("@");
		String porAhora = porArroba[1];
		porAhora = porAhora.replace(".com", "");
		return porAhora;
	}

	public String getLogIn() {
		return logIn;
	}

	public void setLogIn(String logIn) {
		this.logIn = logIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
