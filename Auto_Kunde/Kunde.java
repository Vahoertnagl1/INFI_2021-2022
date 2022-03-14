package test2_1_Bsp;

import java.sql.*;

public class Kunde {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName + " (id int auto_increment, " +
			"vorname varchar(30), nachname varchar(30), age int, primary key (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, String vn, String nn, int age) {
		try {
			String sql = "insert into " + tableName + " (vorname, nachname, age) values " +
			"(?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, vn);
			stmt.setString(2, nn);
			stmt.setInt(3, age);
			System.out.println("insert --> Kunde.");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
