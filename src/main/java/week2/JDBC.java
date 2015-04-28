package week2;

import java.sql.PreparedStatement;
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

			String query = "SELECT moviesofactor(?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "Bruce Willis");
			statement.setFetchSize(5);
			int iters = 100;
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < iters; i++) {
				ResultSet result = statement.executeQuery();
			}
			System.out.println((System.currentTimeMillis() - startTime) / (1.0
					* iters));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
