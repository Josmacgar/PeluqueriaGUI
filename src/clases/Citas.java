package clases;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import bd.ConexionBD;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class Citas extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel labelAtras;
	private JTextField textDni;
	private JTextField textNom;
	private JTextField textApe1;
	private JTextField textApe2;
	private JTextArea textArea;

	public Citas() {
		ConexionBD ob = new ConexionBD();
		setLayout(null);
		setBounds(0, 0, 1074, 629);

		labelAtras = new JLabel("");
		labelAtras.setIcon(new ImageIcon(Citas.class.getResource("/imagenes/flecha.png")));
		labelAtras.setBounds(40, 23, 58, 35);
		add(labelAtras);

		JLabel dni = new JLabel("DNI:");
		dni.setFont(new Font("Tahoma", Font.BOLD, 16));
		dni.setBounds(97, 215, 52, 28);
		add(dni);

		textDni = new JTextField();
		textDni.setForeground(Color.BLACK);
		textDni.setBounds(159, 219, 136, 20);
		textDni.setColumns(10);
		add(textDni);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombre.setBounds(97, 272, 101, 28);
		add(nombre);

		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(197, 278, 136, 20);
		add(textNom);

		JLabel ape1 = new JLabel("Apellido 1:");
		ape1.setFont(new Font("Tahoma", Font.BOLD, 16));
		ape1.setBounds(97, 322, 101, 28);
		add(ape1);

		JLabel ape2 = new JLabel("Apellido 2:");
		ape2.setFont(new Font("Tahoma", Font.BOLD, 16));
		ape2.setBounds(97, 383, 101, 28);
		add(ape2);

		textApe1 = new JTextField();
		textApe1.setColumns(10);
		textApe1.setBounds(208, 328, 136, 20);
		add(textApe1);

		textApe2 = new JTextField();
		textApe2.setColumns(10);
		textApe2.setBounds(208, 389, 136, 20);
		add(textApe2);

		ArrayList<JTextField> listaText = new ArrayList<JTextField>();
		listaText.add(textDni);
		listaText.add(textApe1);
		listaText.add(textApe2);
		listaText.add(textNom);
		
		//los botones se van habilitando o no dependiendo de la accion a realizar
		JRadioButton rdbAñadir = new JRadioButton("A\u00F1adir");
		rdbAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDni.setEnabled(true);
				textNom.setEnabled(true);
				textApe1.setEnabled(true);
				textApe2.setEnabled(true);
			}
		});
		rdbAñadir.setBackground(new Color(204, 153, 102));
		rdbAñadir.setBounds(97, 96, 109, 23);
		add(rdbAñadir);

		JRadioButton rdbElim = new JRadioButton("Eliminar");
		rdbElim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDni.setEnabled(true);
				textNom.setEnabled(false);
				textApe1.setEnabled(false);
				textApe2.setEnabled(false);
			}
		});
		rdbElim.setBackground(new Color(204, 153, 102));
		rdbElim.setBounds(414, 96, 109, 23);
		add(rdbElim);

		JRadioButton rdbVer = new JRadioButton("Ver");
		rdbVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDni.setEnabled(false);
				textNom.setEnabled(false);
				textApe1.setEnabled(false);
				textApe2.setEnabled(false);
			}
		});
		rdbVer.setBackground(new Color(204, 153, 102));
		rdbVer.setBounds(761, 96, 109, 23);
		add(rdbVer);

		ButtonGroup grupoRadio = new ButtonGroup();
		grupoRadio.add(rdbAñadir);
		grupoRadio.add(rdbElim);
		grupoRadio.add(rdbVer);

		ArrayList<JRadioButton> listaRadio = new ArrayList<>();
		listaRadio.add(rdbAñadir);
		listaRadio.add(rdbElim);
		listaRadio.add(rdbVer);

		JLabel lblGenerar = new JLabel("Generar:");
		lblGenerar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenerar.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblGenerar.setBounds(414, 495, 109, 35);
		lblGenerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGenerar.setForeground(UIManager.getColor("CheckBox.darkShadow"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblGenerar.setForeground(Color.black);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				int radio = botonSeleccion(listaRadio);
				switch (radio) {
				case 0:
					try {
						// lo siguiente sirve para que todos los textos esten rellenos
						int contador = 0;
						for (JTextField j : listaText) {
							if (j.getText().isEmpty()) {
								contador++;
							}
						}
						if (contador > 0) {
							JOptionPane.showMessageDialog(null, "Faltan datos");
						} else {

							if (controlarDni(textDni) == 9) {
								añadirClientes(ob, textDni, textNom, textApe1, textApe2);
							} else {
								JOptionPane.showMessageDialog(null, "Dni incorrecto");
							}

						}

					} catch (NumberFormatException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				case 1:
					try {
						// los siguientes if sirven para que el dni no este en blanco
						int contador = 0;
						if (textDni.getText().isEmpty()) {
							contador++;
						}
						if (contador > 0) {
							JOptionPane.showMessageDialog(null, "Falta el dni");
						} else {
							eliminarClientes(ob, textDni);
						}
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					break;
				case 2:
					resultados(textArea, ob);
					break;
				}

			}
		});
		add(lblGenerar);

		textArea = new JTextArea();
		textArea.setBounds(533, 238, 531, 380);
		textArea.setBackground(new Color(245, 211, 143));
		add(textArea);

		JScrollPane scrol = new JScrollPane(textArea);
		scrol.setBounds(533, 238, 531, 380);
		add(scrol);

		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(Citas.class.getResource("/imagenes/madera2.jpg")));
		labelFondo.setBackground(SystemColor.controlShadow);
		labelFondo.setBounds(0, 0, 1074, 633);
		add(labelFondo);

	}

	public JLabel getLabelAtras() {
		return labelAtras;
	}

	public void setLabelAtras(JLabel labelAtras) {
		this.labelAtras = labelAtras;
	}

	//metodo que devuelve el numero del boton seleccionado
	public static int botonSeleccion(ArrayList<JRadioButton> listaRadio) {
		int numero = 0;
		for (int i = 0; i < listaRadio.size(); i++) {
			if (listaRadio.get(i).isSelected()) {
				numero = i;
			}
		}
		return numero;
	}

	//metodo que escribe los datos de los clientes en el textArea
	public static void resultados(JTextArea text, ConexionBD ob) {
		ob.getDatosClientes(text);
	}

	//metodo para añadir clientes
	public static void añadirClientes(ConexionBD ob, JTextField dni, JTextField nombre, JTextField ape1,
			JTextField ape2) throws NumberFormatException, SQLException, IOException {

		try {
			ob.InsertarClientes(dni, nombre, ape1, ape2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	//metodo para eliminar clientes
	public static void eliminarClientes(ConexionBD ob, JTextField dni) {
		try {
			ob.EliminarClientes(dni);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	// metodo para controlar si el dni es correcto
	public static int controlarDni(JTextField textDni) {
		int contarDni = 0;
		String datos = textDni.getText();
		if (datos.length() == 9) {

			for (int i = 0; i < datos.length() - 1; i++) {
				if (Character.isDigit(datos.charAt(i))) {
					contarDni++;
				}
			}
			if (Character.isLetter(datos.charAt(datos.length() - 1))) {
				contarDni++;
			}
		}
		return contarDni;
	}
}
