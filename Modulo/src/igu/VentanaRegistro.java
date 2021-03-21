package igu;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;

import logic.Administracion;
import logic.Cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class VentanaRegistro extends JDialog {

	private VentanaPrincipal main;
	private Administracion administracion;
	private JPanel contentPane;
	private JButton botonIS;
	private JLabel lblUsuario;
	private JTextField textFieldUsuario;
	private JLabel lblContrasena;
	private JPanel panelNoRegistrado;
	private JLabel lblaunNoTiene;
	private JLabel lblRegistrese;
	private JSeparator separator;
	private JSeparator separator_1;
	private VentanaParaRegistrarse ventanaRegistrarse;
	private Locale localizacion;
	private ResourceBundle textos;
	private String mensajeError;
	private String tituloError;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public VentanaRegistro(VentanaPrincipal main, Locale localizacion, Administracion administracion) {
		this.administracion = administracion;
		this.localizacion = localizacion;
		this.main = main;
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/img/logoParTy.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 374, 483);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBotonIS());
		contentPane.add(getLblUsuario());
		contentPane.add(getTextFieldUsuario());
		contentPane.add(getLblContrasena());
		contentPane.add(getPanelNoRegistrado());
		setLocationRelativeTo(main);
		localizar(localizacion);
		getRootPane().setDefaultButton(botonIS);
		contentPane.add(getPasswordField());
	}
	private JButton getBotonIS() {
		if (botonIS == null) {
			botonIS = new JButton("Inciar Sesion");
			botonIS.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(administracion.comprobarClientes(textFieldUsuario.getText())) {
						registradoConExito();
					}else {
						registradoSinExito();
					}
					}
			});
			botonIS.setFont(new Font("Gadugi", Font.BOLD, 18));
			botonIS.setForeground(Color.BLACK);
			botonIS.setBorder(new LineBorder(Color.RED));
			botonIS.setBackground(Color.WHITE);
			botonIS.setBounds(58, 249, 223, 40);
		}
		return botonIS;
	}
	
	private void registradoConExito() {
		main.setRegistrado(true);
		main.cambiarIcono(true);
		Cliente cliente = new Cliente(null,null,null,null,null,textFieldUsuario.getText(),passwordField.getText());
		administracion.setClienteActual(cliente);
		this.dispose();
	}

	private void registradoSinExito() {
		JOptionPane.showMessageDialog(this, mensajeError, tituloError, JOptionPane.ERROR_MESSAGE);
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario : ");
			lblUsuario.setLabelFor(getTextFieldUsuario());
			lblUsuario.setForeground(Color.BLACK);
			lblUsuario.setFont(new Font("Gadugi", Font.BOLD, 19));
			lblUsuario.setBounds(12, 54, 123, 40);
		}
		return lblUsuario;
	}
	private JTextField getTextFieldUsuario() {
		if (textFieldUsuario == null) {
			textFieldUsuario = new JTextField();
			textFieldUsuario.setFont(new Font("Gadugi", Font.PLAIN, 15));
			textFieldUsuario.setBounds(142, 66, 202, 22);
			textFieldUsuario.setColumns(10);
		}
		return textFieldUsuario;
	}
	private JLabel getLblContrasena() {
		if (lblContrasena == null) {
			lblContrasena = new JLabel("Contrase\u00F1a :");
			lblContrasena.setLabelFor(getPasswordField());
			lblContrasena.setForeground(Color.BLACK);
			lblContrasena.setFont(new Font("Gadugi", Font.BOLD, 19));
			lblContrasena.setBounds(12, 107, 118, 40);
		}
		return lblContrasena;
	}
	private JPanel getPanelNoRegistrado() {
		if (panelNoRegistrado == null) {
			panelNoRegistrado = new JPanel();
			panelNoRegistrado.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelNoRegistrado.setBackground(Color.PINK);
			panelNoRegistrado.setBounds(0, 416, 368, 32);
			panelNoRegistrado.add(getLblaunNoTiene());
			panelNoRegistrado.add(getSeparator());
			panelNoRegistrado.add(getSeparator_1());
			panelNoRegistrado.add(getLblRegistrese());
		}
		return panelNoRegistrado;
	}
	private JLabel getLblaunNoTiene() {
		if (lblaunNoTiene == null) {
			lblaunNoTiene = new JLabel("\u00BFAun no tiene cuenta?");
			lblaunNoTiene.setFont(new Font("Gadugi", Font.PLAIN, 14));
		}
		return lblaunNoTiene;
	}
	private JLabel getLblRegistrese() {
		if (lblRegistrese == null) {
			lblRegistrese = new JLabel("Registrese");
			lblRegistrese.setLabelFor(lblRegistrese);
			lblRegistrese.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					registrarse();
				}
			});
			
			lblRegistrese.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
			lblRegistrese.setForeground(Color.BLACK);
			
			lblRegistrese.setFont(new Font("Gadugi", Font.BOLD, 14));
		}
		return lblRegistrese;
	}
	private void registrarse() {
		ventanaRegistrarse = new VentanaParaRegistrarse(main, localizacion,administracion);
		this.dispose();
		ventanaRegistrarse.setVisible(true);
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.WHITE);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	
	private void localizar(Locale localizacion) {
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		lblRegistrese.setText(textos.getString("labelRegistrese"));
		lblRegistrese.setDisplayedMnemonic(textos.getString("mnemonicoRegistrese").charAt(0));
		lblaunNoTiene.setText(textos.getString("labelAunNoCuenta"));
		lblContrasena.setText(textos.getString("labelContrasenaRegistro"));
		lblContrasena.setDisplayedMnemonic(textos.getString("mnemonicoContra").charAt(0));
		lblUsuario.setText(textos.getString("labelUsuarioRegistro"));
		lblUsuario.setDisplayedMnemonic(textos.getString("mnemonicoUsuario").charAt(0));
		setTitle(textos.getString("tituloVentanaRegistro"));
		botonIS.setText(textos.getString("botonIS"));
		botonIS.setMnemonic(textos.getString("mnemonicoIS").charAt(0));
		botonIS.setToolTipText(textos.getString("tootlipIS"));
		mensajeError = textos.getString("mensajeError");
		tituloError = textos.getString("tituloError");
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setFont(new Font("Gadugi", Font.PLAIN, 15));
			passwordField.setBounds(142, 119, 202, 22);
		}
		return passwordField;
	}
}
