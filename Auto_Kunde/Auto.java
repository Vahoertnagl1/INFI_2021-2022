package test2_1_Bsp;

import java.sql.*;

public class Auto {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName + " (kennzeichen varchar(20), " +
			"marke varchar(30), baujahr int, primary key (kennzeichen));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, String kennzeichen, String marke, int baujahr) {
		try {
			String sql = "insert into " + tableName + " values (?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, kennzeichen);
			stmt.setString(2, marke);
			stmt.setInt(3, baujahr);
			System.out.println("insert --> Auto.");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
