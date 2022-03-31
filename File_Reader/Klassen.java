package File_Reader;

import java.sql.*;

public class Klassen {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName +
			" (klasse varchar(20), schuelerAnzahl int,  primary key (klasse));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, String klasse, String anzahl) {
		try {
			String sql = "insert into " + tableName + " values " +
			"(?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, klasse);
			stmt.setString(2, anzahl);
			stmt.executeUpdate();
			stmt.close();
			System.out.println("insert --> klassen.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void showKlassen(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "select klasse, schuelerAnzahl from " + tableName + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String kl = rs.getString("klasse");
				int an = rs.getInt("schuelerAnzahl");
				System.out.printf("%s\t%d\n", kl, an);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
