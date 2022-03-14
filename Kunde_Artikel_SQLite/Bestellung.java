package Artikel_Kunde_Abgabe;

import java.sql.*;

public class Bestellung {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName + " (artikelID integer, kundeID integer, "
					+ "bestelldatum date, anzahl integer, " + "primary key(artikelID, kundeID, bestelldatum), "
					+ " foreign key(kundeID) references Kunde_Test (id), "
					+ " foreign key(artikelID) references Artikel_Test (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void insertInto(Connection c, String tableName, int kundeID, int artikelID, String bestelldatum,
			int anzahl) {
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select lagerbestand from Artikel where id = " + artikelID + ";");
			int xLagerbestand = rs.getInt("lagerbestand");
			rs.close();
			if (anzahl > xLagerbestand) {
				System.out.println("Lagerbestand ist zu niedrig!");
			} else {
				String sql = "insert into " + tableName + " values (" + kundeID + ", " + artikelID + ", \""
						+ bestelldatum + "\", " + anzahl + ");";
				System.out.println("insert --> Bestellung.");
				stmt.executeUpdate(sql);
				stmt.close();
				Artikel.updateLagerbestand(c, "Artikel", xLagerbestand, anzahl, artikelID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void showBestellung(Connection c, String tableName, int kundeID) {
		try {
			Statement stmt = c.createStatement();
			String sql = String.format("select c.name, a.bezeichnung, b.anzahl, (select b.anzahl * a.preis from "
					+ "Bestellung b inner join Artikel a on b.artikelID = a.id where b.kundeID = %d) "
					+ "as gesPreis from Kunde c inner join Bestellung b on c.id = b. kundeID "
					+ "inner join Artikel a on b.artikelID = a.id where b.kundeID = %d;", kundeID, kundeID);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String xname = rs.getString("name");
				String xbezeichnung = rs.getString("bezeichnung");
				int xanzahl = rs.getInt("anzahl");
				double xgesPreis = rs.getDouble("gesPreis");
				System.out.printf("\nName: %s\n", xname);
				System.out.printf("Bezeichnung: %s\n", xbezeichnung);
				System.out.printf("Anzahl: %d\n", xanzahl);
				System.out.printf("gesamt Preis: %.2f\n", xgesPreis);
				System.out.println();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void deleteBestellung(Connection c, String tableName, int kundeID, int artikelID, String bestelldatum) {
		try {
			Statement stmt = c.createStatement();
			String sql = "delete from " + tableName + " where kundeID = " + kundeID + " and " + "artikelID = "
					+ artikelID + " and bestelldatum = " + bestelldatum + ";";
			System.out.println("delete --> Bestellung.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void updateBestellung(Connection c, String tableName, int kundeID, int artikelID, String bestelldatum,
			int anzahl) {
		try {
			Statement stmt = c.createStatement();
			String sql = "update " + tableName + " set anzahl = " + anzahl + " where kundeID = " + kundeID + " and "
					+ "artikelID = " + artikelID + " and bestelldatum = " + bestelldatum + ";";
			System.out.println("update --> Bestellung.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
