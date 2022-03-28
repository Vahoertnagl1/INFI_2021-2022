package File_import_export;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
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
			String pass = "ahoi12345"; //pass ist gewollt leer

			Connection c = getConnection(url, user, pass);

			File f = new File("/home/hoertnagl/eclipse-workspace/INFI_csv/test_1.csv");
			Scanner s = new Scanner(f);
			String[] str = new String[3]; // 3 == Anzahl der Spalten
			String string = ""; //Zwischenspeicher für String
			
			CreateTable_InsertInto.createTable(c, "excelReader");

			while (s.hasNextLine()) {
				string = s.nextLine();
				str = string.split(",");
				CreateTable_InsertInto.insertInto(c, "excelReader", str[0], str[1], str[2], LocalDate.now());
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
