package Kunde_Artikel_MYSQL;

import java.sql.*;

public class Runner {
	
	static Connection getConnection(String url, String user, String pass) {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/INFI";
		String user = "vahoertnagl";
		String pass = ""; //pass ist gewollt leer
		
		Connection c = getConnection(url, user, pass);

		dropTable(c, "Artikel");
		dropTable(c, "Kunde");
		dropTable(c, "Bestellung");
		
		Artikel.createTable(c, "Artikel");
		Kunde.createTable(c, "Kunde");
		Bestellung.createTable(c, "Bestellung");
		
		Kunde.insertInto(c, "Kunde", "Valentin", "vahoertnagl@tsn.at");
		Kunde.insertInto(c, "Kunde", "Felix", "fell05@lose.at");
		Artikel.insertInto(c, "Artikel", "Bleistift", 12.50, 20);
		Artikel.insertInto(c, "Artikel", "Football", 25, 15);
		Bestellung.insertInto(c, "Bestellung", 1, 1, "2020-10-1", 10);
		Bestellung.insertInto(c, "Bestellung", 2, 2, "2022-5-12", 10);
		
		Bestellung.showBestellung(c, "Bestellung", 1);
		Bestellung.showBestellung(c, "Bestellung", 2);
		
		Bestellung.deleteBestellung(c, "Bestellung", 1, 1, "2020-10-1");
		Bestellung.updateBestellung(c, "Bestellung", 2, 2, "2022-05-12", 1);

	}
	
	static void dropTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists " + tableName + ";";
			System.out.println("Tabelle " + tableName + " wurde gelöscht.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
