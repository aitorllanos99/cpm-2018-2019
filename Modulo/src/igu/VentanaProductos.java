package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;

import logic.Administracion;
import logic.Productos;

import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class VentanaProductos extends JFrame {

	private VentanaCarrito carrito;
	private VentanaFinal vfinal;
	private VentanaPrincipal main;
	private Administracion administracion;
	private ResourceBundle textos;
	private Locale localizacion;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel panelLista;
	private JPanel panelVisualizacion;
	private JScrollPane scrollPaneLista;
	private JList<Productos> listaProductos;
	private JPanel panelNorte;
	private JLabel lblNombreArticulo;
	private JPanel panelPrevisualizar;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea textAreaDescripcion;
	private JPanel panelAñadirCarrito;
	private JPanel panelVisual;
	private JButton btnAadirAlCarrito;
	private JPanel panelFoto;
	private JPanel panelNumeros;
	private JLabel lblFoto;
	private JPanel panelUnidades;
	private JLabel label;
	private DefaultListModel<Productos> modeloListProductos;
	private JPanel panelFiltrado;
	private JRadioButton rdbtnComida;
	private JRadioButton rdbtnBebida;
	private JRadioButton rdbtnDecoracion;
	private JRadioButton rdbtnLocal;
	private JRadioButton rdbtnOtros;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnFinalizarPedido;
	private JButton btnCancelarPedido;
	private JLabel lblPrecio;
	private JTextField textFieldPrecioUnitario;
	private JPanel panelCarrito;
	private JPanel panelNombre;
	private JPanel panelGrupal;
	private JPanel panelUnitario;
	private JLabel lblElPrecioEs;
	private JPanel panelPrecio;
	private JLabel lblUnidades;
	private JSpinner spinner;
	private JPanel panelSpinner;
	private JPanel panelLabelUds;
	private JPanel panelAtras;
	private JPanel panelImportante;
	private JLabel labelAtras;

	/**
	 * Create the frame.
	 */
	public VentanaProductos(VentanaPrincipal main, Locale localizacion, Administracion administracion) {
		setFont(new Font("Gadugi", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaProductos.class.getResource("/img/logoParTy.png")));
		this.administracion = administracion;
		this.main = main;
		this.localizacion = localizacion;
		setLocationRelativeTo(main);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1098, 728);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelCentral(), BorderLayout.CENTER);
		setSize(main.getSize());
		setLocationRelativeTo(main);
		carrito = new VentanaCarrito(this, localizacion, administracion, main);
		localizar(localizacion);
		getRootPane().setDefaultButton(btnAadirAlCarrito);
	}

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new GridLayout(1, 0, 0, 0));
			panelCentral.add(getPanelLista());
			panelCentral.add(getPanelVisualizacion());
		}
		return panelCentral;
	}

	private JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setLayout(new BorderLayout(0, 0));
			panelLista.add(getPanelImportante(), BorderLayout.CENTER);
			panelLista.add(getPanelAtras(), BorderLayout.NORTH);
		}
		return panelLista;
	}

	private JPanel getPanelVisualizacion() {
		if (panelVisualizacion == null) {
			panelVisualizacion = new JPanel();
			panelVisualizacion.setLayout(new BorderLayout(0, 0));
			panelVisualizacion.add(getPanelNorte(), BorderLayout.NORTH);
			panelVisualizacion.add(getPanelPrevisualizar(), BorderLayout.CENTER);
			panelVisualizacion.add(getPanelAñadirCarrito(), BorderLayout.SOUTH);
		}
		return panelVisualizacion;
	}

	private JScrollPane getScrollPaneLista() {
		if (scrollPaneLista == null) {
			scrollPaneLista = new JScrollPane();
			scrollPaneLista.setBackground(Color.PINK);
			scrollPaneLista.setViewportView(getListaProductos());
		}
		return scrollPaneLista;
	}

	private JList<Productos> getListaProductos() {
		if (listaProductos == null) {
			modeloListProductos = new DefaultListModel<Productos>();
			listaProductos = new JList<Productos>(modeloListProductos);
			listaProductos.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {

					Productos seleccionado = listaProductos.getSelectedValue();
					if (seleccionado != null) {
						btnAadirAlCarrito.setEnabled(true);
						textAreaDescripcion.setText(seleccionado.getDescripcion());
						lblNombreArticulo.setText(seleccionado.getProducto());
						if (seleccionado.getCodigo().charAt(0) == 'C' || seleccionado.getCodigo().charAt(0) == 'B') {
							textFieldPrecioUnitario.setText("" + seleccionado.getPrecioUnitario() + " €");
						} else {
							textFieldPrecioUnitario.setText("" + administracion.calcularPrecioGrupal(seleccionado) + " €");
						}

						lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource(seleccionado.getFoto())));
					}
				}
			});
			listaProductos.setFont(new Font("Gadugi", Font.BOLD, 14));
			listaProductos.setBorder(new LineBorder(Color.BLACK));
			listaProductos.setBackground(Color.PINK);
		}
		return listaProductos;
	}

	public DefaultListModel<Productos> getModeloListProductos() {
		return modeloListProductos;
	}

	public void setModeloListProductos(DefaultListModel<Productos> modeloListProductos) {
		this.modeloListProductos = modeloListProductos;
	}

	public VentanaFinal getVfinal() {
		return vfinal;
	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBackground(Color.PINK);
			panelNorte.setLayout(new GridLayout(2, 2, 0, 0));
			panelNorte.add(getPanelCarrito());
			panelNorte.add(getPanel_1_1());
		}
		return panelNorte;
	}

	private JLabel getLblNombreArticulo() {
		if (lblNombreArticulo == null) {
			lblNombreArticulo = new JLabel("");
			lblNombreArticulo.setForeground(Color.BLACK);
			lblNombreArticulo.setFont(new Font("Gadugi", Font.BOLD, 28));
		}
		return lblNombreArticulo;
	}

	private JPanel getPanelPrevisualizar() {
		if (panelPrevisualizar == null) {
			panelPrevisualizar = new JPanel();
			panelPrevisualizar.setLayout(new GridLayout(2, 2, 30, 0));
			panelPrevisualizar.add(getPanelVisual());
			panelPrevisualizar.add(getScrollPaneDescripcion());
		}
		return panelPrevisualizar;
	}

	private JScrollPane getScrollPaneDescripcion() {
		if (scrollPaneDescripcion == null) {
			scrollPaneDescripcion = new JScrollPane();
			scrollPaneDescripcion.setForeground(Color.PINK);
			scrollPaneDescripcion.setViewportView(getTextAreaDescripcion());
		}
		return scrollPaneDescripcion;
	}

	private JTextArea getTextAreaDescripcion() {
		if (textAreaDescripcion == null) {
			textAreaDescripcion = new JTextArea();
			textAreaDescripcion.setLineWrap(true);
			textAreaDescripcion.setEditable(false);
			textAreaDescripcion.setFont(new Font("Gadugi", Font.PLAIN, 21));
			textAreaDescripcion.setForeground(Color.BLACK);
			textAreaDescripcion.setBackground(Color.WHITE);
		}
		return textAreaDescripcion;
	}

	private JPanel getPanelAñadirCarrito() {
		if (panelAñadirCarrito == null) {
			panelAñadirCarrito = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelAñadirCarrito.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelAñadirCarrito.setBackground(Color.PINK);
			panelAñadirCarrito.add(getBtnAadirAlCarrito());
			panelAñadirCarrito.add(getBtnFinalizarPedido());
			panelAñadirCarrito.add(getBtnCancelarPedido());
		}
		return panelAñadirCarrito;
	}

	private JPanel getPanelVisual() {
		if (panelVisual == null) {
			panelVisual = new JPanel();
			panelVisual.setBackground(Color.PINK);
			panelVisual.setLayout(new GridLayout(0, 2, 30, 0));
			panelVisual.add(getPanel_2());
			panelVisual.add(getPanel_1());
		}
		return panelVisual;
	}

	private JButton getBtnAadirAlCarrito() {
		if (btnAadirAlCarrito == null) {
			btnAadirAlCarrito = new JButton("A\u00F1adir al Carrito");
			btnAadirAlCarrito.setBackground(Color.WHITE);
			btnAadirAlCarrito.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btnAadirAlCarrito.setEnabled(false);
			btnAadirAlCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(listaProductos.getSelectedValue() !=null) {
					
					if(carrito.isContent(listaProductos.getSelectedValue())) {
						listaProductos.getSelectedValue().incrementarUnidades((int) spinner.getValue());
						carrito.añadirArticulo(listaProductos.getSelectedValue());
					}else {
						listaProductos.getSelectedValue().setUnidades((int) spinner.getValue());
						carrito.añadirArticulo(listaProductos.getSelectedValue());
					}
				//	carrito.añadirArticulo(listaProductos.getSelectedValue());
					btnFinalizarPedido.setEnabled(true);
					carrito.permitir(true);
					}
				}
			});
		}
		return btnAadirAlCarrito;
	}

	public void permitir(boolean flag) {
		btnFinalizarPedido.setEnabled(flag);
	}

	private JPanel getPanel_1() {
		if (panelFoto == null) {
			panelFoto = new JPanel();
			panelFoto.setBackground(Color.PINK);
			panelFoto.setLayout(new BorderLayout(0, 0));
			panelFoto.add(getLblFoto(), BorderLayout.CENTER);
		}
		return panelFoto;
	}

	private JPanel getPanel_2() {
		if (panelNumeros == null) {
			panelNumeros = new JPanel();
			panelNumeros.setLayout(new GridLayout(1, 0, 0, 1));
			panelNumeros.add(getPanel_4());
		}
		return panelNumeros;
	}

	private JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("");
			lblFoto.setBorder(null);
			lblFoto.setBackground(Color.PINK);
			lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/logoParTy.png")));
		}
		return lblFoto;
	}

	private JPanel getPanel_4() {
		if (panelUnidades == null) {
			panelUnidades = new JPanel();
			panelUnidades.setBackground(Color.PINK);
			panelUnidades.setFont(new Font("Gadugi", Font.BOLD, 27));
			panelUnidades.setLayout(new GridLayout(2, 1, 0, 0));
			panelUnidades.add(getPanelUnitario());
			panelUnidades.add(getPanelGrupal());
		}
		return panelUnidades;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setFont(new Font("Gadugi", Font.PLAIN, 15));
			label.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mi Carrito",
					TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					crearCarrito();
				}
			});
			label.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/carritoCompra.png")));
		}
		return label;
	}

	private void crearCarrito() {
		carrito.setVisible(true);
	}

	private JPanel getPanelFiltrado() {
		if (panelFiltrado == null) {
			panelFiltrado = new JPanel();
			panelFiltrado.setBackground(Color.PINK);
			panelFiltrado.setLayout(new GridLayout(5, 2, 0, 0));
			panelFiltrado.add(getRdbtnComida());
			panelFiltrado.add(getRdbtnBebida());
			panelFiltrado.add(getRdbtnDecoracion());
			panelFiltrado.add(getRdbtnLocal());
			panelFiltrado.add(getRdbtnOtros());
		}
		return panelFiltrado;
	}

	private JRadioButton getRdbtnComida() {
		if (rdbtnComida == null) {
			rdbtnComida = new JRadioButton("Comida");
			rdbtnComida.setForeground(Color.BLACK);
			rdbtnComida.setFont(new Font("Gadugi", Font.PLAIN, 16));
			rdbtnComida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// listaProductos.removeAll();
					btnAadirAlCarrito.setEnabled(false);
					spinner.setEnabled(true);
					modeloListProductos.removeAllElements();
					textAreaDescripcion.setText("");
					lblNombreArticulo.setText("");
					textFieldPrecioUnitario.setText("");
					lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/logoParTy.png")));
					lblElPrecioEs.setVisible(false);
					List<Productos> comida = administracion.getComida();
					for (int i = 0; i < comida.size(); i++) {
						modeloListProductos.addElement(comida.get(i));
					}
					cambiarModelo(true);
				}
			});
			rdbtnComida.setBackground(Color.PINK);
			buttonGroup.add(rdbtnComida);
		}
		return rdbtnComida;
	}

	private JRadioButton getRdbtnBebida() {
		if (rdbtnBebida == null) {
			rdbtnBebida = new JRadioButton("Bebida");
			rdbtnBebida.setForeground(Color.BLACK);
			rdbtnBebida.setFont(new Font("Gadugi", Font.PLAIN, 16));
			rdbtnBebida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAadirAlCarrito.setEnabled(false);
					spinner.setEnabled(true);
					modeloListProductos.removeAllElements();
					textAreaDescripcion.setText("");
					lblNombreArticulo.setText("");
					textFieldPrecioUnitario.setText("");
					lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/logoParTy.png")));
					textFieldPrecioUnitario.setText("");
					lblElPrecioEs.setVisible(false);
					List<Productos> bebidas = administracion.getBebida();
					for (int i = 0; i < bebidas.size(); i++) {
						modeloListProductos.addElement(bebidas.get(i));
					}
					cambiarModelo(true);
				}
			});
			rdbtnBebida.setBackground(Color.PINK);
			buttonGroup.add(rdbtnBebida);
		}
		return rdbtnBebida;
	}
	
	public void restart() {
		textAreaDescripcion.setText("");
		lblNombreArticulo.setText("");
		textFieldPrecioUnitario.setText("");
		lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/logoParTy.png")));
		textFieldPrecioUnitario.setText("");
	}

	private JRadioButton getRdbtnDecoracion() {
		if (rdbtnDecoracion == null) {
			rdbtnDecoracion = new JRadioButton("Decoracion");
			rdbtnDecoracion.setForeground(Color.BLACK);
			rdbtnDecoracion.setFont(new Font("Gadugi", Font.PLAIN, 16));
			rdbtnDecoracion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAadirAlCarrito.setEnabled(false);
					spinner.setEnabled(false);
					modeloListProductos.removeAllElements();
					textAreaDescripcion.setText("");
					lblNombreArticulo.setText("");
					textFieldPrecioUnitario.setText("");
					lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/logoParTy.png")));
					textFieldPrecioUnitario.setText("");
					lblElPrecioEs.setVisible(true);
					List<Productos> decoraciones = administracion.getDecoraciones();
					for (int i = 0; i < decoraciones.size(); i++) {
						modeloListProductos.addElement(decoraciones.get(i));
					}
					cambiarModelo(false);
				}
			});
			rdbtnDecoracion.setBackground(Color.PINK);
			buttonGroup.add(rdbtnDecoracion);
		}
		return rdbtnDecoracion;
	}

	private JRadioButton getRdbtnLocal() {
		if (rdbtnLocal == null) {
			rdbtnLocal = new JRadioButton("Local");
			rdbtnLocal.setForeground(Color.BLACK);
			rdbtnLocal.setFont(new Font("Gadugi", Font.PLAIN, 16));
			rdbtnLocal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnAadirAlCarrito.setEnabled(false);
					spinner.setEnabled(false);
					modeloListProductos.removeAllElements();
					textAreaDescripcion.setText("");
					lblNombreArticulo.setText("");
					textFieldPrecioUnitario.setText("");
					lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/logoParTy.png")));
					seleccionarLocales();
				}
			});
			rdbtnLocal.setBackground(Color.PINK);
			buttonGroup.add(rdbtnLocal);
		}
		return rdbtnLocal;
	}

	private JRadioButton getRdbtnOtros() {
		if (rdbtnOtros == null) {
			rdbtnOtros = new JRadioButton("Otros");
			rdbtnOtros.setForeground(Color.BLACK);
			rdbtnOtros.setFont(new Font("Gadugi", Font.PLAIN, 16));
			rdbtnOtros.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAadirAlCarrito.setEnabled(false);
					spinner.setEnabled(false);
					modeloListProductos.removeAllElements();
					textAreaDescripcion.setText("");
					lblNombreArticulo.setText("");
					textFieldPrecioUnitario.setText("");
					lblFoto.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/logoParTy.png")));
					textFieldPrecioUnitario.setText("");
					List<Productos> otros = administracion.getOtros();
					lblElPrecioEs.setVisible(true);
					for (int i = 0; i < otros.size(); i++) {
						modeloListProductos.addElement(otros.get(i));
					}
					cambiarModelo(false);
				}
			});
			rdbtnOtros.setBackground(Color.PINK);
			buttonGroup.add(rdbtnOtros);
		}
		return rdbtnOtros;
	}

	private JButton getBtnFinalizarPedido() {
		if (btnFinalizarPedido == null) {
			btnFinalizarPedido = new JButton("Finalizar pedido");
			btnFinalizarPedido.setEnabled(false);
			btnFinalizarPedido.setBackground(Color.WHITE);
			btnFinalizarPedido.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btnFinalizarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearVentanaFinal();
				}
			});
		}
		return btnFinalizarPedido;
	}

	public void crearVentanaFinal() {
		vfinal = new VentanaFinal(localizacion, carrito, this, administracion);
		carrito.rellenarFinal();
		vfinal.setVisible(true);
		this.dispose();

	}

	private JButton getBtnCancelarPedido() {
		if (btnCancelarPedido == null) {
			btnCancelarPedido = new JButton("Cancelar Pedido");
			btnCancelarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelar();
				}
			});
			btnCancelarPedido.setBackground(Color.WHITE);
			btnCancelarPedido.setFont(new Font("Gadugi", Font.PLAIN, 16));
		}
		return btnCancelarPedido;
	}

	public void cancelar() {
		carrito.dispose();
		main.setVisible(true);
		salir();
	}

	private void salir() {
		this.dispose();
	}

	public void seleccionarLocales() {
		rdbtnLocal.setSelected(true);
		spinner.setEnabled(false);
		List<Productos> locales = administracion.getLocales();
		textFieldPrecioUnitario.setText("");
		lblElPrecioEs.setVisible(true);
		for (int i = 0; i < locales.size(); i++) {
			modeloListProductos.addElement(locales.get(i));
		}
		cambiarModelo(false);
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio : ");
			lblPrecio.setLabelFor(getTextFieldPrecioUnitario());
			lblPrecio.setForeground(Color.BLACK);
			lblPrecio.setFont(new Font("Gadugi", Font.PLAIN, 22));
		}
		return lblPrecio;
	}

	private JTextField getTextFieldPrecioUnitario() {
		if (textFieldPrecioUnitario == null) {
			textFieldPrecioUnitario = new JTextField();
			textFieldPrecioUnitario.setFont(new Font("Gadugi", Font.BOLD, 19));
			textFieldPrecioUnitario.setEditable(false);
			textFieldPrecioUnitario.setColumns(10);
		}
		return textFieldPrecioUnitario;
	}

	private JPanel getPanelCarrito() {
		if (panelCarrito == null) {
			panelCarrito = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelCarrito.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelCarrito.setBackground(Color.PINK);
			panelCarrito.add(getLabel());
		}
		return panelCarrito;
	}

	private JPanel getPanel_1_1() {
		if (panelNombre == null) {
			panelNombre = new JPanel();
			panelNombre.setBackground(Color.PINK);
			panelNombre.add(getLblNombreArticulo());
		}
		return panelNombre;
	}

	public VentanaCarrito getCarrito() {
		return carrito;
	}

	private JPanel getPanelGrupal() {
		if (panelGrupal == null) {
			panelGrupal = new JPanel();
			panelGrupal.setBackground(Color.PINK);
			panelGrupal.setLayout(new GridLayout(0, 1, 0, 0));
			panelGrupal.add(getPanelPrecio());
		}
		return panelGrupal;
	}

	private JPanel getPanelUnitario() {
		if (panelUnitario == null) {
			panelUnitario = new JPanel();
			panelUnitario.setBackground(Color.PINK);
			panelUnitario.add(getLblPrecio());
			panelUnitario.add(getTextFieldPrecioUnitario());
			panelUnitario.add(getLblElPrecioEs());
		}
		return panelUnitario;
	}

	private JLabel getLblElPrecioEs() {
		if (lblElPrecioEs == null) {
			lblElPrecioEs = new JLabel("El precio es por cada 10 invitados");
			lblElPrecioEs.setForeground(Color.BLACK);
			lblElPrecioEs.setFont(new Font("Gadugi", Font.ITALIC, 16));
		}
		return lblElPrecioEs;
	}

	private JPanel getPanelPrecio() {
		if (panelPrecio == null) {
			panelPrecio = new JPanel();
			panelPrecio.setBackground(Color.PINK);
			panelPrecio.setLayout(new GridLayout(0, 2, 0, 0));
			panelPrecio.add(getPanel_5());
			panelPrecio.add(getPanel_3());
		}
		return panelPrecio;
	}

	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("Unidades  :");
			lblUnidades.setLabelFor(getSpinner());
			lblUnidades.setForeground(Color.BLACK);
			lblUnidades.setFont(new Font("Gadugi", Font.PLAIN, 22));
		}
		return lblUnidades;
	}

	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(0, 0, 1, 1));
			// spinner.setModel(new SpinnerNumberModel(0, 0, 1, 1));
		}
		return spinner;
	}

	private void cambiarModelo(Boolean botonSeleccionado) {
		if (botonSeleccionado) {
			spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		} else {
			spinner.setModel(new SpinnerNumberModel(1, 1, 1, 1));
		}

	}

	private JPanel getPanel_3() {
		if (panelSpinner == null) {
			panelSpinner = new JPanel();
			panelSpinner.setBackground(Color.PINK);
			panelSpinner.setLayout(new GridLayout(4, 2, 0, 0));
			panelSpinner.add(getSpinner());
		}
		return panelSpinner;
	}

	private JPanel getPanel_5() {
		if (panelLabelUds == null) {
			panelLabelUds = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelLabelUds.getLayout();
			flowLayout.setVgap(0);
			panelLabelUds.setBackground(Color.PINK);
			panelLabelUds.add(getLblUnidades());
		}
		return panelLabelUds;
	}

	private JPanel getPanelAtras() {
		if (panelAtras == null) {
			panelAtras = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelAtras.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelAtras.setBackground(Color.PINK);
			panelAtras.add(getLabelAtras());
		}
		return panelAtras;
	}

	private JPanel getPanelImportante() {
		if (panelImportante == null) {
			panelImportante = new JPanel();
			panelImportante.setLayout(new GridLayout(2, 1, 0, 0));
			panelImportante.add(getScrollPaneLista());
			panelImportante.add(getPanelFiltrado());
		}
		return panelImportante;
	}

	private JLabel getLabelAtras() {
		if (labelAtras == null) {
			labelAtras = new JLabel("");
			labelAtras.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					main.setVisible(true);
					// System.out.println(administracion.getBebida());
					administracion.vaciarListas();
					ocultar();
				}

			});
			labelAtras.setIcon(new ImageIcon(VentanaProductos.class.getResource("/img/atras.png")));
		}
		return labelAtras;
	}

	private void ocultar() {
		carrito.dispose();
		this.dispose();

	}

	private void localizar(Locale localizacion) {
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		administracion.leerProductos(textos.getString("ficheroProductos"));
		setTitle(textos.getString("tituloVentanaProductos"));
		label.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				textos.getString("tituloCarritoProductos"), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));

		lblPrecio.setText(textos.getString("labelPrecio"));
		lblPrecio.setDisplayedMnemonic(textos.getString("mnemonicoPrecio").charAt(0));

		btnAadirAlCarrito.setText(textos.getString("botonAnadir"));
		btnAadirAlCarrito.setMnemonic(textos.getString("mnemonicoAnadir").charAt(0));
		btnAadirAlCarrito.setToolTipText(textos.getString("tootlipAnadir"));

		btnFinalizarPedido.setText(textos.getString("botonFinalizar"));
		btnFinalizarPedido.setMnemonic(textos.getString("mnemonicoFinalizar").charAt(0));
		btnFinalizarPedido.setToolTipText(textos.getString("tootltipFinalizar"));

		btnCancelarPedido.setText(textos.getString("botonCancelar"));
		btnCancelarPedido.setMnemonic(textos.getString("mnemonicoCancelar").charAt(0));
		btnCancelarPedido.setToolTipText(textos.getString("tootlipCancelar"));

		rdbtnOtros.setText(textos.getString("botonOtros"));
		rdbtnOtros.setMnemonic(textos.getString("mnemonicoOtros").charAt(0));
		rdbtnOtros.setToolTipText(textos.getString("tootlipOtros"));

		rdbtnComida.setText(textos.getString("botonComida"));
		rdbtnComida.setMnemonic(textos.getString("mnemonicoComida").charAt(0));
		rdbtnComida.setToolTipText(textos.getString("tooltipComida"));

		rdbtnBebida.setText(textos.getString("botonBebida"));
		rdbtnBebida.setMnemonic(textos.getString("mnemonicoBebida").charAt(0));
		rdbtnBebida.setToolTipText(textos.getString("tootltipBebida"));

		rdbtnDecoracion.setText(textos.getString("botonDecoracion"));
		rdbtnDecoracion.setMnemonic(textos.getString("mnemonicoDecoracion").charAt(0));
		rdbtnDecoracion.setToolTipText(textos.getString("tooltipDecoracion"));

		rdbtnLocal.setText(textos.getString("botonLocal"));
		rdbtnLocal.setMnemonic(textos.getString("mnemonicoLocal").charAt(0));
		rdbtnLocal.setToolTipText(textos.getString("tootlipLocales"));

		lblUnidades.setText(textos.getString("labelUnidades"));
		lblUnidades.setDisplayedMnemonic(textos.getString("mnemonicoUnidades").charAt(0));

		lblElPrecioEs.setText(textos.getString("labelElPrecio"));
	}
}
