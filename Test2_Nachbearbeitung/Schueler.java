package Test_2_Bsp;

import java.sql.*;

public class Schueler {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName + " (id int auto_increment, vorname varchar(30), " +
			"nachname varchar(30), age int, primary key (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, String vorname, String nachname, int age) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into " + tableName + " (vorname, nachname, age) values " +
			"(\"" + vorname + "\", \"" + nachname + "\", " + age + ");";
			System.out.println("insert --> Schueler.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
