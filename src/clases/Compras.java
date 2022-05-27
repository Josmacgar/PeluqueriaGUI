package clases;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import bd.ConexionBD;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Compras extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel labelAtras;
	private JComboBox comboBox;
	private JComboBox comboBoxCli;
	private JSpinner spinner;
	private JComboBox comboBoxPago;
	private JTextField textTotal;
	private JButton comprobar;

	public Compras() {
		ConexionBD ob = new ConexionBD();
		setLayout(null);
		setBounds(0, 0, 1074, 629);

		labelAtras = new JLabel("");
		labelAtras.setIcon(new ImageIcon(Citas.class.getResource("/imagenes/flecha.png")));
		labelAtras.setBounds(40, 23, 58, 35);
		add(labelAtras);

		JLabel dni = new JLabel("servicio");
		dni.setFont(new Font("Tahoma", Font.BOLD, 16));
		dni.setBounds(97, 215, 95, 28);
		add(dni);

		JLabel nombre = new JLabel("Cliente:");
		nombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombre.setBounds(97, 272, 101, 28);
		add(nombre);

		
		JLabel lblGenerar = new JLabel("\u00A1Comprar!");
		lblGenerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					añadirCompra(ob, comboBox, comboBoxCli, spinner, comboBoxPago);

				} catch (NumberFormatException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblGenerar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenerar.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblGenerar.setBounds(414, 462, 109, 35);
		add(lblGenerar);

		comboBoxCli = new JComboBox();
		comboBoxCli.setBounds(208, 277, 136, 22);
		add(comboBoxCli);

		comboBox = new JComboBox();
		comboBox.setBounds(202, 220, 136, 22);
		add(comboBox);
		codServicios(ob, comboBox, comboBoxCli);

		JLabel cantidad = new JLabel("Cantidad:");
		cantidad.setFont(new Font("Tahoma", Font.BOLD, 16));
		cantidad.setBounds(91, 317, 101, 28);
		add(cantidad);

		JLabel pago = new JLabel("Pago:");
		pago.setFont(new Font("Tahoma", Font.BOLD, 16));
		pago.setBounds(91, 367, 86, 28);
		add(pago);

		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				obtenerPrecio(ob, comboBox, textTotal, spinner);
			}
		});
		spinner.setBounds(208, 319, 58, 28);
		add(spinner);

		comboBoxPago = new JComboBox();
		comboBoxPago.setBounds(202, 372, 136, 22);
		add(comboBoxPago);
		comboBoxPago.addItem("Tarjeta");
		comboBoxPago.addItem("Efectivo");

		JLabel total = new JLabel("Total:");
		total.setFont(new Font("Tahoma", Font.BOLD, 16));
		total.setBounds(524, 215, 101, 28);
		add(total);

		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(607, 207, 95, 49);

		add(textTotal);

		textTotal.setColumns(10);

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

	//metodo para añadir una compra
	public static void añadirCompra(ConexionBD ob, JComboBox<String> codServi, JComboBox<String> codCli,
			JSpinner cantidad, JComboBox<String> tipoPago) throws NumberFormatException, SQLException, IOException {

		try {
			ob.InsertarCompra(codServi, codCli, cantidad, tipoPago);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	// metodo que introduce los codigos de servicios y clientes en comboBox
	public static void codServicios(ConexionBD ob, JComboBox<String> comboBox, JComboBox<String> comboBox2) {
		for (String i : ob.getDatosServicios()) {
			comboBox.addItem(i);
		}
		for (String i : ob.getDatosClientes()) {
			comboBox2.addItem(i);
		}
	}

	//metodo para calcular el total
	public static void obtenerPrecio(ConexionBD ob, JComboBox<String> comboBox, JTextField textTotal,
			JSpinner spinner) {

		String numero = ob.getDatosPrecio(comboBox);
		double prueba = Double.parseDouble(numero);

		double resultado = prueba * Integer.parseInt(spinner.getValue().toString());
		DecimalFormat df = new DecimalFormat("#.00");
		textTotal.setText(String.valueOf(df.format(resultado)) + " €");
	}
}
