package logic;

public class Productos {
	private String codigo;
	private String denominacion;
	private String producto;
	private String descripcion;
	private float precioUnitario;
	private float precioGrupal;
	private String foto;
	private int unidades;
	
	public int getUnidades() {
		return unidades;
	}

	public void incrementarUnidades(int unidades) {
		this.unidades+= unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}


	public Productos(String codigo, String denominacion, String producto, String descripcion, float precioUnitario, float precioGrupal) {
		setCodigo(codigo);
		setDenominacion(denominacion);
		setProducto(producto);
		setDescripcion(descripcion);
		setPrecioUnitario(precioUnitario);
		setPrecioGrupal(precioGrupal);
		foto = "/img/" + codigo + ".jpg";
		unidades = 0;
	}


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public float getPrecioGrupal() {
		return precioGrupal;
	}
	public void setPrecioGrupal(float precioGrupal) {
		this.precioGrupal = precioGrupal;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	public String getFoto() {
		return foto;
	}
	public String toString() {
		String cadena ="";
		if(unidades ==  0) {
			cadena = denominacion + ": " + producto;
		}else {
			cadena = denominacion + ": " + producto + " (" + unidades + ")";
		}
		
		return cadena;
	}
	
}


