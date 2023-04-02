package serviciosPack;

public class Huesped {
	protected int IDgrupo;
	protected String nombre;
	protected int edad;
	protected String ID;

	public Huesped(String nombre, int edad, String iD, int IDGrupo) {
		this.nombre = nombre;
		this.edad = edad;
		ID = iD;
		IDgrupo = IDGrupo;
	}

	public int getIDgrupo() {
		return IDgrupo;
	}

	public void setIDgrupo(int iDgrupo) {
		IDgrupo = iDgrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
