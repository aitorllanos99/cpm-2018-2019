package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import logic.Administracion;
import logic.Cliente;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaParaRegistrarse extends JDialog {

	private Administracion administracion;
	private VentanaPrincipal main;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelInferior;
	private JButton btnRegistrarse;
	private JButton btnCancelar;
	private JPanel panelBotones;
	private JPanel panelRegistro;
	private JPanel panelDatosPersonales;
	private ResourceBundle textos;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblApellidos;
	private JTextField textFieldApellidos;
	private JPanel panelDatosContacto;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblTelefono;
	private JTextField textFieldTelefono;
	private JPanel panelDniNif;
	private JLabel lblDescuentoPorRegistro;
	private JPanel panelUsuarios;
	private JPanel panelSexo;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblUsuario;
	private JTextField textFieldUsuario;
	private JPanel panelContraseñas;
	private JLabel lblContraseña;
	private JLabel lblRepetirContraseña;
	private JPasswordField fieldContraseña;
	private JPasswordField fieldRepContra;
	private JLabel label;
	private JLabel lblDninif;
	private JLabel labelAs1;
	private JLabel labelAs2;
	private JLabel labelAs3;
	private JLabel lblAs4;
	private JTextField textFieldDNI;
	private JLabel lblLosCamposMarcados;
	private JLabel labelApellidosAs;
	private JLabel labelDNIAS;
	private String mensajeExito;
	private String tituloExito;
	private String mensajeErrorUsuario;
	private String tituloErrorUsuario;
	private String mensajeErrorContraseña;
	private String tituloErrorContraseña;
	/**
	 * Create the frame.
	 */
	public VentanaParaRegistrarse(VentanaPrincipal main, Locale localizacion, Administracion administracion) {
		setFont(new Font("Gadugi", Font.PLAIN, 14));
		this.administracion = administracion;
		this.main = main;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaParaRegistrarse.class.getResource("/img/logoParTy.png")));
		setTitle("Par.Ty: Ventana de Registro");
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1076, 573);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelInferior(), BorderLayout.SOUTH);
		contentPane.add(getPanelRegistro(), BorderLayout.CENTER);
		setLocationRelativeTo(main);
		localizar(localizacion);
		getRootPane().setDefaultButton(btnRegistrarse);
		
	}

	
	private JPanel getPanelInferior() {
		if (panelInferior == null) {
			panelInferior = new JPanel();
			panelInferior.setBackground(Color.WHITE);
			panelInferior.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			panelInferior.setLayout(new BorderLayout(0, 0));
			panelInferior.add(getPanelBotones(), BorderLayout.EAST);
		}
		return panelInferior;
	}
	private JButton getBtnRegistrarse() {
		if (btnRegistrarse == null) {
			btnRegistrarse = new JButton("Registrarse");
			btnRegistrarse.setBackground(Color.WHITE);
			btnRegistrarse.setFont(new Font("Gadugi", Font.PLAIN, 15));
			//btnRegistrarse.setEnabled(false);
			btnRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(comprobarBlancos() && comprobarContraseñas()) {
						//btnRegistrarse.setEnabled(true);
						
						Cliente registrado = new Cliente(textFieldNombre.getText(), textFieldApellidos.getText(),textFieldEmail.getText(),textFieldTelefono.getText(), textFieldDNI.getText(),textFieldUsuario.getText(),
									fieldContraseña.getText());
						if(comprobarUsuario(registrado)) {
							main.cambiarIcono(true);
							salir();
							JOptionPane.showMessageDialog(main, mensajeExito, tituloExito, JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			getRootPane().setDefaultButton(btnRegistrarse);
			}
		return btnRegistrarse;
	}
	
	private VentanaParaRegistrarse ventana() {
		return this;
	}
	
	private void salir() {
		this.dispose();
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setFont(new Font("Gadugi", Font.PLAIN, 15));
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelar();
				}
			});
		}
		return btnCancelar;
	}
	
	private void cancelar() {
		this.dispose();
		
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setBackground(Color.WHITE);
			panelBotones.add(getBtnRegistrarse());
			panelBotones.add(getBtnCancelar());
		}
		return panelBotones;
	}
	private JPanel getPanelRegistro() {
		if (panelRegistro == null) {
			panelRegistro = new JPanel();
			panelRegistro.setBackground(Color.WHITE);
			panelRegistro.setLayout(null);
			panelRegistro.add(getPanelDatosPersonales());
			panelRegistro.add(getPanelUsuarios());
			panelRegistro.add(getLblDescuentoPorRegistro());
			panelRegistro.add(getLabel());
			panelRegistro.add(getLblLosCamposMarcados());
		}
		return panelRegistro;
	}
	private JPanel getPanelDatosPersonales() {
		if (panelDatosPersonales == null) {
			panelDatosPersonales = new JPanel();
			panelDatosPersonales.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelDatosPersonales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DatosPersonales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			panelDatosPersonales.setBackground(Color.WHITE);
			panelDatosPersonales.setBounds(0, 26, 1060, 206);
			panelDatosPersonales.setLayout(null);
			panelDatosPersonales.add(getLblNombre());
			panelDatosPersonales.add(getTextFieldNombre());
			panelDatosPersonales.add(getLblApellidos());
			panelDatosPersonales.add(getTextFieldApellidos());
			panelDatosPersonales.add(getPanelDatosContacto());
			panelDatosPersonales.add(getPanelDniNif());
			panelDatosPersonales.add(getPanelSexo());
			panelDatosPersonales.add(getLabelAs1());
			panelDatosPersonales.add(getLabelApellidosAs());
		}
		return panelDatosPersonales;
	}
	

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre: ");
			lblNombre.setLabelFor(getTextFieldNombre());
			lblNombre.setFont(new Font("Gadugi", Font.PLAIN, 20));
			lblNombre.setBounds(12, 31, 103, 27);
		}
		return lblNombre;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(106, 36, 155, 22);
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setLabelFor(getTextFieldApellidos());
			lblApellidos.setFont(new Font("Gadugi", Font.PLAIN, 20));
			lblApellidos.setBounds(341, 31, 126, 27);
		}
		return lblApellidos;
	}
	private JTextField getTextFieldApellidos() {
		if (textFieldApellidos == null) {
			textFieldApellidos = new JTextField();
			textFieldApellidos.setBounds(463, 37, 222, 22);
			textFieldApellidos.setColumns(10);
		}
		return textFieldApellidos;
	}
	private JPanel getPanelDatosContacto() {
		if (panelDatosContacto == null) {
			panelDatosContacto = new JPanel();
			panelDatosContacto.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelDatosContacto.setBorder(new TitledBorder(null, "Datos de Contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatosContacto.setBackground(Color.WHITE);
			panelDatosContacto.setBounds(12, 78, 410, 115);
			panelDatosContacto.setLayout(null);
			panelDatosContacto.add(getLblEmail());
			panelDatosContacto.add(getTextFieldEmail());
			panelDatosContacto.add(getLblTelefono());
			panelDatosContacto.add(getTextFieldTelefono());
		}
		return panelDatosContacto;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("E-Mail:");
			lblEmail.setLabelFor(getTextFieldEmail());
			lblEmail.setFont(new Font("Gadugi", Font.PLAIN, 16));
			lblEmail.setBackground(Color.WHITE);
			lblEmail.setBounds(12, 30, 77, 16);
		}
		return lblEmail;
	}
	private JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(101, 27, 285, 22);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}
	private JLabel getLblTelefono() {
		if (lblTelefono == null) {
			lblTelefono = new JLabel("Telefono:");
			lblTelefono.setLabelFor(getTextFieldTelefono());
			lblTelefono.setFont(new Font("Gadugi", Font.PLAIN, 16));
			lblTelefono.setBounds(12, 70, 77, 16);
		}
		return lblTelefono;
	}
	private JTextField getTextFieldTelefono() {
		if (textFieldTelefono == null) {
			textFieldTelefono = new JTextField();
			textFieldTelefono.setBounds(101, 69, 285, 22);
			textFieldTelefono.setColumns(10);
		}
		return textFieldTelefono;
	}
	private JPanel getPanelDniNif() {
		if (panelDniNif == null) {
			panelDniNif = new JPanel();
			panelDniNif.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelDniNif.setBorder(new TitledBorder(null, "Fecha de Nacimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDniNif.setBackground(Color.WHITE);
			panelDniNif.setBounds(463, 78, 361, 115);
			panelDniNif.setLayout(null);
			panelDniNif.add(getLblDninif());
			panelDniNif.add(getTextFieldDNI());
			panelDniNif.add(getLabelDNIAS());
		}
		return panelDniNif;
	}
	
	private void localizar(Locale localizacion) {
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		setTitle(textos.getString("tituloRegistro"));		
		panelDatosPersonales.setBorder(new TitledBorder(null, textos.getString("nombrePanelDatosPersonales"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosPersonales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), textos.getString("tituloDatosPersonales"), TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelUsuarios.setBorder(new TitledBorder(null, textos.getString("tituloDatosUsuario"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSexo.setBorder(new TitledBorder(null, textos.getString("tituloSexo"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDniNif.setBorder(new TitledBorder(null, textos.getString("tituloDniNif"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosContacto.setBorder(new TitledBorder(null, textos.getString("tituloDatosDeContacto"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelContraseñas.setBorder(new TitledBorder(null, textos.getString("tituloContrasena"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnRegistrarse.setText(textos.getString("botonRegistrarse"));
		btnRegistrarse.setMnemonic(textos.getString("mnemonicoRegistrese").charAt(0));
		btnRegistrarse.setToolTipText(textos.getString("tootlipReigstrese"));
		
		btnCancelar.setText(textos.getString("botonCancelarRegistrarse"));
		btnCancelar.setMnemonic(textos.getString("mnemonicoCancelar").charAt(0));
		btnCancelar.setToolTipText(textos.getString("tootltipCancelarRegistrarse"));
		
		lblEmail.setText(textos.getString("labelE-mail"));
		lblEmail.setDisplayedMnemonic(textos.getString("mnemonicoEmail").charAt(0));
		
		lblTelefono.setText(textos.getString("labelTelefono"));
		lblTelefono.setDisplayedMnemonic(textos.getString("mnemonicoTelefono").charAt(0));
		
		lblNombre.setText(textos.getString("labelNombre"));
		lblNombre.setDisplayedMnemonic(textos.getString("mnemonicoNombre").charAt(0));
		
		lblApellidos.setText(textos.getString("labelApellido"));
		lblApellidos.setDisplayedMnemonic(textos.getString("mnemonicoApellidos").charAt(0));
		
		lblDescuentoPorRegistro.setText(textos.getString("labelDescuento"));
		lblDescuentoPorRegistro.setDisplayedMnemonic(textos.getString("labelNombre").charAt(0));
		
		rdbtnHombre.setText(textos.getString("botonHombre"));
		rdbtnHombre.setMnemonic(textos.getString("mnemonicoHombre").charAt(0));
		
		rdbtnMujer.setText(textos.getString("botonMujer"));
		rdbtnMujer.setMnemonic(textos.getString("mnemonicoMujer").charAt(0));

		lblUsuario.setText(textos.getString("labelUsuario"));
		lblUsuario.setDisplayedMnemonic(textos.getString("mnemonicoUsuarioRegistro").charAt(0));
		
		lblContraseña.setText(textos.getString("labelContrasena"));
		lblContraseña.setDisplayedMnemonic(textos.getString("mnemonicoContraRegistro").charAt(0));
		
		lblRepetirContraseña.setText(textos.getString("labelRepetirContrasena"));
		lblRepetirContraseña.setDisplayedMnemonic(textos.getString("mnemonicoRepContra").charAt(0));
		
		lblDninif.setText(textos.getString("labelDninif"));
		lblDninif.setDisplayedMnemonic(textos.getString("mnemonicoDNI").charAt(0));
		
		mensajeExito = textos.getString("mensajeExito");
		tituloExito = textos.getString("tituloExito");
		mensajeErrorUsuario = textos.getString("mensajeErrorUsuario");
		tituloErrorUsuario = textos.getString("tituloErrorUsuario");
		mensajeErrorContraseña = textos.getString("mensajeErrorContrasena");
		tituloErrorContraseña = textos.getString("tituloErrorContrasena");
	}
	private JLabel getLblDescuentoPorRegistro() {
		if (lblDescuentoPorRegistro == null) {
			lblDescuentoPorRegistro = new JLabel("");
			lblDescuentoPorRegistro.setBounds(22, 471, 838, 16);
			lblDescuentoPorRegistro.setFont(new Font("Gadugi", Font.BOLD, 13));
			lblDescuentoPorRegistro.setForeground(Color.GREEN);
		}
		return lblDescuentoPorRegistro;
	}
	private JPanel getPanelUsuarios() {
		if (panelUsuarios == null) {
			panelUsuarios = new JPanel();
			panelUsuarios.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelUsuarios.setBorder(new TitledBorder(null, "Datos Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelUsuarios.setBackground(Color.WHITE);
			panelUsuarios.setBounds(12, 267, 550, 200);
			panelUsuarios.setLayout(null);
			panelUsuarios.add(getLblUsuario());
			panelUsuarios.add(getTextFieldUsuario());
			panelUsuarios.add(getPanelContraseñas());
			panelUsuarios.add(getLabelAs2());
		}
		return panelUsuarios;
	}
	private JPanel getPanelSexo() {
		if (panelSexo == null) {
			panelSexo = new JPanel();
			panelSexo.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelSexo.setBackground(Color.WHITE);
			panelSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelSexo.setBounds(852, 31, 168, 147);
			panelSexo.setLayout(null);
			panelSexo.add(getRdbtnHombre());
			panelSexo.add(getRdbtnMujer());
		}
		return panelSexo;
	}
	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("Hombre");
			rdbtnHombre.setFont(new Font("Gadugi", Font.PLAIN, 14));
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setBackground(Color.WHITE);
			rdbtnHombre.setBounds(8, 28, 127, 25);
		}
		return rdbtnHombre;
	}
	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("Mujer");
			rdbtnMujer.setFont(new Font("Gadugi", Font.PLAIN, 14));
			buttonGroup.add(rdbtnMujer);
			rdbtnMujer.setBackground(Color.WHITE);
			rdbtnMujer.setBounds(8, 68, 127, 25);
		}
		return rdbtnMujer;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(new Font("Gadugi", Font.PLAIN, 20));
			lblUsuario.setBounds(37, 30, 100, 34);
		}
		return lblUsuario;
	}
	private JTextField getTextFieldUsuario() {
		if (textFieldUsuario == null) {
			textFieldUsuario = new JTextField();
			textFieldUsuario.setBounds(136, 40, 307, 22);
			textFieldUsuario.setColumns(10);
		}
		return textFieldUsuario;
	}
	private JPanel getPanelContraseñas() {
		if (panelContraseñas == null) {
			panelContraseñas = new JPanel();
			panelContraseñas.setFont(new Font("Gadugi", Font.PLAIN, 14));
			panelContraseñas.setBorder(new TitledBorder(null, "Contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelContraseñas.setBackground(Color.WHITE);
			panelContraseñas.setBounds(12, 70, 526, 117);
			panelContraseñas.setLayout(null);
			panelContraseñas.add(getLblContraseña());
			panelContraseñas.add(getLblRepetirContraseña());
			panelContraseñas.add(getFieldContraseña());
			panelContraseñas.add(getFieldRepContra());
			panelContraseñas.add(getLabelAs3());
			panelContraseñas.add(getLblAs4());
		}
		return panelContraseñas;
	}
	private JLabel getLblContraseña() {
		if (lblContraseña == null) {
			lblContraseña = new JLabel("Contrase\u00F1a:");
			lblContraseña.setFont(new Font("Gadugi", Font.PLAIN, 16));
			lblContraseña.setBounds(31, 33, 82, 16);
		}
		return lblContraseña;
	}
	private JLabel getLblRepetirContraseña() {
		if (lblRepetirContraseña == null) {
			lblRepetirContraseña = new JLabel("Repetir Contrase\u00F1a:");
			lblRepetirContraseña.setFont(new Font("Gadugi", Font.PLAIN, 16));
			lblRepetirContraseña.setBounds(12, 68, 139, 19);
		}
		return lblRepetirContraseña;
	}
	private JPasswordField getFieldContraseña() {
		if (fieldContraseña == null) {
			fieldContraseña = new JPasswordField();
			fieldContraseña.setBounds(163, 31, 310, 19);
		}
		return fieldContraseña;
	}
	private JPasswordField getFieldRepContra() {
		if (fieldRepContra == null) {
			fieldRepContra = new JPasswordField();
			fieldRepContra.setBounds(163, 68, 310, 19);
		}
		return fieldRepContra;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(VentanaParaRegistrarse.class.getResource("/img/logoParTy.png")));
			label.setBounds(672, 258, 315, 189);
		}
		return label;
	}
	private JLabel getLblDninif() {
		if (lblDninif == null) {
			lblDninif = new JLabel("DNI/NIF:");
			lblDninif.setLabelFor(getTextFieldDNI());
			lblDninif.setFont(new Font("Gadugi", Font.PLAIN, 16));
			lblDninif.setBounds(12, 41, 68, 16);
		}
		return lblDninif;
	}
	private JLabel getLabelAs1() {
		if (labelAs1 == null) {
			labelAs1 = new JLabel("*");
			labelAs1.setForeground(Color.WHITE);
			labelAs1.setFont(new Font("Tahoma", Font.BOLD, 13));
			labelAs1.setBounds(262, 26, 28, 27);
		}
		return labelAs1;
	}
	private JLabel getLabelAs2() {
		if (labelAs2 == null) {
			labelAs2 = new JLabel("*");
			labelAs2.setFont(new Font("Tahoma", Font.BOLD, 13));
			labelAs2.setForeground(Color.WHITE);
			labelAs2.setBounds(447, 30, 56, 16);
		}
		return labelAs2;
	}
	private JLabel getLabelAs3() {
		if (labelAs3 == null) {
			labelAs3 = new JLabel("*");
			labelAs3.setForeground(Color.WHITE);
			labelAs3.setBounds(470, 13, 56, 16);
		}
		return labelAs3;
	}
	private JLabel getLblAs4() {
		if (lblAs4 == null) {
			lblAs4 = new JLabel("*");
			lblAs4.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblAs4.setForeground(Color.WHITE);
			lblAs4.setBounds(483, 54, 56, 16);
		}
		return lblAs4;
	}
	private JTextField getTextFieldDNI() {
		if (textFieldDNI == null) {
			textFieldDNI = new JTextField();
			textFieldDNI.setBounds(92, 40, 240, 22);
			textFieldDNI.setColumns(10);
		}
		return textFieldDNI;
	}
	private JLabel getLblLosCamposMarcados() {
		if (lblLosCamposMarcados == null) {
			lblLosCamposMarcados = new JLabel("Los campos marcados con un * son obligatorios de rellenar");
			lblLosCamposMarcados.setForeground(Color.WHITE);
			lblLosCamposMarcados.setFont(new Font("Gadugi", Font.BOLD, 15));
			lblLosCamposMarcados.setBounds(56, 0, 660, 29);
		}
		return lblLosCamposMarcados;
	}
	
	
	private boolean comprobarBlancos() {
		if(textFieldNombre.getText().isEmpty() || textFieldApellidos.getText().isEmpty() || textFieldDNI.getText().isEmpty() ||  textFieldUsuario.getText().isEmpty() ||fieldContraseña.getText().isEmpty() || fieldRepContra.getText().isEmpty() ) {
			labelAs1.setForeground(Color.RED);
			labelAs2.setForeground(Color.RED);
			labelAs3.setForeground(Color.RED);
			lblAs4.setForeground(Color.RED);
			labelApellidosAs.setForeground(Color.RED);
			labelDNIAS.setForeground(Color.RED);
			lblLosCamposMarcados.setForeground(Color.RED);
			return false;
		}else {
			labelAs1.setForeground(Color.WHITE);
			labelAs2.setForeground(Color.WHITE);
			labelAs3.setForeground(Color.WHITE);
			lblAs4.setForeground(Color.WHITE);
			labelApellidosAs.setForeground(Color.WHITE);
			labelDNIAS.setForeground(Color.WHITE);
			lblLosCamposMarcados.setForeground(Color.WHITE);
			return true;
		}
		
	}
	

	private boolean comprobarContraseñas() {
		if(fieldContraseña.getText().equals(fieldRepContra.getText())) {
			return true;
		}
		JOptionPane.showMessageDialog(this,mensajeErrorContraseña ,tituloErrorContraseña, JOptionPane.ERROR_MESSAGE);
		labelAs3.setForeground(Color.RED);
		lblAs4.setForeground(Color.RED);
		return false;
	} 
	
	private boolean comprobarUsuario(Cliente registrado) {
		if(administracion.comprobarClientes(registrado.getUsuario())) {
			textFieldUsuario.setText("");
			JOptionPane.showMessageDialog(this,mensajeErrorUsuario ,tituloErrorUsuario, JOptionPane.ERROR_MESSAGE);
			labelAs2.setForeground(Color.RED);
			return false;
		}else {
			administracion.registrarCliente(registrado);
			administracion.setClienteActual(registrado);
			main.setRegistrado(true);
			return true;
		}
	
	}
	private JLabel getLabelApellidosAs() {
		if (labelApellidosAs == null) {
			labelApellidosAs = new JLabel("*");
			labelApellidosAs.setForeground(Color.WHITE);
			labelApellidosAs.setBounds(683, 26, 56, 16);
		}
		return labelApellidosAs;
	}
	private JLabel getLabelDNIAS() {
		if (labelDNIAS == null) {
			labelDNIAS = new JLabel("*");
			labelDNIAS.setForeground(Color.WHITE);
			labelDNIAS.setBounds(325, 24, 56, 16);
		}
		return labelDNIAS;
	}
}
