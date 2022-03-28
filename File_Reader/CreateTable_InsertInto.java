package File_import_export;

import java.sql.*;

public class CreateTable_InsertInto {

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName +
			" (id int auto_increment, vorname varchar(30), nachname varchar(30)," +
			" wohnort varchar(30), gebDat date, primary key (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, String vn, String nn, String wo, String inputDate) {
		try {
			Date date = Date.valueOf(inputDate);
			java.sql.Date outputDate = new java.sql.Date(date.getTime());
			String sql = "insert into " + tableName + " (vorname, nachname, wohnort, gebDat)" +
			" values (?, ?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, vn);
			stmt.setString(2, nn);
			stmt.setString(3, wo);
			stmt.setDate(4, outputDate);
			stmt.executeUpdate();
			stmt.close();
			System.out.println("insert --> excelReader.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
