package tarea4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddAlmacen extends JFrame {

	private JPanel contentPane;
	private JTextField tFNombre;
	private JTextField tFMarca;
	private JTextField tFCategoria;
	Funciones fn = new Funciones();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAlmacen frame = new AddAlmacen();
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
	public AddAlmacen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddProduct = new JLabel("A\u00F1adir Productos");
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddProduct.setBounds(27, 22, 183, 41);
		contentPane.add(lblAddProduct);
		
		tFNombre = new JTextField();
		tFNombre.setBounds(85, 109, 199, 20);
		contentPane.add(tFNombre);
		tFNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(95, 89, 81, 14);
		contentPane.add(lblNombre);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(95, 151, 81, 14);
		contentPane.add(lblMarca);
		
		tFMarca = new JTextField();
		tFMarca.setColumns(10);
		tFMarca.setBounds(85, 171, 199, 20);
		contentPane.add(tFMarca);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategoria.setBounds(95, 211, 81, 14);
		contentPane.add(lblCategoria);
		
		tFCategoria = new JTextField();
		tFCategoria.setColumns(10);
		tFCategoria.setBounds(85, 231, 199, 20);
		contentPane.add(tFCategoria);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrecio.setBounds(95, 272, 81, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblCantidadDisponible = new JLabel("Cantidad Disponible");
		lblCantidadDisponible.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidadDisponible.setBounds(95, 334, 115, 14);
		contentPane.add(lblCantidadDisponible);
		
		JSpinner spCantidad = new JSpinner();
		spCantidad.setBounds(85, 359, 199, 20);
		contentPane.add(spCantidad);
		
		JSpinner spPrecio = new JSpinner();
		spPrecio.setBounds(85, 297, 199, 20);
		contentPane.add(spPrecio);
		
		JButton btnAdd = new JButton("A\u00F1adir");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tFNombre.getText().length() == 0 || tFMarca.getText().length() == 0 || tFCategoria.getText().length() == 0 || spPrecio.getValue().toString().length() == 0 || spCantidad.getValue().toString().length() == 0) {
					JOptionPane.showMessageDialog(null, "No puedes dejar los campos vacios");
				}else {
					if(fn.NuevoProducto(tFNombre.getText(), tFMarca.getText(), tFCategoria.getText(), spPrecio.getValue().toString(), spCantidad.getValue().toString())) {
						JOptionPane.showMessageDialog(null, "Producto añadido con exito.");
						dispose();
					}	
				}
				
			}
		});
		btnAdd.setBounds(134, 417, 89, 23);
		contentPane.add(btnAdd);
	}
}
