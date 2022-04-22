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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Almacen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Funciones fn = new Funciones();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Almacen frame = new Almacen();
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
	public Almacen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductosAlmacen = new JLabel("Productos Almacen");
		lblProductosAlmacen.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProductosAlmacen.setBounds(22, 11, 283, 77);
		contentPane.add(lblProductosAlmacen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 689, 243);
		contentPane.add(scrollPane);
		
		table = new JTable(Funciones.getModelAlmacen());
		scrollPane.setViewportView(table);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddAlmacen addAlmacen = new AddAlmacen();
				addAlmacen.setVisible(true);
			}
		});
		btnNuevo.setBounds(34, 335, 119, 34);
		contentPane.add(btnNuevo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.show(true);
				dispose();
			}
		});

		btnAtras.setBounds(333, 335, 119, 34);
		contentPane.add(btnAtras);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() != -1) {
					if(fn.EliminarProducto(table.getSelectedRow())) {
						JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar una tabla");
				}
			}
		});
		btnEliminar.setBounds(186, 335, 119, 34);
		contentPane.add(btnEliminar);
	}
}
