package serverclient;

import java.sql.*;

public class Basededatos {
public static Connection conectar()
{
	try {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","root","");
		return cn;
	} 
	catch (SQLException e) {
		System.out.println("conexion error " + e);
	}
	return (null);
}

}