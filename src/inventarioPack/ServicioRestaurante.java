package inventarioPack;

public class ServicioRestaurante extends Servicio{
	private String horaInicio;
    private String horaFinal;
    private boolean aCuarto;
    
	ServicioRestaurante(String nombre, Integer costo, String cantidadCliente, String aCuarto, String horaInicio, String horaFinal) {
		super(nombre, costo, nombre);
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		if (aCuarto.equals("A habitación")) {
			this.aCuarto = true;
		}else {
			this.aCuarto = false;
		}
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public boolean isaCuarto() {
		return aCuarto;
	}

	public void setaCuarto(boolean aCuarto) {
		this.aCuarto = aCuarto;
	}

}
