package inventarioPack;

public class Tarifa {
	private String habitacion;
	private int precio;
	private String day;
	public Tarifa(String habitacion, int precio, String day) {
		this.habitacion = habitacion;
		this.precio = precio;
		this.day = day;
	}
	public String getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
}
