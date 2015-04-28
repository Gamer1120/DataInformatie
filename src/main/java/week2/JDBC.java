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

			String query = "SELECT DISTINCT person.name FROM person, writes WHERE person.pid = writes.pid AND writes.mid IN ( SELECT acts.mid FROM acts, person WHERE person.pid = acts.pid AND person.name = ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "Bruce Willis");
			statement.setFetchSize(5);
			ResultSet result = statement.executeQuery();
			connection.close();
			while (result.next()) {
				System.out.println(result.getString(1));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
