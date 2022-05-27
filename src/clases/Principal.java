package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.ConexionBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Principal extends JFrame {

	private JPanel contentPane;
	private panel panel;
	private Citas citas;
	private Servicios servicios;
	private Compras compras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					JOptionPane.showMessageDialog(null, "Politica de Cookies\nAcepte para continuar");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Principal() throws SQLException {
		initComponents();

	}

	private void initComponents() throws SQLException {
		ConexionBD ob = new ConexionBD();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1081, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		//el siguiente codigo es para ir intercalando paneles
		panel = new panel();
		contentPane.add(panel);
		panel.setVisible(true);
		panel.getLabelCita().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.add(citas);
				citas.setVisible(true);
				panel.setVisible(false);
			}

		});
		panel.getLabelServi().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				contentPane.add(servicios);
				servicios.setVisible(true);
				panel.setVisible(false);
			}

		});
		panel.getLabelCompra().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				contentPane.add(compras);
				compras.setVisible(true);
				panel.setVisible(false);
			}

		});

		citas = new Citas();
		contentPane.add(citas);
		citas.setVisible(false);
		citas.getLabelAtras().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.add(panel);
				citas.setVisible(false);
				panel.setVisible(true);
			}

		});

		servicios = new Servicios();
		contentPane.add(servicios);
		servicios.setVisible(false);
		servicios.getLabelAtras().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.add(panel);
				servicios.setVisible(false);
				panel.setVisible(true);
			}

		});

		compras = new Compras();
		contentPane.add(compras);
		compras.setVisible(false);
		compras.getLabelAtras().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.add(panel);
				compras.setVisible(false);
				panel.setVisible(true);
			}

		});

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContent(JPanel ePanel) {

	}

	public Citas getCitas() {
		return citas;
	}

	public void setCitas(Citas citas) {
		this.citas = citas;
	}

}
