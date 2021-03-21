package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Administracion {
	private List<Productos> bebidas= new ArrayList<Productos>();
	private List<Productos> comida= new ArrayList<Productos>();
	private List<Productos> decoraciones= new ArrayList<Productos>();
	private List<Productos> locales = new ArrayList<Productos>();
	private List<Productos> otros= new ArrayList<Productos>();
	private List<Productos> productos = new ArrayList<Productos>();
	private Cliente clienteActual;
	private int invitados = 10;
	private float  precioTotal;
	private float precioDescontado;
	private List<Productos> bebidasCompradas= new ArrayList<Productos>();
	private List<Productos> comidaComprada= new ArrayList<Productos>();
	private List<Productos> decoracionesCompradas= new ArrayList<Productos>();
	private List<Productos> localesComprados = new ArrayList<Productos>();
	private List<Productos> otrosComprados= new ArrayList<Productos>();
	
	
	public int getInvitados() {
		return invitados;
	}

	public void setInvitados(int invitados) {
		this.invitados = invitados;
	}

	/*
	 * Para leer todos los productos de fiesta.txt
	 */
	public void leerProductos(String nameFile) {
		productos.clear();
		String linea = "";
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nameFile));
	    	while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		String[] trozos = linea.split("@");
	    		productos.add(new Productos(trozos[0], trozos [1], trozos[2], trozos[3], Float.parseFloat(trozos[4]), Float.parseFloat(trozos[5])));
	    	}
	    	clasificarListaProductos(productos);
	    	fichero.close();
	    	}
	    	catch (FileNotFoundException fnfe) {
	    		JOptionPane.showMessageDialog(null,"El archivo no se ha encontrado");
	    	}
	    	catch (IOException ioe) {
	    		new RuntimeException("Error de entrada/salida.");
	    	}
	
		}
	
	private void clasificarListaProductos(List<Productos> productos) {
		for(int i=0;i<productos.size();i++) {
			clasificarProductos(productos.get(i));
		}
	}
	
	/*
	 * Metodo que calcula si un cliente ya ha sido registrado en el clientes.dat
	 * @param el cliente a comprobar
	 */
	public  boolean comprobarClientes(String usuario) {
		String linea = "";
		String usuarioCliente = usuario;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("files/clientes.dat"));
			while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		String[] trozos = linea.split("@");
	    		if(trozos[0].equals(usuarioCliente)) {   //El Cliente ya esta Registrado
	    			return true;
	    		}
	    	}
			fichero.close();
	    	
	    	
	    	}
	    	catch (FileNotFoundException fnfe) {
	    		JOptionPane.showMessageDialog(null,"El archivo no se ha encontrado");
	    	}
	    	catch (IOException ioe) {
	    		new RuntimeException("Error de entrada/salida.");
	    	}
		return false;
	}
	
	public Cliente getClienteActual() {
		return clienteActual;
	}

	public void setClienteActual(Cliente clienteActualParam) {
		clienteActual = clienteActualParam;
	}
	
	public List<Productos> getLocales() {
		return locales;
	}
	public List<Productos> getComida() {
		return comida;
	}
	public List<Productos> getBebida() {
		return bebidas;
	}
	public List<Productos> getOtros() {
		return otros;
	}
	public List<Productos> getDecoraciones() {
		return decoraciones;
	}

	/*
 * Para tener una base de datos con todos los usuarios que han entrado
 */
	public  void registrarCliente(Cliente cliente) {
		String linea = cliente.getUsuario() + "@" + cliente.getContrasena() + "\n";
	//	String datos = cliente.getNombre() + "@" + cliente.getApellidos() + "@" + cliente.getNIF() + "@" + cliente.getUsuario()+ "@"+ cliente.getContrasena() +"\n";
		String nombreFichero = "files/clientes.dat";
	//	String nombreDatos = "files/dataBase.dat";
		    try {
		         BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero,true));
		        // fichero.newLine();
		         fichero.write(linea);
		         fichero.close();
		         
		        // BufferedWriter ficheroDatos = new BufferedWriter(new FileWriter(nombreDatos));
		        // ficheroDatos.append(datos);
		       //  ficheroDatos.write(datos);
		        // ficheroDatos.close();
		        
		     }
		    catch (FileNotFoundException fnfe) {
		    	System.out.println("El archivo no se ha podido guardar");
		    }
		    catch (IOException ioe) {
		    	new RuntimeException("Error de entrada/salida.");
		    }
	}	
	
	/*
	 * Metodo que calcula el precio final de una lista de productos
	 * @param la lista de productos a calcular el precio
	 * 
	 */
	public float calcularPrecio(List<Productos> producto) {
		precioTotal = 0;
		for(int i=0; i<producto.size();i++) {
			if(producto.get(i).getCodigo().charAt(0) == 'C' || producto.get(i).getCodigo().charAt(0) == 'B') {
				precioTotal += calcularPrecioUnitario(producto.get(i));
				}else {
					precioTotal += calcularPrecioGrupal(producto.get(i));
				}
			}
		if(clienteActual != null) {
			precioDescontado = (float) (precioTotal*0.15);
			precioTotal = precioTotal - precioDescontado;
		}
		return precioTotal;
	}
	
	public float calcularPrecioUnitario(Productos seleccionado) {
		float precioTotal = 0;
		precioTotal =  seleccionado.getUnidades() * seleccionado.getPrecioUnitario();
		return precioTotal;
	}
	/*
	 * Metodo que calcula el precio final de una lista de productos
	 * @param la lista de productos a calcular el precio
	 * 
	 */
	public float calcularPrecioGrupal(Productos producto) {
		float precioTotal = 0;
		precioTotal =  calcularPrecioPorInvitado() * producto.getPrecioGrupal();
		return precioTotal;
	}
	
	private int calcularPrecioPorInvitado() {
		if(invitados%10 == 0) {
			return (invitados/10);
		}else {
			return (invitados/10)+1;
		}
	}
	
	/*
	 * Metodo que clasifica los prodcutos en diferentes listas dependiendo de su codigo
	 * @param el producto a clasificar
	 */
	public void clasificarProductos(Productos producto) {
		String codigo = producto.getCodigo();
		char letraIdentificativa = codigo.charAt(0);
		if(letraIdentificativa == 'C') {
			comida.add(producto);
		}
		if(letraIdentificativa == 'B') {
			bebidas.add(producto);
		}
		if(letraIdentificativa == 'D') {
			decoraciones.add(producto);
		}
		if(letraIdentificativa == 'O') {
			otros.add(producto);
		}
		if(letraIdentificativa == 'L') {
			locales.add(producto);
		}
	}
	
	/*
	 * Metodo que clasifica los prodcutos en diferentes listas dependiendo de su codigo
	 * @param el producto a clasificar
	 */
	private void clasificarCompra(Productos producto) {
		String codigo = producto.getCodigo();
		char letraIdentificativa = codigo.charAt(0);
		if(letraIdentificativa == 'C') {
			comidaComprada.add(producto);
		}
		if(letraIdentificativa == 'B') {
			bebidasCompradas.add(producto);
		}
		if(letraIdentificativa == 'D') {
			decoracionesCompradas.add(producto);
		}
		if(letraIdentificativa == 'O') {
			otrosComprados.add(producto);
		}
		if(letraIdentificativa == 'L') {
			localesComprados.add(producto);
		}
	}

	public void vaciarListas() {
		comida.clear();
		bebidas.clear();
		decoraciones.clear();
		locales.clear();
		otros.clear();
	}
	
	
	public void hacerFactura(List<Productos> comprados,String nombre, String apellidos, String dni , String fecha,String hora, String Observaciones) {
		String[] trozosFecha = fecha.split("/");
		String fechaFichero = trozosFecha[0]+trozosFecha[1]+trozosFecha[2];
		String nombreFichero = "bills/" + dni + fechaFichero;
		String usuario = " ";
		if(clienteActual != null) 
			usuario = " (CLIENTE REGISTRADO: " + clienteActual.getUsuario() + ")";
		
		 try {
	         BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero));
	         fichero.write("FACTURA FIESTA\n");
	         fichero.write("-------------- \n");
	         //fichero.write("\t\t ** CLIENTE: " + clienteActual.getNombre() + " " + clienteActual.getApellidos() + " (CLIENTE REGISTRADO: " + clienteActual.getUsuario() + ") \n");
	         fichero.write("\t\t ** CLIENTE: " + nombre + " " + apellidos + usuario + "\n");
	         fichero.write("\t\t **NIF: " + dni + "\n");
	        // fichero.write("\t\t **NIF: " + clienteActual.getNIF() + "\n");
	         fichero.write("\t\t **FECHA Y HORA FIESTA: " + fecha + "  " + hora + "\n");
	         fichero.write("\t\t ** NUMERO PERSONAS: " + getInvitados() + "\n");
	         fichero.write("\n");
	         fichero.write("RELACION ARTICULOS: PRODUCTO / CÓDIGO / UNIDADES / TOTAL ARTICULO \n");
	         fichero.write("--------------------------------------------------------------------- \n");
	         for(int i=0;i<comprados.size();i++) {
	        	 clasificarCompra(comprados.get(i));
	         }
	         fichero.write("Bebida: \n");
	         for(int i=0;i<bebidasCompradas.size();i++) {
	        	 fichero.write("* " + bebidasCompradas.get(i).getProducto() +  " / " + bebidasCompradas.get(i).getCodigo() + " / " + bebidasCompradas.get(i).getUnidades() + " / " + bebidasCompradas.get(i).getPrecioUnitario() + "\n");
	         }
	         fichero.write("Comida: \n");
	         for(int i=0;i<comidaComprada.size();i++) {
	        	 fichero.write("* " +comidaComprada.get(i).getProducto() + " / " + comidaComprada.get(i).getCodigo() + " / " + comidaComprada.get(i).getUnidades() + " / " + comidaComprada.get(i).getPrecioUnitario() + "\n");
	         }
	         fichero.write("Decoracion: \n");
	         for(int i=0;i<decoracionesCompradas.size();i++) {
	        	 fichero.write("* " +decoracionesCompradas.get(i).getProducto() + " / " + decoracionesCompradas.get(i).getCodigo() + " / " + decoracionesCompradas.get(i).getUnidades() + " / " + decoracionesCompradas.get(i).getPrecioGrupal() + "\n");
	         }
	         fichero.write("Locales: \n");
	         for(int i=0;i<localesComprados.size();i++) {
	        	 fichero.write("* " +localesComprados.get(i).getProducto() + " / " + localesComprados.get(i).getCodigo() + " / " + localesComprados.get(i).getUnidades() + " / " + localesComprados.get(i).getPrecioGrupal() +"\n");
	         }
	         fichero.write("Otros: \n");
	         for(int i=0;i<otrosComprados.size();i++) {
	        	 fichero.write("* " +otrosComprados.get(i).getProducto() + " / " + otrosComprados.get(i).getCodigo() + " / " + otrosComprados.get(i).getUnidades() + " / " + otrosComprados.get(i).getPrecioGrupal() + "\n");
	         }
	         fichero.write("\n");
	         fichero.write("OBSERVACIONES\n");
	         fichero.write("-------------\n");
	         fichero.write(Observaciones + "\n\n");
	         if(clienteActual != null) {
	 			fichero.write("TOTAL FACTURA CON DESCUENTO CLIENTE: " + (precioTotal + precioDescontado) + "-" + precioDescontado + " = " + precioTotal +  " €");
	 		}else {
	 			 fichero.write("TOTAL FACTURA:" + this.precioTotal + "€ \n");
	 		}
	        
	         fichero.close();
	        
	     }
	    catch (FileNotFoundException fnfe) {
	    	System.out.println("El archivo no se ha podido guardar");
	    }
	    catch (IOException ioe) {
	    	new RuntimeException("Error de entrada/salida.");
	    }
	}
}
