package serviciosPack;


public class Huesped {
	private String nombre;
	private String ID;
	private String correoElectronico;
	private String numeroTelefonico;
	
	public Huesped(String nombre, String iD, String correoElectronico, String numeroTelefonico) {
		this.nombre = nombre;
		ID = iD;
		this.correoElectronico = correoElectronico;
		this.numeroTelefonico = numeroTelefonico;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}
	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}
	
}
