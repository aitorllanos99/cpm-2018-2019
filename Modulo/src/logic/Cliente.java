package logic;

public class Cliente {
	private String Nombre;
	private String Apellidos;
	private String Email;
	private String Telefono;
	private String usuario;
	private String contrasena;
	private boolean registrado;
	private String NIF;
	
	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public Cliente(String nombre,String apellidos,String email,String telefono , String NIF,String usuario , String contrasena) {
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setTelefono(telefono);
		setNIF(NIF);
		setUsuario(usuario);
		setContrasena(contrasena);
		registrado = true;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public boolean isRegistrado() {
		return registrado;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
