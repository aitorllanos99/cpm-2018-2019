package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Administracion;
import logic.Productos;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VentanaCarrito extends JFrame {

	private ResourceBundle textos;
	private VentanaProductos productos;
	private Locale localizacion;
	private Administracion administracion;
	private VentanaFinal vfinal;
	private VentanaPrincipal main;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCarrito;
	private JPanel panelTotal;
	private JScrollPane scrollPane;
	private JPanel panelVisualizacion;
	private JPanel panelIntegrantes;
	private JList<Productos> listaCarrito;
	private JPanel panelArticulo;
	private JPanel panelVisual;
	private JLabel lblIntegrantes;
	private JTextField textField;
	private JPanel panelLabels;
	private JButton btnModificar;
	private JLabel lblNombreArticulo;
	private JPanel panelBotones;
	private JPanel panelFotoUds;
	private JPanel panelDescripcion;
	private JPanel panelUnidades;
	private JPanel panelFoto;
	private JLabel label;
	private JScrollPane scrollPane_1;
	private JTextArea textAreaDescripcion;
	private JButton btnFinalizarPedido;
	private JButton btnCancelarPedido;
	private JButton btnEliminarArticulo;
	private DefaultListModel<Productos> modeloListCarrito;
	private JPanel panelUds;
	private JPanel panelPrecios;
	private JPanel panelLista;
	private JButton btnVaciarCarrito;
	private JPanel panelVaciarCarro;
	private JLabel lblPrecio;
	private JTextField textFieldPrecio;
	private JLabel lblUnidades;
	private JTextField textFieldUnidades;
	private JPanel panelAtras;
	private JLabel labelAtras;
	private JPanel panelModificar;
	private JLabel lblTotal;
	private JTextField textFieldTotal;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public VentanaCarrito(VentanaProductos productos, Locale localizacion, Administracion administracion,
			VentanaPrincipal main) {
		setFont(new Font("Gadugi", Font.PLAIN, 14));
		this.administracion = administracion;
		this.localizacion = localizacion;
		this.productos = productos;
		this.main = main;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCarrito.class.getResource("/img/logoParTy.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 980, 734);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelCarrito(), BorderLayout.CENTER);
		contentPane.add(getPanelTotal(), BorderLayout.SOUTH);
		contentPane.add(getPanelIntegrantes(), BorderLayout.NORTH);
		localizar(localizacion);
		getRootPane().setDefaultButton(btnFinalizarPedido);
	}

	private JPanel getPanelCarrito() {
		if (panelCarrito == null) {
			panelCarrito = new JPanel();
			panelCarrito.setLayout(new GridLayout(1, 0, 0, 0));
			panelCarrito.add(getPanelLista());
			panelCarrito.add(getPanelVisualizacion());
		}
		return panelCarrito;
	}

	private JPanel getPanelTotal() {
		if (panelTotal == null) {
			panelTotal = new JPanel();
			panelTotal.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelTotal;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListaCarrito());
		}
		return scrollPane;
	}

	public void añadirProductos(Productos producto) {
		// modeloTabla.addElement(producto);
	}

	private JPanel getPanelVisualizacion() {
		if (panelVisualizacion == null) {
			panelVisualizacion = new JPanel();
			panelVisualizacion.setLayout(new BorderLayout(0, 0));
			panelVisualizacion.add(getPanelArticulo(), BorderLayout.NORTH);
			panelVisualizacion.add(getPanelVisual(), BorderLayout.CENTER);
			panelVisualizacion.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return panelVisualizacion;
	}

	private JPanel getPanelIntegrantes() {
		if (panelIntegrantes == null) {
			panelIntegrantes = new JPanel();
			panelIntegrantes.setBorder(null);
			panelIntegrantes.setBackground(Color.PINK);
			panelIntegrantes.setLayout(new GridLayout(1, 2, 0, 0));
			panelIntegrantes.add(getPanelAtras());
			panelIntegrantes.add(getPanelModificar());
		}
		return panelIntegrantes;
	}

	private JList<Productos> getListaCarrito() {
		if (listaCarrito == null) {
			modeloListCarrito = new DefaultListModel<Productos>();
			listaCarrito = new JList<Productos>(modeloListCarrito);
			listaCarrito.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					if (listaCarrito.getSelectedValue() != null) {
						btnEliminarArticulo.setEnabled(true);
						Productos seleccionado = listaCarrito.getSelectedValue();
						textAreaDescripcion.setText(seleccionado.getDescripcion());
						lblNombreArticulo.setText(seleccionado.getProducto());
						label.setIcon(new ImageIcon(VentanaProductos.class.getResource(seleccionado.getFoto())));
						textFieldUnidades.setText("" + seleccionado.getUnidades());
						if (seleccionado.getCodigo().charAt(0) == 'C' || seleccionado.getCodigo().charAt(0) == 'B') {
							textFieldPrecio.setText("" + administracion.calcularPrecioUnitario(seleccionado) + " €");
						} else {
							textFieldPrecio.setText("" + administracion.calcularPrecioGrupal(seleccionado) + " €");
						}
					}
				}
			});
			listaCarrito.setFont(new Font("Gadugi", Font.BOLD, 16));
			listaCarrito.setBackground(Color.PINK);
		}
		return listaCarrito;
	}

	private JPanel getPanelArticulo() {
		if (panelArticulo == null) {
			panelArticulo = new JPanel();
			panelArticulo.setBackground(Color.PINK);
			panelArticulo.add(getLblNombreArticulo());
		}
		return panelArticulo;
	}

	private JPanel getPanelVisual() {
		if (panelVisual == null) {
			panelVisual = new JPanel();
			panelVisual.setBackground(Color.PINK);
			panelVisual.setLayout(new GridLayout(2, 1, 0, 0));
			panelVisual.add(getPanelFotoUds());
			panelVisual.add(getPanelDescripcion());
		}
		return panelVisual;
	}

	private JLabel getLblIntegrantes() {
		if (lblIntegrantes == null) {
			lblIntegrantes = new JLabel("Integrantes : ");
			lblIntegrantes.setLabelFor(getTextField());
			lblIntegrantes.setFont(new Font("Gadugi", Font.PLAIN, 20));
		}
		return lblIntegrantes;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Gadugi", Font.BOLD, 15));
			textField.setText("" + administracion.getInvitados());
			textField.setEditable(false);
			textField.setColumns(10);
		}
		return textField;
	}

	public void updateInvites() {
		textField.setText("" + administracion.getInvitados());
		List<Productos> comprados = new ArrayList<Productos>();
		for (int i = 0; i < modeloListCarrito.size(); i++) {
			comprados.add(modeloListCarrito.get(i));
		}
		textFieldTotal.setText("" + administracion.calcularPrecio(comprados) + "€");
	}

	private JPanel getPanelLabels() {
		if (panelLabels == null) {
			panelLabels = new JPanel();
			panelLabels.setBackground(Color.PINK);
			panelLabels.add(getLblIntegrantes());
			panelLabels.add(getTextField());
		}
		return panelLabels;
	}
	
	public boolean isContent(Productos producto) {
		for(int i=0;i<modeloListCarrito.size();i++) {
			if(modeloListCarrito.get(i).getCodigo().equals(producto.getCodigo())) {
				return true;
			}
		}
		return false;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setBackground(Color.WHITE);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaIntegrantes invitados = new VentanaIntegrantes(localizacion, main, administracion,
							productos);
					invitados.setVisible(true);
					updateInvites();
				}
			});
			btnModificar.setFont(new Font("Gadugi", Font.PLAIN, 16));
		}
		return btnModificar;
	}

	private JLabel getLblNombreArticulo() {
		if (lblNombreArticulo == null) {
			lblNombreArticulo = new JLabel();
			lblNombreArticulo.setFont(new Font("Gadugi", Font.BOLD, 28));
		}
		return lblNombreArticulo;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setBackground(Color.PINK);
			FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelBotones.add(getBtnEliminarArticulo());
			panelBotones.add(getBtnFinalizarPedido());
			panelBotones.add(getBtnCancelarPedido());
		}
		return panelBotones;
	}

	private JPanel getPanelFotoUds() {
		if (panelFotoUds == null) {
			panelFotoUds = new JPanel();
			panelFotoUds.setBackground(Color.PINK);
			panelFotoUds.setLayout(new GridLayout(0, 2, 0, 0));
			panelFotoUds.add(getPanelUnidades());
			panelFotoUds.add(getPanelFoto());
		}
		return panelFotoUds;
	}

	private JPanel getPanelDescripcion() {
		if (panelDescripcion == null) {
			panelDescripcion = new JPanel();
			panelDescripcion.setLayout(new GridLayout(1, 0, 0, 0));
			panelDescripcion.add(getScrollPane_1());
		}
		return panelDescripcion;
	}

	private JPanel getPanelUnidades() {
		if (panelUnidades == null) {
			panelUnidades = new JPanel();
			panelUnidades.setBackground(Color.PINK);
			panelUnidades.setLayout(new GridLayout(2, 1, 0, 0));
			panelUnidades.add(getPanelPrecios());
			panelUnidades.add(getPanelUds());
		}
		return panelUnidades;
	}

	private JPanel getPanelFoto() {
		if (panelFoto == null) {
			panelFoto = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelFoto.getLayout();
			flowLayout.setVgap(20);
			panelFoto.setBackground(Color.PINK);
			panelFoto.add(getLabel());
		}
		return panelFoto;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setVerticalAlignment(SwingConstants.BOTTOM);
			label.setIcon(new ImageIcon(VentanaCarrito.class.getResource("/img/logoParTy.png")));
		}
		return label;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTextAreaDescripcion());
		}
		return scrollPane_1;
	}

	private JTextArea getTextAreaDescripcion() {
		if (textAreaDescripcion == null) {
			textAreaDescripcion = new JTextArea();
			textAreaDescripcion.setLineWrap(true);
			textAreaDescripcion.setFont(new Font("Gadugi", Font.PLAIN, 21));
		}
		return textAreaDescripcion;
	}

	private JButton getBtnFinalizarPedido() {
		if (btnFinalizarPedido == null) {
			btnFinalizarPedido = new JButton("Finalizar Pedido");
			btnFinalizarPedido.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btnFinalizarPedido.setEnabled(false);
			btnFinalizarPedido.setBackground(Color.WHITE);
			btnFinalizarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					productos.crearVentanaFinal();
					productos.getVfinal().setVisible(true);
					productos.dispose();
					ocultar();
				}
			});
		}
		return btnFinalizarPedido;
	}

	private void borrarEliminados() {
		textAreaDescripcion.setText("");
		lblNombreArticulo.setText("");
		label.setIcon(new ImageIcon(VentanaCarrito.class.getResource("/img/logoParTy.png")));
		textFieldUnidades.setText("");
		textFieldPrecio.setText("" );
	}
	public void permitir(boolean b) {
		btnFinalizarPedido.setEnabled(b);
	}

	private void ocultar() {
		this.setVisible(false);
	}

	public void rellenarFinal() {
		for (int i = 0; i < modeloListCarrito.size(); i++) {
			productos.getVfinal().añadirArticulos(modeloListCarrito.getElementAt(i));
		}
	}

	private JButton getBtnCancelarPedido() {
		if (btnCancelarPedido == null) {
			btnCancelarPedido = new JButton("Cancelar pedido");
			btnCancelarPedido.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btnCancelarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<modeloListCarrito.size();i++) {
						modeloListCarrito.get(i).setUnidades(0);
					}
					modeloListCarrito.removeAllElements();
					permitir(false);
					productos.permitir(false);
					btnEliminarArticulo.setEnabled(false);
					btnVaciarCarrito.setEnabled(false);
					updateInvites();
					productos.restart();
					borrarEliminados();
					
					salir();
				}
			});
			btnCancelarPedido.setBackground(Color.WHITE);
		}
		return btnCancelarPedido;
	}

	private void salir() {
		this.dispose();
	}

	private JButton getBtnEliminarArticulo() {
		if (btnEliminarArticulo == null) {
			btnEliminarArticulo = new JButton("Eliminar Articulo");
			btnEliminarArticulo.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btnEliminarArticulo.setBackground(Color.WHITE);
			btnEliminarArticulo.setEnabled(false);
			btnEliminarArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (listaCarrito.getSelectedValue() != null) {
						listaCarrito.getSelectedValue().setUnidades(0);
						modeloListCarrito.removeElementAt(listaCarrito.getSelectedIndex());
						if (modeloListCarrito.isEmpty()) {
							permitir(false);
							productos.permitir(false);
							btnEliminarArticulo.setEnabled(false);
							btnVaciarCarrito.setEnabled(false);
						}
						updateInvites();
						borrarEliminados();
						productos.restart();
					}
				}
			});
		}
		return btnEliminarArticulo;
	}

	public boolean añadirArticulo(Productos producto) {
		List<Productos> comprados = new ArrayList<Productos>();
		if (!modeloListCarrito.isEmpty()) {
			for (int i = 0; i < modeloListCarrito.size(); i++) {
				if (modeloListCarrito.get(i).getCodigo().equals(producto.getCodigo())) {
					for (int j = 0; j < modeloListCarrito.size(); j++) {
						comprados.add(modeloListCarrito.get(j));
					}
					textFieldTotal.setText("" + administracion.calcularPrecio(comprados) + "€");
					return false;
				}
			}
			modeloListCarrito.addElement(producto);
			for (int i = 0; i < modeloListCarrito.size(); i++) {
				comprados.add(modeloListCarrito.get(i));
			}
			textFieldTotal.setText("" + administracion.calcularPrecio(comprados) + "€");
		} else {
			modeloListCarrito.addElement(producto);
			for (int i = 0; i < modeloListCarrito.size(); i++) {
				comprados.add(modeloListCarrito.get(i));
			}
			textFieldTotal.setText("" + administracion.calcularPrecio(comprados) + "€");
			btnEliminarArticulo.setEnabled(true);
			btnVaciarCarrito.setEnabled(true);
			return true;
		}
		return true;

	}

	public String getTotal() {
		return textFieldTotal.getText();
	}

	private JPanel getPanelUds() {
		if (panelUds == null) {
			panelUds = new JPanel();
			panelUds.setBackground(Color.PINK);
			panelUds.add(getLblUnidades());
			panelUds.add(getTextFieldUnidades());
		}
		return panelUds;
	}

	private JPanel getPanelPrecios() {
		if (panelPrecios == null) {
			panelPrecios = new JPanel();
			panelPrecios.setBackground(Color.PINK);
			panelPrecios.add(getLblPrecio());
			panelPrecios.add(getTextFieldPrecio());
		}
		return panelPrecios;
	}

	private JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setLayout(new BorderLayout(0, 0));
			panelLista.add(getScrollPane());
			panelLista.add(getPanelVaciarCarro(), BorderLayout.SOUTH);
		}
		return panelLista;
	}

	private JButton getBtnVaciarCarrito() {
		if (btnVaciarCarrito == null) {
			btnVaciarCarrito = new JButton("Vaciar Carrito");
			btnVaciarCarrito.setFont(new Font("Gadugi", Font.PLAIN, 16));
			btnVaciarCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int i=0;i<modeloListCarrito.size();i++) {
						modeloListCarrito.get(i).setUnidades(0);
					}
					modeloListCarrito.removeAllElements();
					permitir(false);
					productos.permitir(false);
					btnEliminarArticulo.setEnabled(false);
					btnVaciarCarrito.setEnabled(false);
					updateInvites();
					productos.restart();
					borrarEliminados();
				}
			});
			btnVaciarCarrito.setBackground(Color.WHITE);
			btnVaciarCarrito.setEnabled(false);
		}
		return btnVaciarCarrito;
	}

	private JPanel getPanelVaciarCarro() {
		if (panelVaciarCarro == null) {
			panelVaciarCarro = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelVaciarCarro.getLayout();
			flowLayout.setHgap(50);
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelVaciarCarro.setBackground(Color.PINK);
			panelVaciarCarro.add(getBtnVaciarCarrito());
			panelVaciarCarro.add(getPanel());
		}
		return panelVaciarCarro;
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setLabelFor(getTextFieldPrecio());
			lblPrecio.setFont(new Font("Gadugi", Font.PLAIN, 22));
		}
		return lblPrecio;
	}

	private JTextField getTextFieldPrecio() {
		if (textFieldPrecio == null) {
			textFieldPrecio = new JTextField();
			textFieldPrecio.setEditable(false);
			textFieldPrecio.setFont(new Font("Gadugi", Font.BOLD, 19));
			textFieldPrecio.setColumns(10);
		}
		return textFieldPrecio;
	}

	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("Unidades : ");
			lblUnidades.setLabelFor(getTextFieldUnidades());
			lblUnidades.setFont(new Font("Gadugi", Font.PLAIN, 22));
		}
		return lblUnidades;
	}

	private JTextField getTextFieldUnidades() {
		if (textFieldUnidades == null) {
			textFieldUnidades = new JTextField();
			textFieldUnidades.setEditable(false);
			textFieldUnidades.setFont(new Font("Gadugi", Font.BOLD, 19));
			textFieldUnidades.setColumns(10);
		}
		return textFieldUnidades;
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

	private JLabel getLabelAtras() {
		if (labelAtras == null) {
			labelAtras = new JLabel("");
			labelAtras.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					salir();
					productos.setVisible(true);
				}
			});
			labelAtras.setIcon(new ImageIcon(VentanaCarrito.class.getResource("/img/atras.png")));
		}
		return labelAtras;
	}

	private JPanel getPanelModificar() {
		if (panelModificar == null) {
			panelModificar = new JPanel();
			panelModificar.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelModificar.setBackground(Color.PINK);
			panelModificar.add(getPanelLabels());
			panelModificar.add(getBtnModificar());
		}
		return panelModificar;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total :");
			lblTotal.setLabelFor(getTextFieldTotal());
			lblTotal.setFont(new Font("Gadugi", Font.PLAIN, 16));
		}
		return lblTotal;
	}

	private JTextField getTextFieldTotal() {
		if (textFieldTotal == null) {
			textFieldTotal = new JTextField();
			textFieldTotal.setEditable(false);
			textFieldTotal.setFont(new Font("Gadugi", Font.BOLD, 16));
			textFieldTotal.setColumns(10);
		}
		return textFieldTotal;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.PINK);
			panel.add(getLblTotal());
			panel.add(getTextFieldTotal());
		}
		return panel;
	}

	private void localizar(Locale localizacion2) {
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		setTitle(textos.getString("tituloCarrito"));
		labelAtras.setToolTipText(textos.getString("tootlipAtrasCarrito"));

		lblIntegrantes.setDisplayedMnemonic(textos.getString("mnemonicosIntegrantes").charAt(0));
		lblIntegrantes.setText(textos.getString("labelIntegrantes"));

		btnModificar.setText(textos.getString("botonModificarCarrito"));
		btnModificar.setMnemonic(textos.getString("mnemonicoModificar").charAt(0));
		btnModificar.setToolTipText(textos.getString("tooltipModificarCarrito"));

		lblPrecio.setText(textos.getString("labelPrecio"));
		lblPrecio.setDisplayedMnemonic(textos.getString("mnemonicoPrecio").charAt(0));

		lblUnidades.setDisplayedMnemonic(textos.getString("mnemonicoUnidades").charAt(0));
		lblUnidades.setText(textos.getString("labelUnidades"));

		btnVaciarCarrito.setText(textos.getString("botonVaciarCarrito"));
		btnVaciarCarrito.setMnemonic(textos.getString("mnemonicoVaciarCarrito").charAt(0));
		btnVaciarCarrito.setToolTipText(textos.getString("tootltipVaciarCarrito"));

		lblTotal.setText(textos.getString("labelTotal"));
		lblTotal.setDisplayedMnemonic(textos.getString("mnemonicoTotal").charAt(0));

		btnEliminarArticulo.setText(textos.getString("botonEliminarArticulo"));
		btnEliminarArticulo.setMnemonic(textos.getString("mnemonicoEliminarArticulo").charAt(0));
		btnEliminarArticulo.setToolTipText(textos.getString("tooltipEliminarArticulo"));

		btnFinalizarPedido.setText(textos.getString("botonFinalizarCarrito"));
		btnFinalizarPedido.setMnemonic(textos.getString("mnemonicoFinalizarCarrito").charAt(0));
		btnFinalizarPedido.setToolTipText(textos.getString("tooltipFinalizarCarrito"));

		btnCancelarPedido.setText(textos.getString("botonCancelarCarrito"));
		btnCancelarPedido.setMnemonic(textos.getString("mnemonicoCancelarCarrito").charAt(0));
		btnCancelarPedido.setToolTipText(textos.getString("tootltipCancelarCarrito"));
	}

}
