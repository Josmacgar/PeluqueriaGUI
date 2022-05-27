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
import javax.swing.JComboBox;

public class Servicios extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel labelAtras;
	private JTextField textNom;
	private JTextField textDes;
	private JTextField textPrecio;
	private JTextArea textArea;
	private JTextField textField;
	private JTextField textCods;
	private JComboBox comboBoxCat;
	private Categorias categorias;

	public Servicios() {
		ConexionBD ob = new ConexionBD();
		setLayout(null);
		setBounds(0, 0, 1074, 629);

		labelAtras = new JLabel("");
		labelAtras.setIcon(new ImageIcon(Servicios.class.getResource("/imagenes/flecha.png")));
		labelAtras.setBounds(40, 23, 58, 35);
		add(labelAtras);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombre.setBounds(97, 272, 101, 28);
		add(nombre);

		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(197, 278, 136, 20);
		add(textNom);

		JLabel des = new JLabel("Descripcion:");
		des.setFont(new Font("Tahoma", Font.BOLD, 16));
		des.setBounds(97, 322, 101, 28);
		add(des);

		JLabel precio = new JLabel("Precio:");
		precio.setFont(new Font("Tahoma", Font.BOLD, 16));
		precio.setBounds(97, 383, 101, 28);
		add(precio);

		textDes = new JTextField();
		textDes.setColumns(10);
		textDes.setBounds(208, 328, 136, 20);
		add(textDes);

		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(176, 389, 136, 20);
		add(textPrecio);

		JLabel categoria = new JLabel("Categoria:");
		categoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		categoria.setBounds(97, 435, 101, 28);
		add(categoria);

		JLabel codigoS = new JLabel("Codigo:");
		codigoS.setFont(new Font("Tahoma", Font.BOLD, 16));
		codigoS.setBounds(97, 224, 101, 28);
		add(codigoS);

		textCods = new JTextField();
		textCods.setColumns(10);
		textCods.setBounds(186, 230, 136, 20);
		add(textCods);

		ArrayList<JTextField> listaText = new ArrayList<JTextField>();
		listaText.add(textDes);
		listaText.add(textNom);
		listaText.add(textPrecio);
		listaText.add(textCods);

		JRadioButton rdbAñadir = new JRadioButton("A\u00F1adir");
		rdbAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxCat.setEnabled(true);
				textNom.setEnabled(true);
				textDes.setEnabled(true);
				textPrecio.setEnabled(true);
				textCods.setEnabled(false);
			}
		});
		rdbAñadir.setBackground(new Color(204, 153, 102));
		rdbAñadir.setBounds(97, 96, 109, 23);
		add(rdbAñadir);

		JRadioButton rdbElim = new JRadioButton("Eliminar");
		rdbElim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxCat.setEnabled(false);
				textNom.setEnabled(false);
				textDes.setEnabled(false);
				textPrecio.setEnabled(false);
				textCods.setEnabled(true);
			}
		});
		rdbElim.setBackground(new Color(204, 153, 102));
		rdbElim.setBounds(414, 96, 109, 23);
		add(rdbElim);

		JRadioButton rdbVer = new JRadioButton("Ver");
		rdbVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxCat.setEnabled(true);
				textNom.setEnabled(false);
				textDes.setEnabled(false);
				textPrecio.setEnabled(false);
				textCods.setEnabled(false);
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

		comboBoxCat = new JComboBox();
		comboBoxCat.setBounds(197, 440, 136, 22);
		add(comboBoxCat);

		// se añaden las categorias del enum
		Categorias[] listaC = categorias.values();
		for (Categorias c : listaC) {
			comboBoxCat.addItem(String.valueOf(c));
		}

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
						// se resta uno si el codigo esta vacio. porque siempre lo esta
						if (textCods.getText().isEmpty()) {
							contador--;
						}

						if (contador > 0) {
							JOptionPane.showMessageDialog(null, "Faltan datos");
						} else {
							añadirServicios(ob, textNom, textDes, textPrecio, comboBoxCat);
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
						if (textCods.getText().isEmpty()) {
							contador++;
						}
						if (contador > 0) {
							JOptionPane.showMessageDialog(null, "Falta el dni");
						} else {
							eliminarServicios(ob, textCods);
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
		labelFondo.setIcon(new ImageIcon(Servicios.class.getResource("/imagenes/madera2.jpg")));
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

	//metodo que devuelve los servicios
	public static void resultados(JTextArea text, ConexionBD ob) {
		ob.getDatosServicios(text);
	}

	//metodo para añadir servicios
	public static void añadirServicios(ConexionBD ob, JTextField nombre, JTextField des, JTextField precio,
			JComboBox<String> cat) throws NumberFormatException, SQLException, IOException {

		try {
			ob.InsertarServicios(nombre, des, precio, cat);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	//metodo para eliminar servicios
	public static void eliminarServicios(ConexionBD ob, JTextField codS) {
		try {
			ob.EliminarServicios(codS);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
