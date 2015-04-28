package week2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		String sql = "SELECT DISTINCT person.name FROM person, writes WHERE person.pid = writes.pid AND writes.mid IN (SELECT acts.mid FROM acts, person WHERE person.pid = acts.pid AND person.name = ?)";
		try {
			Connection connection = DriverManager.getConnection(url, dbName,
					pass);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "Bruce Willis");
			ResultSet result = statement.executeQuery();
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
