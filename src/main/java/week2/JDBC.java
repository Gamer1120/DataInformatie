package week2;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBC {

	String host = "castle.ewi.utwente.nl";
	String pass = "kip";
	String dbName = "db02";
	String url = "jdbc:postgresql://" + host + ":5432/" + dbName;
	Connection connection;
	Statement statement;

	public static void main(String[] args) {
		new JDBC();
	}

	public JDBC() {
		try {
			connection = DriverManager.getConnection(url, dbName, pass);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		init();
	}

	public void init() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loding driver: " + e);
		}

		int iters = 5;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < iters; i++) {
			for (Integer mid : alleFilms()) {
				if (heeftRegisseur(mid)) {
					ArrayList<String> aut = auteurVanFilm(mid);
				}
			}
		}
		System.out.println((System.currentTimeMillis() - startTime)
				/ (1.0 * iters));

	}

	public ArrayList<Integer> alleFilms() {
		ArrayList<Integer> retList = new ArrayList<Integer>();
		ResultSet res = null;
		try {
			res = statement.executeQuery("SELECT allefilms()");
			while (res.next()) {
				retList.add(res.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}

	public ArrayList<String> auteurVanFilm(int mid) {
		ArrayList<String> retList = new ArrayList<String>();
		ResultSet res = null;
		try {
			res = statement.executeQuery("SELECT auteurvanfilm(" + mid + ")");
			while (res.next()) {
				retList.add(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}

	public boolean heeftRegisseur(int mid) {
		ResultSet res = null;
		boolean result = true;
		try {
			res = statement.executeQuery("SELECT heeftregisseur(" + mid + ")");
			while (res.next()) {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
