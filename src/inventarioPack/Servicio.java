package inventarioPack;

public class Servicio {
	protected String nombre;
	protected Integer costo;
	protected boolean pagado;
	protected String textoFactura;
	protected String cantidadCliente;

	public Servicio(String nombre, Integer costo, String cantidadCliente) {
		this.nombre = nombre;
		this.costo = costo;
		this.pagado = false;
		this.cantidadCliente = cantidadCliente;
		textoFactura = textoFacturaTxt();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public String getTextoFactura() {
		textoFactura = textoFacturaTxt();
		return textoFactura;
	}

	private String textoFacturaTxt() {
		String txt = "";
		txt += "El servicio " + this.nombre;
		txt += ", que tiene un costo de " + String.valueOf(this.costo);
		if (pagado) {
			txt += " fue pagado exitosamente.";
		} else {
			txt += " NO ha sido pagado.";
		}
		return txt;
	}

	public String getCantidadCliente() {
		return cantidadCliente;
	}

	public void setCantidadCliente(String cantidadCliente) {
		this.cantidadCliente = cantidadCliente;
	}
}
