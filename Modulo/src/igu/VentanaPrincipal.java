package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;

import logic.Administracion;

import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

import javax.swing.border.TitledBorder;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import javax.swing.UIManager;

public class VentanaPrincipal extends JFrame {

	private VentanaProductos productos;
	private VentanaIntegrantes invitados;
	private Administracion administracion;
	private VentanaParaRegistrarse ventanaRegistro;
	private boolean registrado;
	private VentanaRegistro registro;
	private VentanaFinal vfinal;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPrincipalNorte;
	private JPanel panelPrincipalSur;
	private JPanel panelMenu;
	private JLabel lblDesde0;
	private JLabel lblTiposDeFiesta;
	private JLabel lblProductos;
	private JPanel panelIconos;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton buttonBandera;
	private ResourceBundle textos;
	private JButton buttonRegistro;
	private JPanel panelCentral;
	private JPanel panelSuperior;
	private JSeparator separator;
	private JLabel labelLogo;
	private JPanel panelCentro;
	private Locale localizacion;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JTextArea textAreaInferior;
	private JPanel panelBanderas;
	private JButton buttonInglesa;
	private JLabel lblParty;
	private JLabel lblTerminosYCondiciones;
	private JLabel lblAvisoLegal;
	private JSeparator separator_1;
	private String tituloTyC;
	private String mensajeTyC;
	private String mensajeCopyP;
	private String tituloCopyP;
	private String mensajeAviso;
	private String tituloAviso;
	private JButton botonAyuda;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties p = new Properties();
					p.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(p);
					UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setFont(new Font("Gadugi", Font.PLAIN, 14));
		this.administracion = new Administracion();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoParTy.png")));
		registrado = false;
		setTitle("ParTy: ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1173, 748);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelPrincipalNorte(), BorderLayout.NORTH);
		contentPane.add(getPanelCentral(), BorderLayout.CENTER);
		contentPane.add(getPanelPrincipalSur(), BorderLayout.SOUTH);
		localizar(Locale.getDefault(Locale.Category.FORMAT));
		cargarAyuda();
		
	}
	public boolean isRegistrado() {
		return registrado;
	}

	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}
	private JPanel getPanelPrincipalNorte() {
		if (panelPrincipalNorte == null) {
			panelPrincipalNorte = new JPanel();
			panelPrincipalNorte.setBackground(Color.PINK);
			panelPrincipalNorte.setLayout(new GridLayout(0, 2, 0, 0));
			panelPrincipalNorte.add(getPanelMenu());
			panelPrincipalNorte.add(getPanelIconos());
		}
		return panelPrincipalNorte;
	}
	private JPanel getPanelPrincipalSur() {
		if (panelPrincipalSur == null) {
			panelPrincipalSur = new JPanel();
			panelPrincipalSur.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelPrincipalSur.setBackground(Color.WHITE);
			panelPrincipalSur.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
			panelPrincipalSur.add(getLblParty());
			panelPrincipalSur.add(getScrollPane());
			panelPrincipalSur.add(getLblTerminosYCondiciones());
			panelPrincipalSur.add(getSeparator_1());
			panelPrincipalSur.add(getLblAvisoLegal());
		}
		return panelPrincipalSur;
	}
	private JPanel getPanelMenu() {
		if (panelMenu == null) {
			panelMenu = new JPanel();
			panelMenu.setBorder(new LineBorder(new Color(255, 0, 0)));
			panelMenu.setBackground(Color.WHITE);
			panelMenu.setLayout(new GridLayout(0, 4, 0, 0));
			panelMenu.add(getLblDesde0());
			panelMenu.add(getLblTiposDeFiesta());
			panelMenu.add(getLblProductos());
			panelMenu.add(getBotonAyuda());
		}
		return panelMenu;
	}
	private JLabel getLblDesde0() {
		if (lblDesde0 == null) {
			lblDesde0 = new JLabel("Desde 0");
			lblDesde0.setHorizontalAlignment(SwingConstants.CENTER);
			lblDesde0.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					de0();
				}
			});
			lblDesde0.setBackground(Color.CYAN);
			lblDesde0.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
			lblDesde0.setForeground(Color.RED);
			lblDesde0.setDisplayedMnemonic(KeyEvent.VK_0);
			lblDesde0.setFont(new Font("Gadugi", Font.BOLD, 24));
		}
		return lblDesde0;
	}
	
	private void de0() {
		invitados = new VentanaIntegrantes(localizacion,this,administracion);
		invitados.setVisible(true);
	}
	private JLabel getLblTiposDeFiesta() {
		if (lblTiposDeFiesta == null) {
			lblTiposDeFiesta = new JLabel("Fiestas");
			lblTiposDeFiesta.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					crearProductos();
					productos.seleccionarLocales();
					
					
				}
			});
			lblTiposDeFiesta.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
			lblTiposDeFiesta.setDisplayedMnemonic('F');
			lblTiposDeFiesta.setHorizontalAlignment(SwingConstants.CENTER);
			lblTiposDeFiesta.setForeground(Color.RED);
			lblTiposDeFiesta.setFont(new Font("Gadugi", Font.BOLD, 24));
		}
		return lblTiposDeFiesta;
	}
	private void ocultar() {
		this.setVisible(false);
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos");
			lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
			lblProductos.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					crearProductos();
				}
			});
			lblProductos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
			lblProductos.setDisplayedMnemonic('P');
			lblProductos.setFont(new Font("Gadugi", Font.BOLD, 24));
			lblProductos.setForeground(Color.RED);
		}
		return lblProductos;
	}
	public void cerrarSesion() {
		buttonRegistro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoNoRegistrado.jpg")));
	}
	public Administracion getAdministracion() {
		return administracion;
	}

	private void crearProductos() {
		productos = new VentanaProductos(this, localizacion,administracion);
		ocultar();
		productos.setVisible(true);
	}
	private JPanel getPanelIconos() {
		if (panelIconos == null) {
			panelIconos = new JPanel();
			panelIconos.setBorder(new LineBorder(Color.RED));
			panelIconos.setBackground(Color.WHITE);
			panelIconos.setLayout(new GridLayout(0, 2, 0, 0));
			panelIconos.add(getPanelBanderas());
			panelIconos.add(getButtonRegistro());
		}
		return panelIconos;
	}
	private JButton getButtonBandera() {
		if (buttonBandera == null) {
			buttonBandera = new JButton("");
			buttonBandera.setToolTipText("Haz click para cambiar el idioma a Ingles");
			buttonBandera.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/espa\u00F1a.jpg")));
			buttonBandera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						localizar(new Locale("es"));
				}
			});
			buttonBandera.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
			buttonBandera.setBackground(Color.WHITE);
		}
		return buttonBandera;
	}
	
	private void localizar(Locale localizacion) {
		this.localizacion = localizacion;
		//System.out.println(localizacion);
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		setTitle(textos.getString("tituloPrincipal"));
		
		buttonBandera.setMnemonic(textos.getString("mnemonicoEspana").charAt(0));
		buttonBandera.setToolTipText(textos.getString("tooltipBanderaEspana"));
		
		buttonInglesa.setMnemonic(textos.getString("mnemonicoInglesa").charAt(0));
		buttonInglesa.setToolTipText(textos.getString("tooltipBanderaIngles"));
		
		lblDesde0.setText(textos.getString("labelD0"));
		String mnemonicoD0 = textos.getString("mnemonicoD0");
		lblDesde0.setDisplayedMnemonic(mnemonicoD0.charAt(0));
		lblDesde0.setToolTipText(textos.getString("tooltipD0"));
		
		lblTiposDeFiesta.setText(textos.getString("labelFiestas"));
		String mnemonicoFiestas = textos.getString("mnemonicoFiestas");
		lblTiposDeFiesta.setDisplayedMnemonic(mnemonicoFiestas.charAt(0));
		lblTiposDeFiesta.setToolTipText(textos.getString("tootltipFiestas"));
		
		lblProductos.setText(textos.getString("labelProductos"));
		String mnemonicoProductos = textos.getString("mnemonicoProductos");
		lblProductos.setDisplayedMnemonic(mnemonicoProductos.charAt(0));
		lblProductos.setToolTipText(textos.getString("tooltipProductos"));
		
		botonAyuda.setText(textos.getString("labelAyuda"));
		String mnemonicoAyuda = textos.getString("mnemonicoAyuda");
		botonAyuda.setMnemonic(mnemonicoAyuda.charAt(0));
		botonAyuda.setToolTipText(textos.getString("tooltipAyuda"));
		
		lblParty.setText(textos.getString("labelParTy"));
		lblParty.setDisplayedMnemonic(textos.getString("mnemonicoParTy").charAt(0));
		
		lblTerminosYCondiciones.setText(textos.getString("labelTerminos"));
		lblTerminosYCondiciones.setDisplayedMnemonic(textos.getString("mnemonicoTerminos").charAt(0));
		
		lblAvisoLegal.setText(textos.getString("labelAviso"));
		lblAvisoLegal.setDisplayedMnemonic(textos.getString("mnemonicoAviso").charAt(0));
		
		if(registrado) {
			buttonRegistro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), textos.getString("bordeRegistrado"), TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		}else {
			buttonRegistro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), textos.getString("bordeRegistro"), TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		}
		mensajeTyC = textos.getString("mensajeTyC");
		tituloTyC = textos.getString("tituloTyC");
		mensajeCopyP = textos.getString("mensajeCopyP");
		tituloCopyP = textos.getString("tituloCopyP"); 
		mensajeAviso = textos.getString("mensajeAviso");
		tituloAviso = textos.getString("tituloAviso");
	}
	private JButton getButtonRegistro() {
		if (buttonRegistro == null) {
			buttonRegistro = new JButton("");
			buttonRegistro.setFont(new Font("Gadugi", Font.PLAIN, 14));
			buttonRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(registrado) {
						crearBienvenida();
					}else {
						crearRegistro();
					}
				}
			});
			buttonRegistro.setToolTipText("Haz Click para registrarte");
			buttonRegistro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registrarse", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
			buttonRegistro.setBackground(Color.WHITE);
			buttonRegistro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoNoRegistrado.jpg")));
		}
		return buttonRegistro;
	}
	
	public void cambiarIcono(Boolean registred) {
		if(registred) {
			buttonRegistro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoRegistrado.jpg")));
			buttonRegistro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), textos.getString("bordeRegistrado"), TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));

		}else {
			buttonRegistro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoNoRegistrado.jpg")));
			buttonRegistro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), textos.getString("bordeRegistro"), TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		}
	}
	private void crearBienvenida() {
		VentanaBienvenida bienvenida = new VentanaBienvenida(this, administracion.getClienteActual().getUsuario(),localizacion);
		bienvenida.setVisible(true);
	}
	private void crearRegistro() {
		registro = new VentanaRegistro(this, localizacion, administracion);
		registro.setVisible(true);
	}
	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new BorderLayout(0, 0));
			panelCentral.add(getPanelSuperior(), BorderLayout.NORTH);
			panelCentral.add(getPanelCentro(), BorderLayout.CENTER);
		}
		return panelCentral;
	}
	private JPanel getPanelSuperior() {
		if (panelSuperior == null) {
			panelSuperior = new JPanel();
			panelSuperior.setBackground(Color.PINK);
			panelSuperior.setLayout(new GridLayout(0, 3, 0, 0));
			panelSuperior.add(getSeparator());
			panelSuperior.add(getLabelLogo());
		}
		return panelSuperior;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBackground(Color.PINK);
		}
		return separator;
	}
	private JLabel getLabelLogo() {
		if (labelLogo == null) {
			labelLogo = new JLabel("");
			labelLogo.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					
				}
			});
			labelLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logoParTy.png")));
		}
		return labelLogo;
	}
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBackground(Color.PINK);
			panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 40));
			panelCentro.add(getLabel());
			panelCentro.add(getLblNewLabel());
			panelCentro.add(getLblNewLabel_1());
		}
		return panelCentro;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/fiesta3g.png")));
		}
		return label;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/fiesta1.png")));
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/fiesta2.jpg")));
		}
		return lblNewLabel_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setViewportView(getTextAreaInferior());
		}
		return scrollPane;
	}
	private JTextArea getTextAreaInferior() {
		if (textAreaInferior == null) {
			textAreaInferior = new JTextArea();
			textAreaInferior.setFont(new Font("Gadugi", Font.PLAIN, 18));
			textAreaInferior.setBackground(Color.WHITE);
		}
		return textAreaInferior;
	}
	private JPanel getPanelBanderas() {
		if (panelBanderas == null) {
			panelBanderas = new JPanel();
			panelBanderas.setLayout(new GridLayout(0, 2, 0, 0));
			panelBanderas.add(getButtonBandera());
			panelBanderas.add(getButtonInglesa());
		}
		return panelBanderas;
	}
	private JButton getButtonInglesa() {
		if (buttonInglesa == null) {
			buttonInglesa = new JButton("");
			buttonInglesa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					localizar(new Locale("en"));
				}
			});
			buttonInglesa.setBackground(Color.WHITE);
			buttonInglesa.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ingles.jpg")));
		}
		return buttonInglesa;
	}
	private JLabel getLblParty() {
		if (lblParty == null) {
			lblParty = new JLabel("ParTy 2018 \u00A9");
			lblParty.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					copyParty();
				}
			});
			lblParty.setFont(new Font("Gadugi", Font.PLAIN, 15));
		}
		return lblParty;
	}
	
	private void copyParty() {
		JOptionPane.showMessageDialog(this,mensajeCopyP,tituloCopyP,JOptionPane.INFORMATION_MESSAGE);
	}
	private JLabel getLblTerminosYCondiciones() {
		if (lblTerminosYCondiciones == null) {
			lblTerminosYCondiciones = new JLabel("Terminos y condiciones");
			lblTerminosYCondiciones.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					mostarTyC();
				}
			});
			lblTerminosYCondiciones.setFont(new Font("Gadugi", Font.PLAIN, 15));
		}
		return lblTerminosYCondiciones;
	}
	
	private void mostarTyC() {
		JOptionPane.showMessageDialog(this, mensajeTyC, tituloTyC, JOptionPane.INFORMATION_MESSAGE);
	}
	private JLabel getLblAvisoLegal() {
		if (lblAvisoLegal == null) {
			lblAvisoLegal = new JLabel("Aviso Legal");
			lblAvisoLegal.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					mostrarAviso();
				}
			});
			lblAvisoLegal.setFont(new Font("Gadugi", Font.PLAIN, 15));
		}
		return lblAvisoLegal;
	}
	
	private void mostrarAviso() {
		JOptionPane.showMessageDialog(this, mensajeAviso, tituloAviso, JOptionPane.INFORMATION_MESSAGE);
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setForeground(Color.WHITE);
			separator_1.setBackground(Color.WHITE);
		}
		return separator_1;
	}
	
	
	private void cargarAyuda() {
		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);

		} catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		//hb.setLocation(this);
		hb.setSize(this.getSize());
		hb.enableHelpKey(getRootPane(), "introduccion", hs);
		hb.enableHelpOnButton(botonAyuda, "introduccion", hs);
		//hb.enableHelp(productos, "productos", hs);
	//	hb.enableHelp(productos, "locales", hs);
	//	hb.enableHelp(productos, "filtrarProductos", hs);
	}
	private JButton getBotonAyuda() {
		if (botonAyuda == null) {
			botonAyuda = new JButton("");
			botonAyuda.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
			botonAyuda.setForeground(Color.RED);
			botonAyuda.setBackground(Color.WHITE);
			botonAyuda.setFont(new Font("Gadugi", Font.BOLD, 24));
		}
		return botonAyuda;
	}
}
