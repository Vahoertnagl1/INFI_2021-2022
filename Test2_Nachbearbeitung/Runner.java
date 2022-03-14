package Test_2_Bsp;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;

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

		String url = "jdbc:mysql://localhost:3306/test2";
		String user = "vahoertnagl";
		String pass = ""; // pass ist gewollt leer

		Connection c = getConnection(url, user, pass);
		
		try {
			c.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Schueler.createTable(c, "Schueler");
		Klassen.createTable(c, "Klassen");
		Schueler_Klassen.createTable(c, "Schueler_Klassen");
		
		Schueler.insertInto(c, "Schueler", "Valentin", "Hoertnagl", 17);
		Klassen.insertInto(c, "Klassen", "3AHWII");
		Schueler_Klassen.insertInto(c, "Schueler_Klassen", 1, 1, LocalDate.of(2022, Month.APRIL, 1));
		
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
