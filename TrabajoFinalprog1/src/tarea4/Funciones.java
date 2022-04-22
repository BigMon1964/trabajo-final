package tarea4;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Funciones extends Datos{
	private static DefaultTableModel model = new DefaultTableModel(); 
	private static DefaultTableModel modelAlmacen = new DefaultTableModel(); 
	private static int iDUser;
	
	public Funciones(){
		if(model.getColumnCount() ==0) {
			model.addColumn("ID");model.addColumn("nombre");model.addColumn("apellido");model.addColumn("telefono");model.addColumn("correo electronico");model.addColumn("usuario");
			modelAlmacen.addColumn("ID");modelAlmacen.addColumn("Nombre");modelAlmacen.addColumn("Marca");modelAlmacen.addColumn("Categoria");modelAlmacen.addColumn("Precio");modelAlmacen.addColumn("Cantidad Disponible");
		}
		updateJtable();
		updateTablaAlmacen();
	}
	
	public boolean Login(String usuario, String password){
		boolean respuesta = false;
		String query = "Select * from usuarios where UserName = " + "'"+usuario+"'" + " and Password = "+ "'"+password+"'";
		java.sql.Connection conexion  = Conexion.Db();

		try {
			Statement statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(query);
				if(rs.next()) {
					System.out.print(rs.getObject(1));
					Menu menu = new Menu();
					menu.setVisible(true);
					respuesta = true;
					iDUser= Integer.valueOf(rs.getString("idUser").toString());
					System.out.println(iDUser);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return respuesta;
	}
	
	
	public void updateJtable() {
		String query = "Select * from usuarios";
		java.sql.Connection conexion  = Conexion.Db();

		try {
			Statement statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (model.getRowCount() > 0) {
			       model.removeRow(0);
			}
			while(rs.next()) {
				System.out.print(rs.getObject(1));	
				model.addRow(new Object[]{rs.getString("idUser"),rs.getString("Nombre"),rs.getString("Apellido"),rs.getString("Telefono"),rs.getString("Email"),rs.getString("UserName")});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	static DefaultTableModel getModel() {
		return model;
	}
	
	static DefaultTableModel getModelAlmacen() {
		return modelAlmacen;
	}
	
	@Override
	boolean eliminarDatos(int fila) {
		java.sql.Connection conexion  = Conexion.Db();
		try {
			String query = "delete from usuarios where idUser = "+ model.getValueAt(fila, 0).toString(); 
			Statement statement = conexion.prepareStatement(query);
			statement.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(Integer.parseInt(model.getValueAt(fila, 0).toString()) == iDUser) {
			JOptionPane.showMessageDialog(null, "Acabas de borrar el usuario actual.", "Error", JOptionPane.ERROR_MESSAGE);
			Login lg = new Login();
			lg.setVisible(true);
			return true;
		}
		updateJtable();
		return false;
	}

	@Override
	boolean editarDatos(String nombre, String apellido, String telefono, String correo, String usuario, String password, int fila) {
		java.sql.Connection conexion  = Conexion.Db();
		try {
			//UPDATE `usuarios` SET `idUser`='[value-1]',`UserName`='[value-2]',`Nombre`='[value-3]',`Apellido`='[value-4]',`Telefono`='[value-5]',`Email`='[value-6]',`Password`='[value-7]' WHERE 1
			String query = "update usuarios set UserName = " + "'"+usuario+"'" + ",  Password = "+ "'"+password+"'" + ", Nombre =" +"'"+nombre+"'"+", Apellido =" +"'"+apellido+"'"+
							",Telefono =" +"'"+telefono+"'" +", Email =" +"'"+correo+"'" + " where idUser = "+ "'"+Integer.parseInt(model.getValueAt(fila, 0).toString())+"'";
			System.out.println(query);
			Statement statement = conexion.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateJtable();
		return true;
	}

	@Override
	void addDatos(String nombre, String apellido, String telefono, String correo, String usuario, String password) {
		java.sql.Connection conexion  = Conexion.Db();
		try {
			//UPDATE `usuarios` SET `idUser`='[value-1]',`UserName`='[value-2]',`Nombre`='[value-3]',`Apellido`='[value-4]',`Telefono`='[value-5]',`Email`='[value-6]',`Password`='[value-7]' WHERE 1
			String query = "Insert into usuarios (UserName, Nombre, Apellido, Telefono, Email, Password) values ('"+usuario+"'" +",'"+nombre+"'"+ ",'"+apellido+"'"+",'"+telefono+"'" + ",'"+correo+"'" + ",'"+password+"')";
			System.out.println(query);
			Statement statement = conexion.createStatement();
			statement.executeUpdate(query);
			updateJtable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateJtable();
	}
	
	void updateTablaAlmacen() {
		String query = "Select * from productos";
		java.sql.Connection conexion  = Conexion.Db();

		try {
			Statement statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (modelAlmacen.getRowCount() > 0) {
			       modelAlmacen.removeRow(0);
			}
			while(rs.next()) {
				System.out.print(rs.getObject(1));	
				modelAlmacen.addRow(new Object[]{rs.getString("idProducto"),rs.getString("NombreProducto"),rs.getString("MarcaProducto"),rs.getString("CategoriaProducto"),rs.getString("PrecioProducto"),rs.getString("StockProducto")});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public boolean NuevoProducto(String nombreProducto, String marcaProducto, String categoriaProducto, String precioProducto, String stockProducto) {
		java.sql.Connection conexion  = Conexion.Db();
		try {
			String query = "Insert into productos(NombreProducto, MarcaProducto, CategoriaProducto, PrecioProducto, StockProducto) values ('"+nombreProducto+"'" +",'"+marcaProducto+"'"+ ",'"+categoriaProducto+"'"+","+precioProducto+"" + ","+stockProducto+")";
			System.out.println(query);
			Statement statement = conexion.createStatement();
			statement.executeUpdate(query);
			updateJtable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateTablaAlmacen();
		return true;
	}
	
	public boolean EliminarProducto(int id) {
		java.sql.Connection conexion  = Conexion.Db();
		try {
			String query = "delete from productos where idProducto = "+ model.getValueAt(id, 0).toString(); 
			Statement statement = conexion.prepareStatement(query);
			statement.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateTablaAlmacen();
		return true;
	}
	
	/*public int GetIdDiferente() {
		String query = "Select * from productos";
		java.sql.Connection conexion  = Conexion.Db();

		try {
			Statement statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (modelAlmacen.getRowCount() > 0) {
			       modelAlmacen.removeRow(0);
			}
			while(rs.next()) {
				System.out.print(rs.getObject(1));	
				modelAlmacen.addRow(new Object[]{rs.getString("idProducto"),rs.getString("NombreProducto"),rs.getString("MarcaProducto"),rs.getString("CategoriaProducto"),rs.getString("PrecioProducto"),rs.getString("StockProducto")});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}*/
}
