package br.com.olx.challenge4.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {

		if (connection == null || connection.isClosed())
			connection = getConnectionDB();

		return connection;

	}

	private static Connection getConnectionDB() {
		Connection con = null;

		String url = "jdbc:postgresql://localhost:5432/olx";
		String user = "postgres";
		String password = "admin";
		try {
			//PostgreSQL driver
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
		}
		return con;
	}	

}
