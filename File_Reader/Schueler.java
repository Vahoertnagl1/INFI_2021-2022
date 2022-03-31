package File_Reader;

import java.sql.*;

public class Schueler {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName +
			" (id int auto_increment, vorname varchar(30), nachname varchar(30)," +
			" wohnort varchar(30), gebDat date, klasse varchar(20), primary key (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, String vn, String nn, String wo, String inputDate, String klasse) {
		try {
			Date date = Date.valueOf(inputDate);
			java.sql.Date outputDate = new java.sql.Date(date.getTime());
			String sql = "insert into " + tableName + " (vorname, nachname, wohnort, gebDat, klasse)" +
			" values (?, ?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, vn);
			stmt.setString(2, nn);
			stmt.setString(3, wo);
			stmt.setDate(4, outputDate);
			stmt.setString(5, klasse);
			stmt.executeUpdate();
			stmt.close();
			System.out.println("insert --> schueler.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void showSchueler(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "select vorname, nachname, wohnort, gebDat, klasse from " + tableName + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			String vn = rs.getString("vorname");
			String nn = rs.getString("nachname");
			String wo = rs.getString("wohnort");
			Date date = rs.getDate("gebDat");
			String kl = rs.getString("klasse");
			System.out.printf("%s\t%s\t%s\t%s\t%s\n", vn, nn, wo, date, kl);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
