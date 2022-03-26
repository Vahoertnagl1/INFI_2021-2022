package File_import_export;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class File_Reader_v2 {

	static Connection getConnection(String url, String user, String pass) {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			
			String url = "jdbc:mysql://localhost:3306/excelReader";
			String user = "vahoertnagl";
			String pass = ""; // pass ist gewollt leer

			Connection c = getConnection(url, user, pass);

			File f = new File("/home/hoertnagl/eclipse-workspace/Test/test_1.csv");
			Scanner s = new Scanner(f);
			String[] str = new String[3]; // 3 == Anzahl der Spalten
			String string = ""; //Zwischenspeicher für String

			while (s.hasNextLine()) {
				string = s.nextLine();
				str = string.split(",");
				String sql = "insert into personen (vorname, nachname, wohnort) values " +
				"(?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, str[0]);
				stmt.setString(2, str[1]);
				stmt.setString(3, str[2]);
				stmt.executeUpdate();
				stmt.close();
				System.out.println("insert --> Personen.");
			}
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File wurde nicht gefunden.");
		}
	}
}
