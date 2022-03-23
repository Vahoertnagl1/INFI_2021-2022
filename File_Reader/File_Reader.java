package File_import_export;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class File_Reader {

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
			String[][] str = new String[6][3]; //6 == Anzahl Zeilen; 3 == Anzahl Spalten
			String string = ""; // String Zwischenspeicher
			int i = 0; // Laufvariable

			while (s.hasNextLine()) {
				string = s.nextLine();
				str[i] = string.split(","); // eine Zeile wird in str gespeichert & bei "," abgeschnitten
				String sql = "insert into personen (vorname, nachname, wohnort) values (?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, str[i][0]);
				stmt.setString(2, str[i][1]);
				stmt.setString(3, str[i][2]);
				stmt.executeUpdate();
				stmt.close();
				System.out.printf("insert --> %d. personen.\n", i + 1);
				i++;
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File wurde nicht gefunden.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception.");
		}
	}
}
