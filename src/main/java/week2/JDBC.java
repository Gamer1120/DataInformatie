package week2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loding driver: " + e);
		}
		String host = "castle.ewi.utwente.nl";
		String pass = "kip";
		String dbName = "db02";
		String url = "jdbc:postgresql://" + host + ":5432/" + dbName;
		String sql = "SELECT name, year FROM movie WHERE rating >= 8.7 AND rating <= 9.0";
		try {
			Connection connection = DriverManager.getConnection(url, dbName,
					pass);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			ResultSetMetaData data = result.getMetaData();
			int columns = data.getColumnCount();
			while (result.next()) {
				for (int i = 1; i <= columns; i++) {
				System.out.print(data.getColumnName(i) + " = " + result.getObject(i) + "; ");
				}
				System.out.println();
			}
			connection.close();
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
