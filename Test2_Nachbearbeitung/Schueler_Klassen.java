package Test_2_Bsp;

import java.sql.*;
import java.time.LocalDate;

public class Schueler_Klassen {
	
	

	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName + " (sID int, kID int, " +
			"eintragsdatum date, primary key (sID, kID, eintragsdatum), foreign key (sID) references Schueler (id), " +
			"foreign key (kID) references Klassen (id));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void insertInto(Connection c, String tableName, int sID, int kID, LocalDate ld) {
		Date date = Date.valueOf(ld);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String sql = "insert into " + tableName + " (sID, kID, eintragsdatum) values (?, ?, ?);";
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, sID);
			stmt.setInt(2, kID);
			stmt.setDate(3, sqlDate);
			System.out.println("insert --> Schueler_Klassen.");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
