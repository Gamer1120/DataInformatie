package week2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static void main(String[] args) {
		new JDBC();
	}

	public JDBC() {
		init();
	}

	public void init() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loding driver: " + e);
		}
		String host = "castle.ewi.utwente.nl";
		String pass = "kip";
		String dbName = "db02";
		String url = "jdbc:postgresql://" + host + ":5432/" + dbName;
		try {
			Connection connection = DriverManager.getConnection(url, dbName,
					pass);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT name, year FROM movie WHERE rating >= 8.7 AND rating <= 9.0");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
