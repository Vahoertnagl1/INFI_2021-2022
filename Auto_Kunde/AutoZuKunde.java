package test2_1_Bsp;

import java.sql.*;
import java.time.LocalDate;

public class AutoZuKunde {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName + " (kundeID int, kennzeichen varchar(20), " +
			"andDatum date, endDatum date, primary key (kundeID, kennzeichen), foreign key (kundeID) references Kunde (id), " +
			"foreign key (kennzeichen) references Auto (kennzeichen));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, int kID, String kenn, LocalDate ad, LocalDate ed) {
		Date dateAn = Date.valueOf(ad);
		Date dateEn = Date.valueOf(ed);
		java.sql.Date sqlDateAn = new java.sql.Date(dateAn.getTime());
		java.sql.Date sqlDateEn = new java.sql.Date(dateEn.getTime());
		String sql = "insert into " + tableName + " values (?, ?, ?, ?);";
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, kID);
			stmt.setString(2, kenn);
			stmt.setDate(3, sqlDateAn);
			stmt.setDate(4, sqlDateEn);
			System.out.println("insert --> AutoZuKunde.");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
