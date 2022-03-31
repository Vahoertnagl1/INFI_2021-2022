package File_Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class File_Reader_Schueler {

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
			String pass = ""; //pass ist gewollt leer

			Connection c = getConnection(url, user, pass);

			File schueler = new File("/home/hoertnagl/eclipse-workspace/INFI_csv/schueler.csv");
			File klassen = new File("/home/hoertnagl/eclipse-workspace/INFI_csv/klassen.csv");
			Scanner s = new Scanner(schueler);
			String string = ""; //Zwischenspeicher für String
			
			Schueler.createTable(c, "schueler");
			Klassen.createTable(c, "klassen");

			while (s.hasNextLine()) {
				string = s.nextLine();
				String[] str = string.split(",");
				Schueler.insertInto(c, "schueler", str[0], str[1], str[2], str[3], str[4]);
			}
			
			s.close();
			Scanner s2 = new Scanner(klassen);
			String string2 = ""; //Zwischenspeicher für String
			
			while (s2.hasNextLine()) {
				string2 = s2.nextLine();
				String[] str = string2.split(",");
				Klassen.insertInto(c, "klassen", str[0], str[1]);
			}
			
			Schueler.showSchueler(c, "schueler");
			Klassen.showKlassen(c, "klassen");
			
			s2.close();
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
