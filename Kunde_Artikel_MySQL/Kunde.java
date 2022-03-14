package Kunde_Artikel_MYSQL;

import java.sql.*;

public class Kunde {
	
	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName
					+ " (id int auto_increment, name varchar(30), email varchar(30), "
					+ "primary key (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void insertInto(Connection c, String tableName, String name, String email) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into " + tableName + " (name, email) values " + "(\"" + name + "\", \"" + email
					+ "\");";
			System.out.println("insert --> Kunde.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
