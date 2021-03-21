package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import logic.Administracion;
import logic.Productos;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaFinal extends JFrame {

	private VentanaPrincipal main;
	private VentanaCarrito carrito;
	private VentanaProductos productos;
	private ResourceBundle textos;
	private Administracion administracion;
	private DefaultListModel<Productos> modeloListCarrito;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCental;
	private JPanel panelDatos;
	private JPanel panelPrevisualizacionPedido;
	private JScrollPane scrollPane;
	private JList<Productos> listProductos;
	private JPanel panelTotal;
	private JTextField textField;
	private JLabel lblTotal;
	private JPanel panelBotones;
	private JButton btnNewButton;
	private JButton btnCancelar;
	private JPanel panelNombreYApellidos;
	private JPanel panelNombre;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JPanel panelApellidos;
	private JLabel lblApellidos;
	private JTextField textFieldApellidos;
	private JPanel panelDNI;
	private JLabel lblDninif;
	private JTextField textFieldDNI;
	private JPanel panelImprescindible;
	private JPanel panelFechas;
	private JScrollPane scrollPane_1;
	private JTextArea textAreaObservaciones;
	private JPanel panelFechaFiesta;
	private JPanel panelHoraFiesta;
	private JLabel lblDia;
	private JLabel lblAo;
	private JComboBox comboBoxDia;
	private JComboBox comboBoxMes;
	private JComboBox comboBoxAno;
	private JLabel lblMes;
	private JComboBox comboBoxMin;
	private JComboBox comboBoxHora;
	private JLabel lblHora;
	private JLabel lblMinutos;
	private JButton btnAtras;
	private JButton btnModificar;
	private JPanel panelInferior;
	private JPanel panelModificar;
	private String mensajeBlancos;
	private String tituloBlancos;
	private String mensajeExito;
	private String tituloExito;
	private String mensajeFecha;
	private String tituloFecha;

	/**
	 * Create the frame.
	 */
	public VentanaFinal(Locale localizacion, VentanaCarrito carrito, VentanaProductos productos,
			Administracion administracion) {
		setFont(new Font("Gadugi", Font.PLAIN, 14));
		this.carrito = carrito;
		this.productos = productos;
		this.administracion = administracion;
		setTitle("ParTy: Confirmacion del Pedido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFinal.class.getResource("/img/logoParTy.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 988, 704);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelCental(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		setLocationRelativeTo(productos);
		setSize(productos.getSize());
		localizar(localizacion);
		exponerPrecio();
		getRootPane().setDefaultButton(btnNewButton);
	}

	private JPanel getPanelCental() {
		if (panelCental == null) {
			panelCental = new JPanel();
			panelCental.setLayout(new GridLayout(2, 1, 0, 0));
			panelCental.add(getPanelPrevisualizacionPedido());
			panelCental.add(getPanelDatos());
		}
		return panelCental;
	}

	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelDatos.setBackground(Color.PINK);
			panelDatos.setBorder(
					new TitledBorder(null, "Datos Contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatos.setLayout(new BorderLayout(0, 0));
			panelDatos.add(getPanelNombreYApellidos(), BorderLayout.NORTH);
			panelDatos.add(getPanelImprescindible(), BorderLayout.CENTER);
		}
		return panelDatos;
	}

	private JPanel getPanelPrevisualizacionPedido() {
		if (panelPrevisualizacionPedido == null) {
			panelPrevisualizacionPedido = new JPanel();
			panelPrevisualizacionPedido.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelPrevisualizacionPedido.setBackground(Color.PINK);
			panelPrevisualizacionPedido.setBorder(
					new TitledBorder(null, "Resumen del pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPrevisualizacionPedido.setLayout(new BorderLayout(0, 0));
			panelPrevisualizacionPedido.add(getScrollPane());
			panelPrevisualizacionPedido.add(getPanel_1_2(), BorderLayout.SOUTH);
		}
		return panelPrevisualizacionPedido;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListProductos());
		}
		return scrollPane;
	}

	private JList<Productos> getListProductos() {
		if (listProductos == null) {
			modeloListCarrito = new DefaultListModel<Productos>();
			listProductos = new JList<Productos>(modeloListCarrito);
			listProductos.setFont(new Font("Gadugi", Font.PLAIN, 15));
		}
		return listProductos;
	}

	public void añadirArticulos(Productos productos) {
		modeloListCarrito.addElement(productos);
	}

	public void exponerPrecio() {
		/**
		 * List<Productos> comprados = new ArrayList<Productos>();
		 * System.out.println(modeloListCarrito); for(int
		 * i=0;i<modeloListCarrito.getSize();i++) {
		 * comprados.add(modeloListCarrito.getElementAt(i)); }
		 * System.out.println(comprados); textField.setText("" +
		 * administracion.calcularPrecio(comprados));
		 */
		textField.setText(productos.getCarrito().getTotal());
	}

	private JPanel getPanelTotal() {
		if (panelTotal == null) {
			panelTotal = new JPanel();
			panelTotal.setBackground(Color.PINK);
			panelTotal.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));
			panelTotal.add(getLblTotal());
			panelTotal.add(getTextField());
		}
		return panelTotal;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Gadugi", Font.PLAIN, 16));
			textField.setEditable(false);
			textField.setColumns(10);
		}
		return textField;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total:");
			lblTotal.setLabelFor(getTextField());
			lblTotal.setFont(new Font("Gadugi", Font.PLAIN, 16));
		}
		return lblTotal;
	}

	private JPanel getPanel_1() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setBackground(Color.PINK);
			FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
			flowLayout.setHgap(30);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelBotones.add(getBtnNewButton());
			panelBotones.add(getBtnAtras());
			panelBotones.add(getBtnCancelar());
		}
		return panelBotones;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Finalizar Pedido");
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setFont(new Font("Gadugi", Font.PLAIN, 15));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (comprobarBlancos()) {
						if (comprobarFecha()) {
							List<Productos> comprados = new ArrayList<Productos>();
							for (int i = 0; i < modeloListCarrito.getSize(); i++)
								comprados.add(modeloListCarrito.getElementAt(i));
							String fecha = comboBoxDia.getSelectedItem() + "/" + comboBoxMes.getSelectedItem() + "/"
									+ comboBoxAno.getSelectedItem();
							String hora = comboBoxHora.getSelectedItem() + ":" + comboBoxMin.getSelectedItem();
							administracion.hacerFactura(comprados, textFieldNombre.getText(),
									textFieldApellidos.getText(), textFieldDNI.getText(), fecha, hora,
									textAreaObservaciones.getText());
							acabar();
							VentanaPrincipal main = new VentanaPrincipal();
							main.setVisible(true);
							salir();
						} else {
							fecha();
						}
					} else {
						blancos();
					}
				}

			});
		}
		return btnNewButton;
	}

	private void fecha() {
		JOptionPane.showMessageDialog(this, mensajeFecha, tituloFecha, JOptionPane.ERROR_MESSAGE);

	}

	private void blancos() {
		JOptionPane.showMessageDialog(this, mensajeBlancos, tituloBlancos, JOptionPane.ERROR_MESSAGE);
	}

	private void acabar() {
		JOptionPane.showMessageDialog(this, mensajeExito, tituloExito, JOptionPane.INFORMATION_MESSAGE);
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					productos.cancelar();
					salir();
				}
			});
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setFont(new Font("Gadugi", Font.PLAIN, 15));
		}
		return btnCancelar;
	}

	private void salir() {
		this.dispose();
	}

	private JPanel getPanelNombreYApellidos() {
		if (panelNombreYApellidos == null) {
			panelNombreYApellidos = new JPanel();
			panelNombreYApellidos.setBackground(Color.PINK);
			FlowLayout flowLayout = (FlowLayout) panelNombreYApellidos.getLayout();
			flowLayout.setHgap(90);
			panelNombreYApellidos.add(getPanelNombre());
			panelNombreYApellidos.add(getPanelApellidos());
			panelNombreYApellidos.add(getPanel_1_1());
		}
		return panelNombreYApellidos;
	}

	private JPanel getPanelNombre() {
		if (panelNombre == null) {
			panelNombre = new JPanel();
			panelNombre.setBackground(Color.PINK);
			panelNombre.add(getLblNombre());
			panelNombre.add(getTextFieldNombre());
		}
		return panelNombre;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre :");
			lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblNombre.setLabelFor(getTextFieldNombre());
		}
		return lblNombre;
	}

	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setFont(new Font("Gadugi", Font.PLAIN, 14));
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}

	private JPanel getPanelApellidos() {
		if (panelApellidos == null) {
			panelApellidos = new JPanel();
			panelApellidos.setBackground(Color.PINK);
			panelApellidos.add(getLblApellidos());
			panelApellidos.add(getTextFieldApellidos());
		}
		return panelApellidos;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos : ");
			lblApellidos.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblApellidos.setLabelFor(getTextFieldApellidos());
		}
		return lblApellidos;
	}

	private JTextField getTextFieldApellidos() {
		if (textFieldApellidos == null) {
			textFieldApellidos = new JTextField();
			textFieldApellidos.setFont(new Font("Gadugi", Font.PLAIN, 14));
			textFieldApellidos.setColumns(10);
		}
		return textFieldApellidos;
	}

	private JPanel getPanel_1_1() {
		if (panelDNI == null) {
			panelDNI = new JPanel();
			panelDNI.setBackground(Color.PINK);
			panelDNI.add(getLblDninif());
			panelDNI.add(getTextFieldDNI());
		}
		return panelDNI;
	}

	private JLabel getLblDninif() {
		if (lblDninif == null) {
			lblDninif = new JLabel("DNI/NIF:");
			lblDninif.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblDninif.setLabelFor(getTextFieldDNI());
		}
		return lblDninif;
	}

	private JTextField getTextFieldDNI() {
		if (textFieldDNI == null) {
			textFieldDNI = new JTextField();
			textFieldDNI.setFont(new Font("Gadugi", Font.PLAIN, 14));
			textFieldDNI.setColumns(10);
		}
		return textFieldDNI;
	}

	private JPanel getPanelImprescindible() {
		if (panelImprescindible == null) {
			panelImprescindible = new JPanel();
			panelImprescindible.setLayout(new GridLayout(2, 1, 0, 0));
			panelImprescindible.add(getPanelFechas());
			panelImprescindible.add(getScrollPane_1());
		}
		return panelImprescindible;
	}

	private JPanel getPanelFechas() {
		if (panelFechas == null) {
			panelFechas = new JPanel();
			panelFechas.setLayout(new GridLayout(0, 2, 0, 0));
			panelFechas.add(getPanel_2());
			panelFechas.add(getPanel_3());
		}
		return panelFechas;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBorder(
					new TitledBorder(null, "Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scrollPane_1.setBackground(Color.WHITE);
			scrollPane_1.setViewportView(getTextAreaObservaciones());
		}
		return scrollPane_1;
	}

	private JTextArea getTextAreaObservaciones() {
		if (textAreaObservaciones == null) {
			textAreaObservaciones = new JTextArea();
			textAreaObservaciones.setLineWrap(true);
			textAreaObservaciones.setFont(new Font("Gadugi", Font.PLAIN, 15));
		}
		return textAreaObservaciones;
	}

	private JPanel getPanel_2() {
		if (panelFechaFiesta == null) {
			panelFechaFiesta = new JPanel();
			panelFechaFiesta.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelFechaFiesta.setBackground(Color.PINK);
			panelFechaFiesta.setBorder(
					new TitledBorder(null, "Fecha Fiesta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelFechaFiesta.setLayout(new GridLayout(2, 3, 0, 0));
			panelFechaFiesta.add(getLblDia());
			panelFechaFiesta.add(getLblMes());
			panelFechaFiesta.add(getLblAo());
			panelFechaFiesta.add(getComboBoxDia());
			panelFechaFiesta.add(getComboBoxMes());
			panelFechaFiesta.add(getComboBoxAno());
		}
		return panelFechaFiesta;
	}

	private JPanel getPanel_3() {
		if (panelHoraFiesta == null) {
			panelHoraFiesta = new JPanel();
			panelHoraFiesta.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelHoraFiesta.setBackground(Color.PINK);
			panelHoraFiesta.setBorder(
					new TitledBorder(null, "Hora Fiesta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelHoraFiesta.setLayout(new GridLayout(2, 2, 0, 0));
			panelHoraFiesta.add(getLblHora());
			panelHoraFiesta.add(getLblMinutos());
			panelHoraFiesta.add(getComboBoxHora());
			panelHoraFiesta.add(getComboBoxMin());
		}
		return panelHoraFiesta;
	}

	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("Dia : ");
			lblDia.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblDia.setLabelFor(getComboBoxDia());
		}
		return lblDia;
	}

	private JLabel getLblAo() {
		if (lblAo == null) {
			lblAo = new JLabel("A\u00F1o:");
			lblAo.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblAo.setLabelFor(getComboBoxAno());
		}
		return lblAo;
	}

	private JComboBox getComboBoxDia() {
		if (comboBoxDia == null) {
			comboBoxDia = new JComboBox();
			comboBoxDia.setFont(new Font("Gadugi", Font.PLAIN, 15));
			comboBoxDia.setBackground(Color.WHITE);
			comboBoxDia.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
					"26", "27", "28", "29", "30", "31" }));
		}
		return comboBoxDia;
	}

	private JComboBox getComboBoxMes() {
		if (comboBoxMes == null) {
			comboBoxMes = new JComboBox();
			comboBoxMes.setFont(new Font("Gadugi", Font.PLAIN, 15));
			comboBoxMes.setBackground(Color.WHITE);
			comboBoxMes.setModel(new DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
					"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
		}
		return comboBoxMes;
	}

	private JComboBox getComboBoxAno() {
		if (comboBoxAno == null) {
			comboBoxAno = new JComboBox();
			comboBoxAno.setFont(new Font("Gadugi", Font.PLAIN, 15));
			comboBoxAno.setBackground(Color.WHITE);
			comboBoxAno.setModel(new DefaultComboBoxModel(new String[] { "2018", "2019", "2020" }));
		}
		return comboBoxAno;
	}

	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("Mes:");
			lblMes.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblMes.setLabelFor(getComboBoxMes());
		}
		return lblMes;
	}

	private JComboBox getComboBoxMin() {
		if (comboBoxMin == null) {
			comboBoxMin = new JComboBox();
			comboBoxMin.setFont(new Font("Gadugi", Font.PLAIN, 15));
			comboBoxMin.setBackground(Color.WHITE);
			comboBoxMin.setModel(new DefaultComboBoxModel(new String[] { "01m", "02m", "03m", "04m", "05m", "06m",
					"07m", "08m", "09m", "10m", "11m", "12m", "13m", "14m", "15m", "16m", "17m", "18m", "19m", "20m",
					"21m", "22m", "23m", "24m", "25m", "26m", "27m", "28m", "29m", "30m", "31m", "32m", "33m", "34m",
					"35m", "36m", "37m", "38m", "39m", "40m", "41m", "42m", "43m", "44m", "45m", "46m", "47m", "48m",
					"49m", "50m", "51m", "52m", "53m", "54m", "55m", "56m", "57m", "58m", "59m" }));
		}
		return comboBoxMin;
	}

	private JComboBox getComboBoxHora() {
		if (comboBoxHora == null) {
			comboBoxHora = new JComboBox();
			comboBoxHora.setFont(new Font("Gadugi", Font.PLAIN, 15));
			comboBoxHora.setBackground(Color.WHITE);
			comboBoxHora.setModel(new DefaultComboBoxModel(new String[] { "00h", "01h", "02h", "03h", "04h", "05h",
					"06h", "07h", "08h", "09h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h", "19h",
					"20h", "21h", "22h", "23h", "24h" }));
		}
		return comboBoxHora;
	}

	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora:");
			lblHora.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblHora.setLabelFor(getComboBoxHora());
		}
		return lblHora;
	}

	private JLabel getLblMinutos() {
		if (lblMinutos == null) {
			lblMinutos = new JLabel("Minutos : ");
			lblMinutos.setFont(new Font("Gadugi", Font.PLAIN, 15));
			lblMinutos.setLabelFor(getComboBoxMin());
		}
		return lblMinutos;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.setBackground(Color.WHITE);
			btnAtras.setFont(new Font("Gadugi", Font.PLAIN, 15));
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					carrito.setVisible(true);
					ocultar();
				}
			});
		}
		return btnAtras;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setFont(new Font("Gadugi", Font.PLAIN, 15));
			btnModificar.setBackground(Color.WHITE);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					carrito.setVisible(true);
					ocultar();
				}
			});
		}
		return btnModificar;
	}

	private void ocultar() {
		this.setVisible(false);
	}

	private JPanel getPanel_1_2() {
		if (panelInferior == null) {
			panelInferior = new JPanel();
			panelInferior.setLayout(new GridLayout(2, 1, 0, 0));
			panelInferior.add(getPanel_4());
			panelInferior.add(getPanelTotal());
		}
		return panelInferior;
	}

	private JPanel getPanel_4() {
		if (panelModificar == null) {
			panelModificar = new JPanel();
			panelModificar.setBackground(Color.PINK);
			FlowLayout flowLayout = (FlowLayout) panelModificar.getLayout();
			flowLayout.setHgap(50);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelModificar.add(getBtnModificar());
		}
		return panelModificar;
	}

	private void localizar(Locale localizacion) {
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		setTitle(textos.getString("tituloVentanaFinal"));

		panelDatos.setBorder(new TitledBorder(null, textos.getString("panelDatos"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelPrevisualizacionPedido.setBorder(new TitledBorder(null, textos.getString("panelResumen"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFechaFiesta.setBorder(new TitledBorder(null, textos.getString("panelFecha"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		scrollPane_1.setBorder(new TitledBorder(null, textos.getString("panelObservaciones"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelHoraFiesta.setBorder(new TitledBorder(null, textos.getString("panelHora"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		btnCancelar.setText(textos.getString("botonCancelarFinal"));
		btnCancelar.setMnemonic(textos.getString("mnemonicoCancelarFinal").charAt(0));
		btnCancelar.setToolTipText(textos.getString("tooltipCancelarFinal"));

		btnModificar.setText(textos.getString("botonModificar"));
		btnModificar.setMnemonic(textos.getString("mnemonicoModificar").charAt(0));
		btnModificar.setToolTipText("tooltipModificar");

		btnAtras.setText(textos.getString("botonAtras"));
		btnAtras.setMnemonic(textos.getString("mnemonicoAtras").charAt(0));
		btnAtras.setToolTipText(textos.getString("tooltipAtrasFinal"));

		btnNewButton.setText(textos.getString("botonFinalizarPedido"));
		btnNewButton.setMnemonic(textos.getString("mnemonicoFinalizarPedido").charAt(0));
		btnNewButton.setToolTipText(textos.getString("tootltipFinalizarFinal"));

		lblNombre.setText(textos.getString("labelNombreFinal"));
		lblNombre.setDisplayedMnemonic(textos.getString("mnemonicoNombreFinal").charAt(0));

		lblApellidos.setText(textos.getString("labelApellidoFinal"));
		lblApellidos.setDisplayedMnemonic(textos.getString("mnemonicoApellidoFinal").charAt(0));

		lblDninif.setText(textos.getString("labelDNIFinal"));
		lblDninif.setDisplayedMnemonic(textos.getString("mnemonicoDNIFinal").charAt(0));

		lblDia.setText(textos.getString("labelDia"));
		lblDia.setDisplayedMnemonic(textos.getString("mnemonicoDia").charAt(0));

		lblMes.setText(textos.getString("labelMes"));
		lblMes.setDisplayedMnemonic(textos.getString("mnemonicoMes").charAt(0));

		lblAo.setText(textos.getString("labelAno"));
		lblAo.setDisplayedMnemonic(textos.getString("mnemonicoAno").charAt(0));

		lblHora.setText(textos.getString("labelHora"));
		lblHora.setDisplayedMnemonic(textos.getString("mnemonicoHora").charAt(0));

		lblMinutos.setText(textos.getString("labelMinutos"));
		lblDninif.setDisplayedMnemonic(textos.getString("mnemonicoMinutos").charAt(0));

		comboBoxMes.setModel(new DefaultComboBoxModel(
				new String[] { textos.getString("combo1"), textos.getString("combo2"), textos.getString("combo3"),
						textos.getString("combo4"), textos.getString("combo5"), textos.getString("combo6"),
						textos.getString("combo7"), textos.getString("combo8"), textos.getString("combo9"),
						textos.getString("combo10"), textos.getString("combo11"), textos.getString("combo12") }));

		mensajeBlancos = textos.getString("mensajeBlancos");
		tituloBlancos = textos.getString("tituloBlancos");
		mensajeExito = textos.getString("mensajesExito");
		tituloExito = textos.getString("tituloExito");
		mensajeFecha = textos.getString("mensajeFecha");
		tituloFecha = textos.getString("tituloFecha");
	}

	private boolean comprobarBlancos() {
		if (textFieldNombre.getText().isEmpty() || textFieldApellidos.getText().isEmpty()
				|| textFieldDNI.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean comprobarFecha() {
		if ((Integer.parseInt((String) comboBoxDia.getSelectedItem()) > 30)) {
			if (comboBoxMes.getSelectedItem().equals("Enero") || comboBoxMes.getSelectedItem().equals("Marzo")
					|| comboBoxMes.getSelectedItem().equals("Mayo") || comboBoxMes.getSelectedItem().equals("Julio")
					|| comboBoxMes.getSelectedItem().equals("Agosto") || comboBoxMes.getSelectedItem().equals("Octubre")
					|| comboBoxMes.getSelectedItem().equals("Diciembre")) {
				return true;
			}

		} else {
			if (Integer.parseInt((String) comboBoxDia.getSelectedItem()) > 29) {
				if (comboBoxMes.getSelectedItem().equals("Febrero"))
				return false;
			}else {
				return true;
			}

		}
		return false;
	}
}
