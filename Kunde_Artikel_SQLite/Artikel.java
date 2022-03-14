package Artikel_Kunde_Abgabe;

import java.sql.*;

public class Artikel {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName
					+ " (id integer primary key autoincrement, bezeichnung varchar(30), "
					+ "preis double, lagerbestand int);";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void insertInto(Connection c, String tableName, String bezeichnung, double preis, int lagerbestand) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into " + tableName + " (bezeichnung, preis, lagerbestand) values " + "(\""
					+ bezeichnung + "\", " + preis + ", " + lagerbestand + ");";
			System.out.println("insert --> Artikel.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateLagerbestand(Connection c, String tableName, int lagerbAlt, int anzahl, int artikelID) {
		try {
			Statement stmt = c.createStatement();
			int lagerbestandNeu = lagerbAlt - anzahl;
			String sql = "update " + tableName + " set lagerbestand = " + lagerbestandNeu + " where id = " + artikelID
					+ ";";
			System.out.println("Lagerbestand --> update.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
