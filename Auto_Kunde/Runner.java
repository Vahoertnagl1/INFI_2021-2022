package test2_1_Bsp;

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
		
		String url = "jdbc:mysql://localhost:3306/test2_1";
		String user = "vahoertnagl";
		String pass = ""; //pass ist gewollt leer
		
		try {
			Connection c = getConnection(url, user, pass);
			c.setAutoCommit(true);
			
			Auto.createTable(c, "Auto");
			Kunde.createTable(c, "Kunde");
			AutoZuKunde.createTable(c, "AutoZuKunde");
			
			Auto.insertInto(c, "Auto", "IL223KO", "BMW", 2013);
			Kunde.insertInto(c, "Kunde", "Valentin", "Hoertnagl", 18);
			AutoZuKunde.insertInto(c, "AutoZuKunde", 1, "IL223KO", LocalDate.now(), LocalDate.of(2022, Month.MARCH, 20));
			
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
