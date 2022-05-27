package clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import bd.ConexionBD;

import javax.swing.JButton;
import javax.swing.JFrame;

public class panel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JLabel labelCita;
	private JLabel labelServi;
	private JLabel labelCompra;
	private JLabel fecha;
	private JLabel direccion;
	private JLabel salir;
	public panel() throws SQLException {
		ConexionBD ob = new ConexionBD();
		ob.cerrarConexion();
		setBounds(0, 0, 1074, 629);
		setLayout(null);


		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/logoBarber.png")));
		labelLogo.setBounds(23, 23, 181, 141);
		add(labelLogo);
		
		JLabel lblNewLabel = new JLabel("Tu Barberia De Confianza");
		lblNewLabel.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		lblNewLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		lblNewLabel.setBounds(258, 87, 550, 77);
		add(lblNewLabel);
		
		JLabel labelTitulo = new JLabel("Pro Barber");
		labelTitulo.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		labelTitulo.setFont(new Font("Lucida Handwriting", Font.BOLD, 34));
		labelTitulo.setBounds(374, 23, 240, 53);
		add(labelTitulo);
		
		JLabel bigote1 = new JLabel("");
		bigote1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/bigote.png")));
		bigote1.setBounds(303, 40, 52, 23);
		add(bigote1);
		
		JLabel bigote2 = new JLabel("");
		bigote2.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/bigote.png")));
		bigote2.setBounds(618, 40, 52, 23);
		add(bigote2);
		
		labelCita = new JLabel("");
		labelCita.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/cita.png")));
		labelCita.setBounds(123, 233, 123, 94);
		labelCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labelCita.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/citaOver.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				labelCita.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/cita.png")));
			}
		});
		add(labelCita);
		
		JLabel labelTCita = new JLabel("Clientes");
		labelTCita.setFont(new Font("Tahoma", Font.BOLD, 19));
		labelTCita.setHorizontalAlignment(SwingConstants.CENTER);
		labelTCita.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		labelTCita.setBounds(133, 325, 92, 23);
		add(labelTCita);
		
		labelServi = new JLabel("");
		labelServi.setIcon(new ImageIcon(panel.class.getResource("/imagenes/servicio.png")));
		labelServi.setBounds(450, 233, 123, 94);
		labelServi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labelServi.setIcon(new ImageIcon(panel.class.getResource("/imagenes/servicioOver.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				labelServi.setIcon(new ImageIcon(panel.class.getResource("/imagenes/servicio.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add(labelServi);
		
		JLabel labelTServi = new JLabel("Servicios");
		labelTServi.setHorizontalAlignment(SwingConstants.CENTER);
		labelTServi.setForeground(SystemColor.controlDkShadow);
		labelTServi.setFont(new Font("Tahoma", Font.BOLD, 19));
		labelTServi.setBounds(470, 325, 92, 23);
		add(labelTServi);
		
		labelCompra = new JLabel("");
		labelCompra.setIcon(new ImageIcon(panel.class.getResource("/imagenes/carritos.png")));
		labelCompra.setBounds(774, 233, 123, 94);
		labelCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labelCompra.setIcon(new ImageIcon(panel.class.getResource("/imagenes/carritosOver.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				labelCompra.setIcon(new ImageIcon(panel.class.getResource("/imagenes/carritos.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add(labelCompra);
		
		JLabel labelTCompra = new JLabel("Compra");
		labelTCompra.setHorizontalAlignment(SwingConstants.CENTER);
		labelTCompra.setForeground(SystemColor.controlDkShadow);
		labelTCompra.setFont(new Font("Tahoma", Font.BOLD, 19));
		labelTCompra.setBounds(805, 325, 92, 23);
		add(labelTCompra);
		
		fecha = new JLabel("");
		fecha.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		fecha.setHorizontalAlignment(SwingConstants.CENTER);
		fecha.setBounds(346, 482, 268, 53);
		add(fecha);
		
		LocalDate hoy = LocalDate.now();
		fecha.setText(String.valueOf(hoy.getDayOfMonth())+"-"+String.valueOf(hoy.getMonth())+"-"+String.valueOf(hoy.getYear()));
		
		direccion = new JLabel("Villanueva, 41660, Calvario, 31");
		direccion.setFont(new Font("Lucida Handwriting", Font.BOLD, 18));
		direccion.setBounds(31, 565, 345, 53);
		add(direccion);
		
		salir = new JLabel("");
		salir.setIcon(new ImageIcon(panel.class.getResource("/imagenes/salir.png")));
		salir.setBounds(987, 28, 63, 48);
		salir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				salir.setIcon(new ImageIcon(panel.class.getResource("/imagenes/salirOver.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				salir.setIcon(new ImageIcon(panel.class.getResource("/imagenes/salir.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ob.cerrarConexion();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(salir);
		
		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/madera2.jpg")));
		labelFondo.setBackground(SystemColor.controlShadow);
		labelFondo.setBounds(0, 0, 1074, 633);
		add(labelFondo);
			

	}
	public JLabel getLabelCita() {
		return labelCita;
	}
	public void setLabelCita(JLabel labelCita) {
		this.labelCita = labelCita;
	}
	public JLabel getLabelServi() {
		return labelServi;
	}
	public void setLabelServi(JLabel labelServi) {
		this.labelServi = labelServi;
	}
	public JLabel getLabelCompra() {
		return labelCompra;
	}
	public void setLabelCompra(JLabel labelCompra) {
		this.labelCompra = labelCompra;
	}
}
