package tarea4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
		public static Connection Db() {
	        Connection conex = null;
	         
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacenitlafinal","root","");
	            //System.out.print("wenasssssssss");
	         
	        } catch (Exception e) {
	        	//System.out.print("wenas");
	            System.out.println("Error al conectar con la base de datos."+ e.getMessage().toString());
	        }
	        return conex;
	    }
} 

