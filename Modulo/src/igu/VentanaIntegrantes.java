package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Administracion;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaIntegrantes extends JDialog {
	
	private Locale localizacion;
	private ResourceBundle textos;
	private VentanaPrincipal main;
	private Administracion administracion;
	private VentanaProductos productos;
	private JPanel contentPane;
	private JLabel lblPorFavorIndique;
	private JSpinner spinner;
	private JButton btnContinuar;


	/**
	 * Create the frame.
	 */
	public VentanaIntegrantes(Locale localizacion, VentanaPrincipal main, Administracion administracion) {
		setFont(new Font("Gadugi", Font.PLAIN, 14));
		this.localizacion = localizacion;
		this.main = main;
		this.administracion = administracion;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaIntegrantes.class.getResource("/img/logoParTy.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 291, 205);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPorFavorIndique());
		contentPane.add(getSpinner());
		contentPane.add(getBtnContinuar());
		setLocationRelativeTo(main);
		localizar(localizacion);
		getRootPane().setDefaultButton(btnContinuar);
	}
	
	
	public VentanaIntegrantes(Locale localizacion, VentanaPrincipal main, Administracion administracion, VentanaProductos productos) {
		this.localizacion = localizacion;
		this.main = main;
		this.administracion = administracion;
		this.productos =  productos;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaIntegrantes.class.getResource("/img/logoParTy.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 291, 205);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPorFavorIndique());
		contentPane.add(getSpinner());
		contentPane.add(getBtnContinuar());
		setLocationRelativeTo(main);
		localizar(localizacion);
		getRootPane().setDefaultButton(btnContinuar);
	}
	

	private JLabel getLblPorFavorIndique() {
		if (lblPorFavorIndique == null) {
			lblPorFavorIndique = new JLabel("Numero de Asistentes  :");
			lblPorFavorIndique.setFont(new Font("Gadugi", Font.PLAIN, 16));
			lblPorFavorIndique.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPorFavorIndique.setBounds(12, 0, 168, 65);
		}
		return lblPorFavorIndique;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setFont(new Font("Gadugi", Font.PLAIN, 15));
			spinner.setBackground(Color.WHITE);
			spinner.setEnabled(true);
			spinner.setModel(new SpinnerNumberModel(5, 1, 100, 5));
			spinner.setBounds(44, 66, 175, 43);
		}
		return spinner;
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
			btnContinuar.setBackground(Color.WHITE);
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					administracion.setInvitados((int) spinner.getValue());
					if(productos == null) {
						productos = new VentanaProductos(main,localizacion,administracion);
					}
					productos.setVisible(true);
					productos.getCarrito().updateInvites();
					main.setVisible(false);
					salir();
				}
			});
			btnContinuar.setFont(new Font("Gadugi", Font.PLAIN, 15));
			btnContinuar.setBounds(138, 120, 123, 25);
		}
		return btnContinuar;
	}
	
	private void salir() {
		this.dispose();
	}
	private void localizar(Locale localizacion) {
		textos = ResourceBundle.getBundle("rcs/Textos", localizacion);
		setTitle(textos.getString("tituloIntegrantes"));
		
		btnContinuar.setText(textos.getString("botonContinuar"));
		btnContinuar.setMnemonic(textos.getString("mnemonicoContinuar").charAt(0));
		btnContinuar.setToolTipText(textos.getString("tootltipContinuar"));
		
		lblPorFavorIndique.setText(textos.getString("labelAsistentes"));
		lblPorFavorIndique.setDisplayedMnemonic(textos.getString("mnemonicoAsistentes").charAt(0));
	}
}
