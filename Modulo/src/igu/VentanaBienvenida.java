package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class VentanaBienvenida extends JDialog {

	private ResourceBundle textos;
	private Locale localizacion;
	private VentanaPrincipal main;
	private JPanel contentPane;
	private JButton btnCerrarSesion;
	private JButton btnAceptar;
	private JLabel lblBienvenidoPepen;
	private JLabel lblUsuario;
	private JLabel lblUnPlacerTenerte;



	/**
	 * Create the frame.
	 */
	public VentanaBienvenida(VentanaPrincipal main, String usuario, Locale localizacion) {
		setFont(new Font("Gadugi", Font.PLAIN, 14));
		this.main = main;
		this.localizacion = localizacion;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaBienvenida.class.getResource("/img/logoParTy.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 389, 237);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCerrarSesion());
		contentPane.add(getBtnAceptar());
		contentPane.add(getLblBienvenidoPepen());
		contentPane.add(getLblUsuario());
		contentPane.add(getLblUnPlacerTenerte());
		setLocationRelativeTo(main);
		lblUsuario.setText(usuario);
		localizar(localizacion);
		getRootPane().setDefaultButton(btnAceptar);
	}
	private JButton getBtnCerrarSesion() {
		if (btnCerrarSesion == null) {
			btnCerrarSesion = new JButton("Cerrar Sesion");
			btnCerrarSesion.setBackground(Color.WHITE);
			btnCerrarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					main.setRegistrado(false);
					main.cerrarSesion();
					main.getAdministracion().setClienteActual(null);
					main.cambiarIcono(false);
				}
			});
			btnCerrarSesion.setFont(new Font("Gadugi", Font.PLAIN, 15));
			btnCerrarSesion.setBounds(167, 118, 147, 38);
		}
		return btnCerrarSesion;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setBackground(Color.WHITE);
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
			btnAceptar.setFont(new Font("Gadugi", Font.PLAIN, 15));
			btnAceptar.setBounds(12, 118, 143, 38);
		}
		return btnAceptar;
	}
	
	private void salir() {
		this.dispose();
	}
	private JLabel getLblBienvenidoPepen() {
		if (lblBienvenidoPepen == null) {
			lblBienvenidoPepen = new JLabel("Bienvenido ");
			lblBienvenidoPepen.setFont(new Font("Gadugi", Font.BOLD, 21));
			lblBienvenidoPepen.setBounds(32, 28, 123, 29);
		}
		return lblBienvenidoPepen;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("");
			lblUsuario.setFont(new Font("Gadugi", Font.BOLD, 21));
			lblUsuario.setBounds(167, 28, 147, 29);
		}
		return lblUsuario;
	}
	private JLabel getLblUnPlacerTenerte() {
		if (lblUnPlacerTenerte == null) {
			lblUnPlacerTenerte = new JLabel("Un placer tenerte de vuelta");
			lblUnPlacerTenerte.setFont(new Font("Gadugi", Font.BOLD, 21));
			lblUnPlacerTenerte.setBounds(32, 67, 327, 38);
		}
		return lblUnPlacerTenerte;
	}
	private void localizar(Locale localizacion) {
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		setTitle(textos.getString("tituloBienvenida"));
		lblUnPlacerTenerte.setText(textos.getString("labelPlacer"));
		lblBienvenidoPepen.setText(textos.getString("labelBienvenido"));
		btnAceptar.setText(textos.getString("botonAceptar"));
		btnAceptar.setMnemonic(textos.getString("mnemonicoAceptar").charAt(0));
		btnAceptar.setToolTipText(textos.getString("tootlipAceptar"));
		
		btnCerrarSesion.setText(textos.getString("botonCerrarSesion"));
		btnCerrarSesion.setToolTipText(textos.getString("tooltipCerrarSesion"));
		btnCerrarSesion.setMnemonic(textos.getString("mnemonicoCerrarSesion").charAt(0));
	}
}
