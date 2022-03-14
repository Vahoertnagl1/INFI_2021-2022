package Test_2_Bsp;

import java.sql.*;

public class Klassen {
	
	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName + " (id int auto_increment, " +
			"klassenname varchar(20), primary key (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, String klassenname) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into " + tableName + " (klassenname) values " +
			"(\"" + klassenname + "\");";
			System.out.println("insert --> Klassen.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
